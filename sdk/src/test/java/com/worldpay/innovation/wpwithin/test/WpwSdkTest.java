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

import junit.framework.TestCase;

/**
 *
 * @author worldpay
 */
public class WpwSdkTest {
	private WPWithinWrapper wpw;

	private static String HOST = "127.0.0.1";
	private static int PORT = 9876;

	@Test(expected = WPWithinGeneralException.class)
	public void teExceptionTest() {
		this.wpw = new WPWithinWrapperImpl(HOST, PORT, false, null, "adsasdasd");
		wpw.stopRPCAgent();
	}

}