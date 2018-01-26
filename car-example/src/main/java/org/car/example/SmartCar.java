package org.car.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

import org.json.simple.JSONObject;

import com.worldpay.innovation.wpwithin.WPWithinGeneralException;
import com.worldpay.innovation.wpwithin.WPWithinWrapper;
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
		this.wpw = wpw;
		this.chargeLevel = 99;
		this.config = config;
		this.jsonObject = new JSONObject(); // Simple JSONObject is causing type safety warnings
		updateFlow(JsonTags.FLOW, "Smart Car Web service.");
		updateFlow(JsonTags.BATTERY, String.valueOf(chargeLevel));
		updateFlow(JsonTags.DESCRIPTION, "standby");
		updateFlow(JsonTags.PRICE, null);
		updateFlow(JsonTags.UNITS, null);
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJsonObject() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(tz);
		jsonObject.put(JsonTags.TIMESTAMP.getTag(), df.format(new Date()));
		return jsonObject;
	}

	public int getChargeLevel() {
		return chargeLevel;
	}

	public synchronized void setChargeLevel(int chargeLevel) {
		updateFlow(JsonTags.FLOW, "Battery level set to: " + chargeLevel + "%.");
		updateFlow(JsonTags.BATTERY, String.valueOf(chargeLevel));
		this.chargeLevel = chargeLevel;
	}

	public void setup(String name, String description, String interfaceAddr) {
		wpw.setup(name, description, interfaceAddr);
		updateFlow(JsonTags.FLOW, "Charger plugged in.");
		updateFlow(JsonTags.BATTERY, String.valueOf(chargeLevel));
		updateFlow(JsonTags.DESCRIPTION, "Plugging-in");
		updateFlow(JsonTags.PRICE, null);
		updateFlow(JsonTags.UNITS, null);
	}

	public void discoverDevices() {
		String producerName = "Car charger";
		updateFlow(JsonTags.FLOW, "Device discovery phase.");
		updateFlow(JsonTags.BATTERY, String.valueOf(chargeLevel));
		updateFlow(JsonTags.DESCRIPTION, "Searching for devices");
		updateFlow(JsonTags.PRICE, null);
		updateFlow(JsonTags.UNITS, null);

		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(tz);
		updateFlow(JsonTags.LAST_SEARCH_TIMESTAMP, df.format(new Date()));

		WWServiceMessage device = wpw.searchForDevice(2000, producerName);
		if (device.getDeviceName().isEmpty()) {
			throw new WPWithinGeneralException(producerName + " producer device was not found.");
		}
		updateFlow(JsonTags.DESCRIPTION, "Found device: " + device.getDeviceDescription());
		this.chargerDevice = device;
	}

	public void connectToDevice() throws WPWithinGeneralException {
		updateFlow(JsonTags.FLOW, "Trying to establish connection...");
		updateFlow(JsonTags.BATTERY, String.valueOf(chargeLevel));
		updateFlow(JsonTags.DESCRIPTION, "Connecting...");
		updateFlow(JsonTags.PRICE, null);
		updateFlow(JsonTags.UNITS, null);
		// Will pick the first device discovered
		WWServiceMessage svcMsg = chargerDevice;
		wpw.initConsumer(svcMsg.getScheme(), svcMsg.getHostname(), svcMsg.getPortNumber(), svcMsg.getUrlPrefix(),
				wpw.getDevice().getUid(), config.getHceCard(), config.getPspConfig());
	}

	public void getAvailableServices() throws WPWithinGeneralException {
		updateFlow(JsonTags.FLOW, "Service query phase.");
		updateFlow(JsonTags.BATTERY, String.valueOf(chargeLevel));
		updateFlow(JsonTags.DESCRIPTION, "Querying services...");
		updateFlow(JsonTags.PRICE, null);
		updateFlow(JsonTags.UNITS, null);
		this.servicesSet = wpw.requestServices();
		if (servicesSet != null && servicesSet.size() > 0) {
			Iterator<WWServiceDetails> svcIterator = servicesSet.iterator();
			while (svcIterator.hasNext()) {
				WWServiceDetails svc = svcIterator.next();
				updateFlow(JsonTags.DESCRIPTION, "Found service: " + svc.getServiceDescription());
			}
		}
	}

	public void selectChargingOption() {
		this.servicePrices = wpw.getServicePrices(this.serviceID);
		WWPrice price;
		ChargingOptions chargeOption = null;
		updateFlow(JsonTags.FLOW, "Found " + servicePrices.size() + " charging options for selected service");
		updateFlow(JsonTags.BATTERY, String.valueOf(chargeLevel));
		updateFlow(JsonTags.DESCRIPTION, "Selecting service...");
		updateFlow(JsonTags.PRICE, null);
		updateFlow(JsonTags.UNITS, null);
		try {
			Thread.sleep(500);
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
					updateFlow(JsonTags.FLOW, "Selected option: " + price.getDescription());
					updateFlow(JsonTags.DESCRIPTION, price.getUnitDescription());
					updateFlow(JsonTags.PRICE, "Price per kW: " + ((float) price.getPricePerUnit().getAmount() / 100.0)
							+ price.getPricePerUnit().getCurrencyCode());
					this.selectedServicePrice = price;
				}
			}
		}
	}

	public void getServicePriceQuote() throws WPWithinGeneralException {
		this.unitsToSupply = MAX_CHARGE - chargeLevel;
		WWTotalPriceResponse tpr = wpw.selectService(serviceID, unitsToSupply, selectedServicePrice.getId());
		if (tpr != null) {
			updateFlow(JsonTags.FLOW, "Price calculation phase.");
			updateFlow(JsonTags.BATTERY, String.valueOf(chargeLevel));
			updateFlow(JsonTags.UNITS, "Units to supply: " + tpr.getUnitsToSupply());
			updateFlow(JsonTags.PRICE, "Total price: " + ((float) tpr.getTotalPrice() / 100) + tpr.getCurrencyCode());
			updateFlow(JsonTags.DESCRIPTION, "Order summary...");

			this.totalPriceResponse = tpr;
			this.priceCurrency = tpr.getCurrencyCode();
		} else {
			updateFlow(JsonTags.FLOW, "Result of select service is null...");
		}
	}

	public void purchaseService() throws WPWithinGeneralException {

		WWPaymentResponse pResp = wpw.makePayment(this.totalPriceResponse);
		if (pResp != null) {
			updateFlow(JsonTags.FLOW, "Payment phase.");
			updateFlow(JsonTags.BATTERY, String.valueOf(chargeLevel));
			updateFlow(JsonTags.PRICE, "Total paid: " + ((float) pResp.getTotalPaid() / 100) + priceCurrency);
			updateFlow(JsonTags.DESCRIPTION, "Purchasing...");

			this.paymentResponse = pResp;
		} else {
			updateFlow(JsonTags.FLOW, "Result of make payment is null...");
		}
	}

	public void startCharging() throws WPWithinGeneralException {
		WWServiceDeliveryToken token = paymentResponse.getServiceDeliveryToken();
		wpw.beginServiceDelivery(serviceID, token, unitsToSupply);
		try {
			updateFlow(JsonTags.FLOW, "Charging the car...");
			updateFlow(JsonTags.DESCRIPTION, "Charging...");
			updateFlow(JsonTags.PRICE, null);
			updateFlow(JsonTags.UNITS, null);

			for (int i = 0; i <= unitsToSupply; i = i + 1) {
				updateFlow(JsonTags.BATTERY, String.valueOf(chargeLevel + i));
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
		updateFlow(JsonTags.FLOW, "Car charged, waiting for input...");
		updateFlow(JsonTags.BATTERY, "100");
		updateFlow(JsonTags.DESCRIPTION, "standby");
		updateFlow(JsonTags.PRICE, null);
		updateFlow(JsonTags.UNITS, null);
		wpw.endServiceDelivery(serviceID, token, unitsToSupply);
	}

	@SuppressWarnings("unchecked")
	private void updateFlow(JsonTags tag, String msg) {
		this.jsonObject.put(tag.getTag(), msg);
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
		updateFlow(JsonTags.FLOW, msg);
		updateFlow(JsonTags.DESCRIPTION, "Error");
		updateFlow(JsonTags.PRICE, null);
		updateFlow(JsonTags.UNITS, null);
	}
}
