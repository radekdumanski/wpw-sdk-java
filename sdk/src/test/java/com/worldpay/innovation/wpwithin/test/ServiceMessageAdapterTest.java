package com.worldpay.innovation.wpwithin.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.worldpay.innovation.wpwithin.rpc.types.ServiceMessage;
import com.worldpay.innovation.wpwithin.thriftadapter.ServiceMessageAdapter;
import com.worldpay.innovation.wpwithin.types.WWServiceMessage;

public class ServiceMessageAdapterTest {
	private ServiceMessage serviceMessage;
	private Set<ServiceMessage> serviceMessages = new HashSet<>();
	private static String deviceDescription = "deviceDescription";
	private static String deviceName = "deviceName";
	private static String hostname = "hostname";
	private static int portNumber = 123;
	private static String scheme = "scheme";
	private static String serverId = "serverId";
	private static String urlPrefix = "urlPrefix";
	private Set<String> serviceTypes = new HashSet<>(2);
	
	public ServiceMessageAdapterTest() {
		serviceTypes.add("type1");
		serviceTypes.add("type2");
		
		serviceMessage = new ServiceMessage();
		serviceMessage.setDeviceDescription(deviceDescription);
		serviceMessage.setDeviceName(deviceName);
		serviceMessage.setHostname(hostname);
		serviceMessage.setPortNumber(portNumber);
		serviceMessage.setScheme(scheme);
		serviceMessage.setServerId(serverId);
		serviceMessage.setServiceTypes(serviceTypes);
		serviceMessage.setUrlPrefix(urlPrefix);
		serviceMessages.add(serviceMessage);
	}
	
	@Test
	public void convertServiceMessageTest() {
		WWServiceMessage wwServiceMessage = ServiceMessageAdapter.convertServiceMessage(serviceMessage);
		
		assertEquals(deviceDescription, wwServiceMessage.getDeviceDescription());
		assertEquals(deviceName, wwServiceMessage.getDeviceName());
		assertEquals(hostname, wwServiceMessage.getHostname());
		assertEquals(portNumber, wwServiceMessage.getPortNumber());
		assertEquals(scheme, wwServiceMessage.getScheme());
		assertEquals(serverId, wwServiceMessage.getServerId());
		assertEquals(serviceTypes, wwServiceMessage.getServiceTypes());
		assertEquals(urlPrefix, wwServiceMessage.getUrlPrefix());
	}
	
	@Test
	public void convertServiceMessagesTest() {
		
		Set<WWServiceMessage> wwServiceMessages = ServiceMessageAdapter.convertServiceMessages(serviceMessages);
		
		assertEquals(deviceDescription, wwServiceMessages.iterator().next().getDeviceDescription());
		assertEquals(deviceName, wwServiceMessages.iterator().next().getDeviceName());
		assertEquals(hostname, wwServiceMessages.iterator().next().getHostname());
		assertEquals(portNumber, wwServiceMessages.iterator().next().getPortNumber());
		assertEquals(scheme, wwServiceMessages.iterator().next().getScheme());
		assertEquals(serverId, wwServiceMessages.iterator().next().getServerId());
		assertEquals(serviceTypes, wwServiceMessages.iterator().next().getServiceTypes());
		assertEquals(urlPrefix, wwServiceMessages.iterator().next().getUrlPrefix());
	}
}
