package org.car.charger;

import java.util.Base64;

import java.util.HashMap;
import java.util.Map;

import org.car.charger.JsonTags;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.worldpay.innovation.wpwithin.PSPConfig;
import com.worldpay.innovation.wpwithin.WPWithinGeneralException;
import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.eventlistener.EventListener;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;
import com.worldpay.innovation.wpwithin.types.WWPrice;
import com.worldpay.innovation.wpwithin.types.WWPricePerUnit;
import com.worldpay.innovation.wpwithin.types.WWService;
import com.worldpay.innovation.wpwithin.types.WWServiceDeliveryToken;
import com.worldpay.innovation.wpwithin.types.WWTotalPriceResponse;

public class Charger {
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

		try {

			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Car charger example...");
			chargerJsonObject = chargerObj;

			String[] splitedPkgName = Charger.class.getPackage().getName().split("\\.");
			String rpcLogFile = "rpc-within-" + splitedPkgName[splitedPkgName.length - 1] + ".log";

			wpw = new WPWithinWrapperImpl("127.0.0.1", 10000, true, wpWithinEventListener, 10002, rpcAgentListener,
					rpcLogFile);
			wpw.setup("Car charger", "Car charger device.");

			WWService svc = new WWService();
			svc.setName("Car charger");
			svc.setDescription("Hybrid / electric car charger.");
			svc.setId(1);
			ChargingServices chargingServices = new ChargingServices();
			svc.setPrices(chargingServices.getServicesMap());
			wpw.addService(svc);

			Map<String, String> pspConfig = new HashMap<>();

			// Worldpay Online Payments
			// test api keys
			// pspConfig.put(PSPConfig.HTE_PUBLIC_KEY,
			// "T_C_03eaa1d3-4642-4079-b030-b543ee04b5af");
			// pspConfig.put(PSPConfig.HTE_PRIVATE_KEY,
			// "T_S_f50ecb46-ca82-44a7-9c40-421818af5996");
			// pspConfig.put(PSPConfig.MERCHANT_CLIENT_KEY,
			// "T_C_03eaa1d3-4642-4079-b030-b543ee04b5af");
			// pspConfig.put(PSPConfig.MERCHANT_SERVICE_KEY,
			// "T_S_f50ecb46-ca82-44a7-9c40-421818af5996");
			// moro keys
			pspConfig.put(PSPConfig.PSP_NAME, PSPConfig.WORLDPAY_ONLINE_PAYMENTS);
			pspConfig.put(PSPConfig.HTE_PUBLIC_KEY, "T_C_baa89137-5b03-47da-bf3b-1abc0219a868");
			pspConfig.put(PSPConfig.HTE_PRIVATE_KEY, "T_S_95f6c457-daee-4169-b354-be738707681f");
			pspConfig.put(PSPConfig.API_ENDPOINT, "https://api.worldpay.com/v1");
			pspConfig.put(PSPConfig.MERCHANT_CLIENT_KEY, "T_C_baa89137-5b03-47da-bf3b-1abc0219a868");
			pspConfig.put(PSPConfig.MERCHANT_SERVICE_KEY, "T_S_95f6c457-daee-4169-b354-be738707681f");

			// Worldpay Total US / SecureNet
			// pspConfig.put(PSPConfig.PSP_NAME, PSPConfig.SECURE_NET);
			// pspConfig.put(PSPConfig.API_ENDPOINT,
			// "https://gwapi.demo.securenet.com/api");
			// pspConfig.put(PSPConfig.HTE_PUBLIC_KEY,
			// "8c0ce953-455d-4c12-8d14-ff20d565e485");
			// pspConfig.put(PSPConfig.HTE_PRIVATE_KEY, "KZ9kWv2EPy7M");
			// pspConfig.put(PSPConfig.DEVELOPER_ID, "12345678");
			// pspConfig.put(PSPConfig.APP_VERSION, "0.1");
			// pspConfig.put(PSPConfig.PUBLIC_KEY, "8c0ce953-455d-4c12-8d14-ff20d565e485");
			// pspConfig.put(PSPConfig.SECURE_KEY, "KZ9kWv2EPy7M");
			// pspConfig.put(PSPConfig.SECURE_NET_ID, "8008609");

			wpw.initProducer(pspConfig);
			updateFlow(chargerObj, JsonTags.FLOW, "Broadcasting...");
			chargerJsonObject = chargerObj;
			wpw.startServiceBroadcast(0);


		} catch (WPWithinGeneralException e) {

			e.printStackTrace();
		}
	}

	private  EventListener wpWithinEventListener = new EventListener() {

		@Override
		public void onBeginServiceDelivery(int serviceID, int servicePriceID,
				WWServiceDeliveryToken wwServiceDeliveryToken, int unitsToSupply) throws WPWithinGeneralException {
			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Starting service: "+wpw.getDevice().getServices().get(serviceID).getName());
			updateFlow(chargerObj, JsonTags.DESCRIPTION, "Service to provide: "+wpw.getDevice().getServices().get(serviceID).getPrices().get(servicePriceID).getDescription());
			updateFlow(chargerObj, JsonTags.UNITS, "UnitsToSupply: "+unitsToSupply);
			setChargerJsonObject(chargerObj);
		}

		@Override
		public void onEndServiceDelivery(int serviceID, WWServiceDeliveryToken wwServiceDeliveryToken,
				int unitsReceived) throws WPWithinGeneralException {

			try {

				System.out.printf("Charging completed - stopping service: %s\n",
						wpw.getDevice().getServices().get(serviceID).getName());
				System.out.printf("Units supplied: %d\n", unitsReceived);
				System.out.printf("SDT.Key: %s\n", wwServiceDeliveryToken.getKey());
				System.out.printf("SDT.Expiry: %s\n", wwServiceDeliveryToken.getExpiry());
				System.out.printf("SDT.Issued: %s\n", wwServiceDeliveryToken.getIssued());
				System.out.printf("SDT.Signature: %s\n",
						Base64.getEncoder().encodeToString(wwServiceDeliveryToken.getSignature()));
				System.out.printf("SDT.RefundOnExpiry: %b\n", wwServiceDeliveryToken.isRefundOnExpiry());
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		@Override
		public void onMakePaymentEvent(int totalPrice, String orderCurrency, String clientToken,
				String orderDescription, String uuid) throws WPWithinGeneralException {

			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Making payment...");
			updateFlow(chargerObj, JsonTags.DESCRIPTION, "Order description: "+orderDescription);
			updateFlow(chargerObj, JsonTags.UNITS, "Total price: "+totalPrice+orderCurrency);
			setChargerJsonObject(chargerObj);
		}

		@Override
		public void onErrorEvent(String msg) throws WPWithinGeneralException {
			
			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Error occurred: "+msg);
			setChargerJsonObject(chargerObj);

		}

		@Override
		public void onServiceDiscoveryEvent(String remoteAddr) throws WPWithinGeneralException {
			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Client connected from: "+remoteAddr);
			setChargerJsonObject(chargerObj);
		}

		@Override
		public void onServicePricesEvent(String remoteAddr, int serviceId) throws WPWithinGeneralException {
			
			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Service negotiation...1/2"); 
			updateFlow(chargerObj, JsonTags.DESCRIPTION, "Service selected: "+wpw.getDevice().getServices().get(serviceId).getName());
			setChargerJsonObject(chargerObj);
		}

		@Override
		public void onServiceTotalPriceEvent(String remoteAddr, int serviceId, WWTotalPriceResponse totalPriceResponse)
				throws WPWithinGeneralException {
			
			JSONObject chargerObj = new JSONObject();
			updateFlow(chargerObj, JsonTags.FLOW, "Service negotiation...2/2"); 
			updateFlow(chargerObj, JsonTags.DESCRIPTION, "Option selected: "+wpw.getDevice().getServices().get(serviceId).getPrices().get(totalPriceResponse.getPriceId()));
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
}
