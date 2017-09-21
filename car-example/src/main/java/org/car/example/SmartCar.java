package org.car.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.worldpay.innovation.wpwithin.PSPConfig;
import com.worldpay.innovation.wpwithin.WPWithinGeneralException;
import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.types.WWHCECard;
import com.worldpay.innovation.wpwithin.types.WWPaymentResponse;
import com.worldpay.innovation.wpwithin.types.WWPrice;
import com.worldpay.innovation.wpwithin.types.WWServiceDeliveryToken;
import com.worldpay.innovation.wpwithin.types.WWServiceDetails;
import com.worldpay.innovation.wpwithin.types.WWServiceMessage;
import com.worldpay.innovation.wpwithin.types.WWTotalPriceResponse;

public class SmartCar {

	private WPWithinWrapper wpw;
	private int chargeLevel;
	private Set<WWServiceMessage> devicesSet;
	private Set<WWServiceDetails> servicesSet;
	private WWServiceDetails chargingService;
	private Set<WWPrice> servicePrices;
	private WWPrice selectedServicePrice;
	private Integer serviceID;
	private WWTotalPriceResponse totalPriceResponse;
	private WWPaymentResponse paymentResponse;
	private int unitsToSupply;
	private static int MAX_CHARGE = 100;
	private JSONObject jsonObject;
	private JSONArray jsonArray;

	public SmartCar(WPWithinWrapper wpw) {
		this.wpw = wpw;
		this.chargeLevel = 100;
	}

	public int getChargeLevel() {
		return chargeLevel;
	}

	public void setChargeLevel(int chargeLevel) {
		System.err.println("Battery level set to: " + chargeLevel + "%.");
		updateFlow("Battery level set to: " + chargeLevel + "%.");
		this.chargeLevel = chargeLevel;
	}

	public void setup(String name, String description) {
		wpw.setup(name, description);
	}

	public void discoverDevices() {
		Set<WWServiceMessage> devices = wpw.deviceDiscovery(10000);
		if (devices.size() > 0) {

			System.out.printf("%d device(s) found:\n", devices.size());

			if (devices.iterator().hasNext()) {

				WWServiceMessage svcMsg = devices.iterator().next();

				System.out.printf("Device Description: %s\n", svcMsg.getDeviceDescription());
				System.out.printf("Hostname: %s\n", svcMsg.getHostname());
				System.out.printf("Port: %d\n", svcMsg.getPortNumber());
				System.out.printf("URL Prefix: %s\n", svcMsg.getUrlPrefix());
				System.out.printf("ServerId: %s\n", svcMsg.getServerId());
				System.out.printf("Scheme: %s\n", svcMsg.getScheme());

				System.out.println("--------");
			}
			this.devicesSet = devices;
		} else {

			System.out.println("No services found..");
		}

	}

	public void connectToDevice() throws WPWithinGeneralException {

		if (devicesSet != null && devicesSet.iterator().hasNext()) {
			// Will pick the first device discovered
			WWServiceMessage svcMsg = devicesSet.iterator().next();
			WWHCECard card = new WWHCECard();

			card.setFirstName("Bilbo");
			card.setLastName("Baggins");
			card.setCardNumber("5555555555554444");
			card.setExpMonth(11);
			card.setExpYear(2018);
			card.setType("Card");
			card.setCvc("113");

			Map<String, String> pspConfig = new HashMap<>();

			// Worldpay Online Payments
			pspConfig.put(PSPConfig.PSP_NAME, PSPConfig.WORLDPAY_ONLINE_PAYMENTS);
			pspConfig.put(PSPConfig.API_ENDPOINT, "https://api.worldpay.com/v1");

			// Worldpay Total US / SecureNet
			// pspConfig.put(PSPConfig.PSP_NAME, PSPConfig.SECURE_NET);
			// pspConfig.put(PSPConfig.API_ENDPOINT,
			// "https://gwapi.demo.securenet.com/api");
			// pspConfig.put(PSPConfig.APP_VERSION, "0.1");
			// pspConfig.put(PSPConfig.DEVELOPER_ID, "12345678");

			wpw.initConsumer(svcMsg.getScheme(), svcMsg.getHostname(), svcMsg.getPortNumber(), svcMsg.getUrlPrefix(),
					wpw.getDevice().getUid(), card, pspConfig);
		}
	}

	public void getAvailableServices() throws WPWithinGeneralException {

		this.servicesSet = wpw.requestServices();

		System.out.printf("%d service(s) found\n", servicesSet.size());

		if (servicesSet != null && servicesSet.size() > 0) {

			Iterator<WWServiceDetails> svcIterator = servicesSet.iterator();

			while (svcIterator.hasNext()) {

				WWServiceDetails svc = svcIterator.next();

				System.out.println("Service:");
				System.out.printf("Id: %d\n", svc.getServiceId());
				System.out.printf("Description: %s\n", svc.getServiceDescription());
				System.out.println("------");
			}
		}
	}

