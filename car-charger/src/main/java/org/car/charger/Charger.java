package org.car.charger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Collectors;

import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.worldpay.innovation.wpwithin.PSPConfig;
import com.worldpay.innovation.wpwithin.WPWithinGeneralException;
import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.eventlistener.EventListener;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;
import com.worldpay.innovation.wpwithin.types.WWService;
import com.worldpay.innovation.wpwithin.types.WWServiceDeliveryToken;
import com.worldpay.innovation.wpwithin.types.WWTotalPriceResponse;

public class Charger {
	private static String rpcLogFile;
	private static Config config;
	private WPWithinWrapper wpw;
	private JSONObject chargerJsonObject;
	private boolean broadcasting;

	/**
	 * Initializes JSONObject as we keep charger's state information there This
	 * should be moved to some specific object props in the future to get code a bit
	 * cleaner
	 */
	public Charger() {
		this.chargerJsonObject = new JSONObject();
	}

	private void updateFlow(JsonTags tag, String msg) {
		chargerJsonObject.put(tag.getTag(), msg);
	}

	public JSONObject getChargerJsonObject() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(tz);
		this.chargerJsonObject.put(JsonTags.TIMESTAMP.getTag(), df.format(new Date()));
		return this.chargerJsonObject;
	}

	public String startBroadcasting() { // sync?
		if (this.broadcasting == true) {
			return "{\"status\":\"already started?\"}"; // exception ?
		}
		this.broadcasting = true;
		wpw.startServiceBroadcast(0);
		updateFlow(JsonTags.BROADCAST_STATUS, "broadcasting");
		updateFlow(JsonTags.FLOW, "Broadcasting...");
		return "{\"status\":\"started\"}";
	}

	public String stopBroadcasting() { // sync?
		if (this.broadcasting == false) {
			return "{\"status\":\"already stopped?\"}"; // exception ?
		}
		this.broadcasting = false;
		wpw.stopServiceBroadcast();
		updateFlow(JsonTags.BROADCAST_STATUS, "off");
		updateFlow(JsonTags.FLOW, "standby");
		return "{\"status\":\"stopped\"}";
	}

	public void run() throws Exception {
		try {

			// Begin
			updateFlow(JsonTags.FLOW, "Car charger example...");

			// Load config, init WPW and start broadcasting
			loadConfig();
			wpw = new WPWithinWrapperImpl(config.getHost(), config.getPort(), true, wpWithinEventListener,
					config.getCallbackPort(), rpcAgentListener, rpcLogFile);
			wpw.setup(config.getDeviceName(), "Car charger device.", config.getInterfaceAddr());
			WWService svc = new WWService();
			svc.setName("Car charger");
			svc.setDescription("Hybrid / electric car charger.");
			svc.setId(1);
			ChargingServices chargingServices = new ChargingServices();
			svc.setPrices(chargingServices.getServicesMap());
			wpw.addService(svc);
			SGXWrapper sgx = SGXWrapper.INSTANCE;
			LongByReference eid = new LongByReference();
			IntByReference updated = new IntByReference();
			LongByReference[] token = new LongByReference[1024];
			long tmp = sgx.en_create_enclave(token, eid, updated);
			String htePublicKey = null, htePrivateKey = null, merchantClientKey = null, merchantServiceKey = null;
			if(tmp == 0) {
				System.out.println("Enclave initialized.");
				int len = 100;
				byte[] byteArray = new byte[len];
				sgx.en_get_hte_public_key(eid.getValue(), byteArray, len);
				htePublicKey = new String(byteArray).trim();
				sgx.en_get_hte_private_key(eid.getValue(), byteArray, len);
				htePrivateKey = new String(byteArray).trim();
				sgx.en_get_merchant_client_key(eid.getValue(), byteArray, len);
				merchantClientKey = new String(byteArray).trim();
				sgx.en_get_merchant_service_key(eid.getValue(), byteArray, len);
				merchantServiceKey = new String(byteArray).trim();
				long desTmp = sgx.en_destroy_enclave(eid.getValue());
				if(desTmp ==0) {
					System.out.println("Enclave closed.");
				}
			}
			HashMap<String, String> pspConfig = PSPConfig.getPspConfig(htePublicKey, htePrivateKey, merchantClientKey, merchantServiceKey);
			wpw.initProducer(pspConfig);
			updateFlow(JsonTags.FLOW, "Broadcasting...");
			this.broadcasting = true;
			wpw.startServiceBroadcast(0);
			updateFlow(JsonTags.BROADCAST_STATUS, "broadcasting");
		} catch (WPWithinGeneralException e) {
			updateFlow(JsonTags.FLOW, e.getMessage());
			e.printStackTrace();
		}
	}

	private EventListener wpWithinEventListener = new EventListener() {

		@Override
		public void onBeginServiceDelivery(int serviceID, int servicePriceID,
				WWServiceDeliveryToken wwServiceDeliveryToken, int unitsToSupply) throws WPWithinGeneralException {
			updateFlow(JsonTags.FLOW,
					"Service delivery phase: " + wpw.getDevice().getServices().get(serviceID).getName());
			updateFlow(JsonTags.DESCRIPTION, "Option to provide: "
					+ wpw.getDevice().getServices().get(serviceID).getPrices().get(servicePriceID).getDescription());
			updateFlow(JsonTags.UNITS, "UnitsToSupply: " + unitsToSupply);
			updateFlow(JsonTags.SERVICE_STATUS, "delivering");
		}

		@Override
		public void onEndServiceDelivery(int serviceID, WWServiceDeliveryToken wwServiceDeliveryToken,
				int unitsReceived) throws WPWithinGeneralException {

			updateFlow(JsonTags.FLOW, "Broadcasting...");
			updateFlow(JsonTags.SERVICE_STATUS, "standby");
			updateFlow(JsonTags.DESCRIPTION, "");
			updateFlow(JsonTags.UNITS, "");
		}

		@Override
		public void onMakePaymentEvent(int totalPrice, String orderCurrency, String clientToken,
				String orderDescription, String uuid) throws WPWithinGeneralException {

			updateFlow(JsonTags.FLOW, "Making payment...");
			updateFlow(JsonTags.DESCRIPTION, "Order description: " + orderDescription);
			updateFlow(JsonTags.UNITS, "Total price: " + ((float) totalPrice) / 100 + orderCurrency);

			TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			df.setTimeZone(tz);
			updateFlow(JsonTags.LAST_PAYMENT_RECEIVED_TIMESTAMP, df.format(new Date()));
		}

		@Override
		public void onErrorEvent(String msg) throws WPWithinGeneralException {

			updateFlow(JsonTags.FLOW, "Error occurred: " + msg);
		}

		@Override
		public void onServiceDiscoveryEvent(String remoteAddr) throws WPWithinGeneralException {
			updateFlow(JsonTags.FLOW, "Service query phase...");
			updateFlow(JsonTags.DESCRIPTION, "Connected client: " + remoteAddr);
			updateFlow(JsonTags.UNITS, "");

			TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			df.setTimeZone(tz);
			
			updateFlow(JsonTags.LAST_DISCOVERY_TIMESTAMP, df.format(new Date()));
		}

		@Override
		public void onServicePricesEvent(String remoteAddr, int serviceId) throws WPWithinGeneralException {

			updateFlow(JsonTags.FLOW, "Service negotiation...1/2");
			updateFlow(JsonTags.DESCRIPTION,
					"Service selected: " + wpw.getDevice().getServices().get(serviceId).getName());
			updateFlow(JsonTags.UNITS, "");

			TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			df.setTimeZone(tz);
			updateFlow(JsonTags.LAST_SERVICE_PRICES_TIMESTAMP, df.format(new Date()));
		}

		@Override
		public void onServiceTotalPriceEvent(String remoteAddr, int serviceId, WWTotalPriceResponse totalPriceResponse)
				throws WPWithinGeneralException {

			updateFlow(JsonTags.FLOW, "Service negotiation...2/2");
			updateFlow(JsonTags.DESCRIPTION, "Option selected: " + wpw.getDevice().getServices().get(serviceId)
					.getPrices().get(totalPriceResponse.getPriceId()).getDescription());
			updateFlow(JsonTags.UNITS, "");

			TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			df.setTimeZone(tz);
			updateFlow(JsonTags.LAST_SERVICE_TOTAL_PRICE_TIMESTAMP, df.format(new Date()));
		}
	};

	private static final Listener rpcAgentListener = new Listener() {
		@Override
		public void onApplicationExit(int exitCode, String stdOutput, String errOutput) {

			System.out.println("RPC Agent process did exit.");
			System.out.printf("ExitCode: %d", exitCode);
			System.out.printf("stdout: \n%s\n", stdOutput);
			System.out.printf("stderr: \n%s\n", errOutput);
		}
	};

	/**
	 * Loads config and path to logfile
	 */
	private static void loadConfig() {
		// define log file name for the rpc agent (based on the package name),
		// e.g. "rpc-within-consumerex.log";
		String[] splitedPkgName = Main.class.getPackage().getName().split("\\.");
		rpcLogFile = "rpc-within-" + splitedPkgName[splitedPkgName.length - 1] + ".log";
		Gson gson = new Gson();
		InputStream stream = Config.class.getResourceAsStream("/charger.json");
		String result = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));
		config = gson.fromJson(result, Config.class);
	}
}
