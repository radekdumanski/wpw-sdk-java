package org.car.example;

public enum JsonTags {
	FLOW ("flow"), DESCRIPTION ("description"), BATTERY ("battery"), UNITS ("units"), PRICE ("price"), TIMESTAMP("timestamp"), LAST_SEARCH_TIMESTAMP("lastSearchTimestamp");
	
	private final String tag;
	
	private JsonTags(String s) {
		this.tag = s;
	}
	
	public String getTag() {
		return this.tag;
	}
}
