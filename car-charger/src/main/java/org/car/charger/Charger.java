package org.car.charger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
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

	public void setChargerJsonObject(JSONObject chargerJsonObject) {
		this.chargerJsonObject = chargerJsonObject;
	}

	private void updateFlow(JSONObject obj, JsonTags tag, String msg) {
		obj.put(tag.getTag(), msg);
	}

	public JSONObject getChargerJsonObject() {
		return this.chargerJsonObject;
	}

	public void run() throws Exception {
			JSONObject chargerObj = new JSONObject();
		try {
			
			updateFlow(chargerObj, JsonTags.FLOW, "Car charger example...");
			chargerJsonObject = chargerObj;
			loadConfig();
			wpw = new WPWithinWrapperImpl(config.getHost(), config.getPort(), true, wpWithinEventListener, config.getCallbackPort(), rpcAgentListener,
					rpcLogFile);
			wpw.setup(config.getDeviceName(), "Car charger device.", config.getInterfaceAddr());

			WWService svc = new WWService();
			svc.setName("Car charger");
			svc.setDescription("Hybrid / electric car charger.");
			svc.setId(1);
			ChargingServices chargingServices = new ChargingServices();
			svc.setPrices(chargingServices.getServicesMap());
			wpw.addService(svc);
			wpw.initProducer(config.getPspConfig());
			updateFlow(chargerObj, JsonTags.FLOW, "Broadcasting...");
			chargerJsonObject = chargerObj;
			wpw.startServiceBroadcast(0);

		} catch (WPWithinGeneralException e) {
			updateFlow(chargerObj, JsonTags.FLOW, e.getMessage());
			e.printStackTrace();
		}
	}

	private EventListener wpWithinEventListener = new EventListener() {

		@Override
		public void onBeginServiceDelivery(int serviceID, int servicePriceID,
				WWServiceDeliveryToken wwServiceDeliveryToken, int unitsToSupply) throws WPWithinGeneralException {
			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW,
					"Service delivery phase: " + wpw.getDevice().getServices().get(serviceID).getName());
			updateFlow(chargerObj, JsonTags.DESCRIPTION, "Option to provide: "
					+ wpw.getDevice().getServices().get(serviceID).getPrices().get(servicePriceID).getDescription());
			updateFlow(chargerObj, JsonTags.UNITS, "UnitsToSupply: " + unitsToSupply);
			setChargerJsonObject(chargerObj);
		}

		@Override
		public void onEndServiceDelivery(int serviceID, WWServiceDeliveryToken wwServiceDeliveryToken,
				int unitsReceived) throws WPWithinGeneralException {

			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Broadcasting...");
			chargerJsonObject = chargerObj;
		}

		@Override
		public void onMakePaymentEvent(int totalPrice, String orderCurrency, String clientToken,
				String orderDescription, String uuid) throws WPWithinGeneralException {

			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Making payment...");
			updateFlow(chargerObj, JsonTags.DESCRIPTION, "Order description: " + orderDescription);
			updateFlow(chargerObj, JsonTags.UNITS, "Total price: " + ((float) totalPrice) / 100 + orderCurrency);
			setChargerJsonObject(chargerObj);
		}

		@Override
		public void onErrorEvent(String msg) throws WPWithinGeneralException {

			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Error occurred: " + msg);
			setChargerJsonObject(chargerObj);

		}

		@Override
		public void onServiceDiscoveryEvent(String remoteAddr) throws WPWithinGeneralException {
			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Service query phase...");
			updateFlow(chargerObj, JsonTags.DESCRIPTION, "Connected client: " + remoteAddr);
			setChargerJsonObject(chargerObj);
		}

		@Override
		public void onServicePricesEvent(String remoteAddr, int serviceId) throws WPWithinGeneralException {

			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Service negotiation...1/2");
			updateFlow(chargerObj, JsonTags.DESCRIPTION,
					"Service selected: " + wpw.getDevice().getServices().get(serviceId).getName());
			setChargerJsonObject(chargerObj);
		}

		@Override
		public void onServiceTotalPriceEvent(String remoteAddr, int serviceId, WWTotalPriceResponse totalPriceResponse)
				throws WPWithinGeneralException {

			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Service negotiation...2/2");
			updateFlow(chargerObj, JsonTags.DESCRIPTION, "Option selected: " + wpw.getDevice().getServices()
					.get(serviceId).getPrices().get(totalPriceResponse.getPriceId()).getDescription());
			setChargerJsonObject(chargerObj);
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
