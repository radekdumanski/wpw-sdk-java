/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worldpay.innovation.wpwithin.test;



import org.junit.Test;

import com.worldpay.innovation.wpwithin.WPWithinGeneralException;
import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;

/**
 *
 * @author worldpay
 */
public class WpwSdkTest {
	private WPWithinWrapper wpw;

	private static String HOST = "127.0.0.1";
	private static int PORT = 9876;
	private static String LOGFILE = "logfile";

	@Test(expected = WPWithinGeneralException.class)
	public void stopRPCAgentTest() {
		this.wpw = new WPWithinWrapperImpl(HOST, PORT, false, null, LOGFILE);
		wpw.stopRPCAgent();
	}
	
	@Test(expected = WPWithinGeneralException.class)
	public void startServiceBroadcastTest() {
		this.wpw = new WPWithinWrapperImpl(HOST, PORT, false, null, LOGFILE);
		wpw.startServiceBroadcast(10);
	}
	
	@Test(expected = WPWithinGeneralException.class)
	public void stopServiceBroadcastTest() {
		this.wpw = new WPWithinWrapperImpl(HOST, PORT, false, null, LOGFILE);
		wpw.stopServiceBroadcast();
	}
	
	@Test(expected = WPWithinGeneralException.class)
	public void searchForDeviceTest() {
		this.wpw = new WPWithinWrapperImpl(HOST, PORT, false, null, LOGFILE);
		wpw.searchForDevice(1000, "device");
	}
	
	@Test(expected = WPWithinGeneralException.class)
	public void setup() {
		this.wpw = new WPWithinWrapperImpl(HOST, PORT, false, null, LOGFILE);
		wpw.setup("my", "device", null);
	}
}