	public void selectChargingOption() {
		this.servicePrices = wpw.getServicePrices(this.serviceID);
		WWPrice price;
		ChargingOptions chargeOption = null;
		System.out.printf("%d options found for service id %d\n", servicePrices.size(), serviceID);

		if (servicePrices != null && servicePrices.size() > 0) {
			Iterator<WWPrice> priceIterator = servicePrices.iterator();
			if (chargeLevel < 70) {
				chargeOption = ChargingOptions.SUPER;
			} else if (69 < chargeLevel && chargeLevel < 90) {
				chargeOption = ChargingOptions.MEDIUM;
			} else if (89 < chargeLevel) {
				chargeOption = ChargingOptions.SLOW;
			}
			while (priceIterator.hasNext()) {
				price = priceIterator.next();
				if (price.getDescription().equals(chargeOption.name())) {
					System.out.println("This is the selected charging option:");
					System.out.printf("Id: %d\n", price.getId());
					System.out.printf("Description: %s\n", price.getDescription());
					System.out.printf("UnitId: %d\n", price.getUnitId());
					System.out.printf("UnitDescription: %s\n", price.getUnitDescription());
					System.out.printf("Unit Price Amount: %d\n", price.getPricePerUnit().getAmount());
					System.out.printf("Unit Price CurrencyCode: %s\n", price.getPricePerUnit().getCurrencyCode());
					System.out.println("------");
					this.selectedServicePrice = price;
				}
			}
		}
	}

	public void getServicePriceQuote() throws WPWithinGeneralException {
		this.unitsToSupply = MAX_CHARGE - chargeLevel;
		WWTotalPriceResponse tpr = wpw.selectService(serviceID, unitsToSupply, selectedServicePrice.getId());

		if (tpr != null) {

			System.out.println("Did retrieve price quote:");
			System.out.printf("Merchant client key: %s\n", tpr.getMerchantClientKey());
			System.out.printf("Payment reference id: %s\n", tpr.getPaymentReferenceId());
			System.out.printf("Units to supply: %d\n", tpr.getUnitsToSupply());
			System.out.printf("Currency code: %s\n", tpr.getCurrencyCode());
			System.out.printf("Total price: %d\n", tpr.getTotalPrice());
			this.totalPriceResponse = tpr;
		} else {

			System.out.println("Result of select service is null..");
		}
	}

	public void purchaseService() throws WPWithinGeneralException {

		WWPaymentResponse pResp = wpw.makePayment(this.totalPriceResponse);

		if (pResp != null) {

			System.out.printf("Payment response: ");
			System.out.printf("Total paid: %d\n", pResp.getTotalPaid());
			System.out.printf("ServiceDeliveryToken.issued: %s\n", pResp.getServiceDeliveryToken().getIssued());
			System.out.printf("ServiceDeliveryToken.expiry: %s\n", pResp.getServiceDeliveryToken().getExpiry());
			System.out.printf("ServiceDeliveryToken.key: %s\n", pResp.getServiceDeliveryToken().getKey());
			System.out.printf("ServiceDeliveryToken.signature: %s\n",
					Base64.getEncoder().encodeToString(pResp.getServiceDeliveryToken().getSignature()));
			System.out.printf("ServiceDeliveryToken.refundOnExpiry: %b\n",
					pResp.getServiceDeliveryToken().isRefundOnExpiry());
			this.paymentResponse = pResp;
		} else {

			System.out.println("Result of make payment is null..");
		}
	}

	public void startCharging() throws WPWithinGeneralException {
		System.out.println("Service started - releasing charge.");
		WWServiceDeliveryToken token = paymentResponse.getServiceDeliveryToken();
		wpw.beginServiceDelivery(serviceID, token, unitsToSupply);
		try {
			System.out.println("Charging the car...");
			for (int i = 0; i <= unitsToSupply; i++) {
				Thread.sleep(150);
				System.out.printf("Charging progress: %d/%d\r", i, unitsToSupply);
			}
			stopService(token);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectChargingService() {
		if (servicesSet != null && servicesSet.iterator().hasNext()) {
			this.chargingService = servicesSet.iterator().next();
			this.serviceID = chargingService.getServiceId();
		}
	}
	
	private void stopService(WWServiceDeliveryToken token) throws WPWithinGeneralException {
		System.out.printf("Service stopped - car charged.");
		wpw.endServiceDelivery(serviceID, token, unitsToSupply);
		wpw.stopRPCAgent();
	}
	
	private void updateFlow(String msg) {
		JSONObject obj = new JSONObject();
		obj.put("flow", msg);
		
		try (FileWriter file = new FileWriter("flow.json")) {
			file.write(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
