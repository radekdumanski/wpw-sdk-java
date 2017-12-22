package com.worldpay.innovation.wpwithin.test;

import java.util.Map;

import com.worldpay.innovation.wpwithin.types.WWHCECard;

public class Config {
	private int port;
	private int callbackPort;
	private String host;
	private String interfaceAddr;
	private Map<String, String> pspConfig;
	private WWHCECard hceCard;
	private String producerName;
	private String producerDescription;
	private int serviceID;
	private String serviceName;
	private String serviceDescription;
	private String serviceType;
	private int priceID;
	private String priceDescription;
	private int unitID;
	private String unitDescription;
	private int unitPriceAmount;
	private String unitPriceCurrency;
	private int unitsToSupply;
	private int unitsToOrder;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getCallbackPort() {
		return callbackPort;
	}

	public void setCallbackPort(int callbackPort) {
		this.callbackPort = callbackPort;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getInterfaceAddr() {
		return interfaceAddr;
	}

	public void setInterfaceAddr(String interfaceAddr) {
		this.interfaceAddr = interfaceAddr;
	}

	public Map<String, String> getPspConfig() {
		return pspConfig;
	}

	public void setPspConfig(Map<String, String> pspConfig) {
		this.pspConfig = pspConfig;
	}

	public String getProducerDescription() {
		return producerDescription;
	}

	public void setProducerDescription(String producerDescription) {
		this.producerDescription = producerDescription;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public int getPriceID() {
		return priceID;
	}

	public void setPriceID(int priceID) {
		this.priceID = priceID;
	}

	public String getPriceDescription() {
		return priceDescription;
	}

	public void setPriceDescription(String priceDescription) {
		this.priceDescription = priceDescription;
	}

	public int getUnitID() {
		return unitID;
	}

	public void setUnitID(int unitID) {
		this.unitID = unitID;
	}

	public String getUnitDescription() {
		return unitDescription;
	}

	public void setUnitDescription(String unitDescription) {
		this.unitDescription = unitDescription;
	}

	public int getUnitPriceAmount() {
		return unitPriceAmount;
	}

	public void setUnitPriceAmount(int unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}

	public String getUnitPriceCurrency() {
		return unitPriceCurrency;
	}

	public void setUnitPriceCurrency(String unitPriceCurrency) {
		this.unitPriceCurrency = unitPriceCurrency;
	}

	public int getUnitsToSupply() {
		return unitsToSupply;
	}

	public void setUnitsToSupply(int unitsToSupply) {
		this.unitsToSupply = unitsToSupply;
	}

	public int getUnitsToOrder() {
		return unitsToOrder;
	}

	public void setUnitsToOrder(int unitsToOrder) {
		this.unitsToOrder = unitsToOrder;
	}

	public WWHCECard getHceCard() {
		return hceCard;
	}

	public void setHceCard(WWHCECard hceCard) {
		this.hceCard = hceCard;
	}
}
