package org.car.charger;

public enum JsonTags {
	FLOW ("flow"), DESCRIPTION ("description"), BATTERY ("battery"), UNITS ("units"), PRICE ("price"),
	SERVICE_STATUS("service"),
	BROADCAST_STATUS("broadcast"),
	LAST_PAYMENT_RECEIVED_TIMESTAMP("lastPaymentReceivedTimestamp"),
	LAST_DISCOVERY_TIMESTAMP("lastDiscoveryTimestamp"),
	LAST_SERVICE_PRICES_TIMESTAMP("lastServicePricesTimestamp"),
	LAST_SERVICE_TOTAL_PRICE_TIMESTAMP("lastServiceTotalPriceTimestamp"),
	TIMESTAMP("timestamp");
	
	private final String tag;
	
	private JsonTags(String s) {
		this.tag = s;
	}
	
	public String getTag() {
		return this.tag;
	}
}
