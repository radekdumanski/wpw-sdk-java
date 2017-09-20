package org.car.charger;

import java.util.HashMap;

import com.worldpay.innovation.wpwithin.types.WWPrice;
import com.worldpay.innovation.wpwithin.types.WWPricePerUnit;

public class ChargingServices extends WWPrice {

	private HashMap<Integer, WWPrice> servicesMap;

	public ChargingServices() {
		this.servicesMap = new HashMap<>();
		registerService(1, ChargingOptions.SUPER, 100, CurrencyCode.GBP, 1, "Charging up to 20kW per hour");
		registerService(2, ChargingOptions.MEDIUM, 70, CurrencyCode.GBP, 1, "Charging up to 7.2kW per hour.");
		registerService(3, ChargingOptions.SLOW, 50, CurrencyCode.GBP, 1, "Charging up to 3.6kW per hour.");
	}

	private void registerService(int id, ChargingOptions description, int pricePerUnit, CurrencyCode currencyCode, int unitID,
			String unitDescription) {
		WWPrice wwPrice = new WWPrice();
		wwPrice.setId(id);
		wwPrice.setDescription(description.name());
		wwPrice.setUnitDescription(unitDescription);
		wwPrice.setUnitId(unitID);
		WWPricePerUnit wwPricePerUnit = new WWPricePerUnit();
		wwPricePerUnit.setAmount(pricePerUnit);
		wwPricePerUnit.setCurrencyCode(currencyCode.name());
		wwPrice.setPricePerUnit(wwPricePerUnit);
		servicesMap.put(wwPrice.getId(), wwPrice);
	}

	public HashMap<Integer, WWPrice> getServicesMap() {
		return servicesMap;
	}
}
