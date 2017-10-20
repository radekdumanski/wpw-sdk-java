package org.car.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
	private WWServiceMessage chargerDevice;
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
	private CarController carController = null;
	private String priceCurrency;
	private Config config;

	public CarController getCarController() {
		return carController;
	}

	public void setCarController(CarController carController) {
		this.carController = carController;
	}

	SmartCar(WPWithinWrapper wpw, Config config) {
		JSONObject obj = new JSONObject();
		this.wpw = wpw;
		this.chargeLevel = 99;
		this.config = config;
		updateFlow(obj, JsonTags.FLOW, "Smart Car Web service.");
		updateFlow(obj, JsonTags.BATTERY, String.valueOf(chargeLevel));
		this.jsonObject = obj;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public int getChargeLevel() {
		return chargeLevel;
	}

	public synchronized void setChargeLevel(int chargeLevel) {
		JSONObject obj = new JSONObject();
		updateFlow(obj, JsonTags.FLOW, "Battery level set to: " + chargeLevel + "%.");
		updateFlow(obj, JsonTags.BATTERY, String.valueOf(chargeLevel));
		this.jsonObject = obj;
		this.chargeLevel = chargeLevel;
	}

	public void setup(String name, String description) {
		wpw.setup(name, description);
		JSONObject obj = new JSONObject();
		updateFlow(obj, JsonTags.FLOW, "Charger plugged in.");
		updateFlow(obj, JsonTags.BATTERY, String.valueOf(chargeLevel));
		this.jsonObject = obj;
	}

	public void discoverDevices() {
		JSONObject obj = new JSONObject();

		updateFlow(obj, JsonTags.FLOW, "Device discovery phase.");
		updateFlow(obj, JsonTags.BATTERY, String.valueOf(chargeLevel));

		WWServiceMessage device = wpw.searchForDevice(10000, "Car charger");
		updateFlow(obj, JsonTags.DESCRIPTION, "Found device: " + device.getDeviceDescription());
		this.jsonObject = obj;
		this.chargerDevice = device;

	}

	public void connectToDevice() throws WPWithinGeneralException {
		JSONObject obj = new JSONObject();
		updateFlow(obj, JsonTags.FLOW, "Trying to establish connection...");
		updateFlow(obj, JsonTags.BATTERY, String.valueOf(chargeLevel));
		this.jsonObject = obj;
		// Will pick the first device discovered
		WWServiceMessage svcMsg = chargerDevice;
		wpw.initConsumer(svcMsg.getScheme(), svcMsg.getHostname(), svcMsg.getPortNumber(), svcMsg.getUrlPrefix(),
				wpw.getDevice().getUid(), config.getHceCard(), config.getPspConfig());
	}

	public void getAvailableServices() throws WPWithinGeneralException {
		JSONObject obj = new JSONObject();
		updateFlow(obj, JsonTags.FLOW, "Service query phase.");
		updateFlow(obj, JsonTags.BATTERY, String.valueOf(chargeLevel));
		this.servicesSet = wpw.requestServices();
		if (servicesSet != null && servicesSet.size() > 0) {
			Iterator<WWServiceDetails> svcIterator = servicesSet.iterator();
			while (svcIterator.hasNext()) {
				WWServiceDetails svc = svcIterator.next();
				updateFlow(obj, JsonTags.DESCRIPTION, "Found service: " + svc.getServiceDescription());
				this.jsonObject = obj;
			}
		}
	}

	public void selectChargingOption() {
		this.servicePrices = wpw.getServicePrices(this.serviceID);
		WWPrice price;
		JSONObject obj = new JSONObject();
		ChargingOptions chargeOption = null;
		updateFlow(obj, JsonTags.FLOW, "Found " + servicePrices.size() + " charging options for selected service");
		updateFlow(obj, JsonTags.BATTERY, String.valueOf(chargeLevel));
		this.jsonObject = obj;
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
					updateFlow(obj, JsonTags.FLOW, "Selected option: " + price.getDescription());
					updateFlow(obj, JsonTags.DESCRIPTION, price.getUnitDescription());
					updateFlow(obj, JsonTags.PRICE,
							"Price per kW: " +  ((float)price.getPricePerUnit().getAmount() / 100.0)
									+ price.getPricePerUnit().getCurrencyCode());


					this.jsonObject = obj;
					this.selectedServicePrice = price;
				}
			}
		}
	}

	public void getServicePriceQuote() throws WPWithinGeneralException {
		this.unitsToSupply = MAX_CHARGE - chargeLevel;
		WWTotalPriceResponse tpr = wpw.selectService(serviceID, unitsToSupply, selectedServicePrice.getId());
		JSONObject obj = new JSONObject();
		if (tpr != null) {
			updateFlow(obj, JsonTags.FLOW, "Price calculation phase.");
			updateFlow(obj, JsonTags.BATTERY, String.valueOf(chargeLevel));
			updateFlow(obj, JsonTags.UNITS, "Units to supply: " + tpr.getUnitsToSupply());
			updateFlow(obj, JsonTags.PRICE,
					"Total price: " +  ((float)tpr.getTotalPrice() / 100) + tpr.getCurrencyCode());
			
			this.jsonObject = obj;
			this.totalPriceResponse = tpr;
			this.priceCurrency = tpr.getCurrencyCode();
		} else {
			updateFlow(obj, JsonTags.FLOW, "Result of select service is null...");
			this.jsonObject = obj;
		}
	}

	public void purchaseService() throws WPWithinGeneralException {

		WWPaymentResponse pResp = wpw.makePayment(this.totalPriceResponse);
		JSONObject obj = new JSONObject();
		if (pResp != null) {
			updateFlow(obj, JsonTags.FLOW, "Payment phase.");
			updateFlow(obj, JsonTags.BATTERY, String.valueOf(chargeLevel));
			updateFlow(obj, JsonTags.PRICE, "Total paid: " +  ((float)pResp.getTotalPaid() / 100) + priceCurrency);
			
			this.jsonObject = obj;
			this.paymentResponse = pResp;
		} else {
			updateFlow(obj, JsonTags.FLOW, "Result of make payment is null...");
			this.jsonObject = obj;
		}
	}

	public void startCharging() throws WPWithinGeneralException {
		WWServiceDeliveryToken token = paymentResponse.getServiceDeliveryToken();
		///
		wpw.beginServiceDelivery(serviceID, token, unitsToSupply);
		try {
			JSONObject obj = new JSONObject();
			updateFlow(obj, JsonTags.FLOW, "Charging the car...");
			
			for (int i = 0; i <= unitsToSupply; i = i + 1) {
				updateFlow(obj, JsonTags.BATTERY, String.valueOf(chargeLevel + i));
				this.jsonObject = obj;
				Thread.sleep(1000);
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

	private void stopService(WWServiceDeliveryToken token) throws WPWithinGeneralException, InterruptedException {
		JSONObject obj = new JSONObject();
		updateFlow(obj, JsonTags.FLOW, "Car charged, waiting for input...");
		updateFlow(obj, JsonTags.BATTERY, "100");
		
		this.jsonObject = obj;
		wpw.endServiceDelivery(serviceID, token, unitsToSupply);
	}

	private void updateFlow(JSONObject obj, JsonTags tag, String msg) {
		obj.put(tag.getTag(), msg);
	}

	public synchronized boolean attachController(CarController carController) {
		if (this.carController == null) {
			this.carController = carController;
			return true;
		} else {
			return false;
		}
	}
	void SetJsonException(String msg) {
		JSONObject obj = new JSONObject();
		updateFlow(obj, JsonTags.FLOW, msg);
		this.jsonObject = obj;
	}
}
