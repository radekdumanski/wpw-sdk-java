package com.worldpay.innovation.wpwithin.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.junit.Before;
//import org.junit.Rule;
//import org.testng.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import com.worldpay.innovation.wpwithin.WPWithinGeneralException;
import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.eventlistener.EventListener;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;
import com.worldpay.innovation.wpwithin.types.WWPaymentResponse;
import com.worldpay.innovation.wpwithin.types.WWPrice;
import com.worldpay.innovation.wpwithin.types.WWPricePerUnit;
import com.worldpay.innovation.wpwithin.types.WWService;
import com.worldpay.innovation.wpwithin.types.WWServiceDeliveryToken;
import com.worldpay.innovation.wpwithin.types.WWServiceDetails;
import com.worldpay.innovation.wpwithin.types.WWServiceMessage;
import com.worldpay.innovation.wpwithin.types.WWTotalPriceResponse;

import junit.framework.AssertionFailedError;
import net.jodah.concurrentunit.Waiter;

public class E2ETest {
	private static Config config;
	private static String rpcLogFile;
	private WPWithinWrapper wpw;
	private UncaughtExceptionHandler eh;
	private static Waiter waiter;

//	 @Before
//	 public void setExceptionHandler() {
//	 eh = new UncaughtExceptionHandler() {
//	
//	 @Override
//	 public void uncaughtException(Thread t, Throwable e) {
//		 waiter.assertNull(e);
//	 }
//	 };
//	 }

	private void setupProducer() {
		waiter = new Waiter();
		loadConfig();
		this.wpw = new WPWithinWrapperImpl(config.getHost(), config.getPort(), true, wpWithinEventListener,
				config.getCallbackPort(), rpcAgentListener, "e2etest.log");

		wpw.setup(config.getProducerName(), config.getProducerDescription(), config.getInterfaceAddr());
		WWService svc = new WWService();
		svc.setName(config.getServiceName());
		svc.setDescription(config.getServiceDescription());
		svc.setId(config.getServiceID());
		svc.setServiceType(config.getServiceType());

		WWPrice ccPrice = new WWPrice();
		ccPrice.setId(config.getPriceID());
		ccPrice.setDescription(config.getPriceDescription());
		ccPrice.setUnitDescription(config.getUnitDescription());
		ccPrice.setUnitId(config.getUnitID());
		WWPricePerUnit ppu = new WWPricePerUnit();
		ppu.setAmount(config.getUnitPriceAmount());
		ppu.setCurrencyCode(config.getUnitPriceCurrency());
		ccPrice.setPricePerUnit(ppu);

		HashMap<Integer, WWPrice> prices = new HashMap<>(1);
		prices.put(ccPrice.getId(), ccPrice);

		svc.setPrices(prices);

		wpw.addService(svc);

		wpw.initProducer(config.getPspConfig());

		wpw.startServiceBroadcast(0);
	}

	@Test
	public void testProducerConsumerFlow() {
		Thread.setDefaultUncaughtExceptionHandler(eh);
		setupProducer();

		// consumer
		wpw.setup("consumerTest", "consumer test device", config.getInterfaceAddr());
		String deviceUUID = wpw.getDevice().getUid();
		WWServiceMessage device = wpw.searchForDevice(7500, config.getProducerName());
		// device discovery
		assertNotNull("Desired device was not found.", device);
		assertEquals(config.getProducerName(), device.getDeviceName());
		assertEquals(config.getProducerDescription(), device.getDeviceDescription());
		try {
			assertEquals(Inet4Address.getLocalHost().getHostAddress(), device.getHostname());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		assertEquals(64521, device.getPortNumber());
		assertEquals(config.getServiceType(), device.getServiceTypes().iterator().next());
		// init consumer
		wpw.initConsumer(device.getScheme(), device.getHostname(), device.getPortNumber(), device.getUrlPrefix(),
				device.getServerId(), config.getHceCard(), config.getPspConfig());
		// request services
		Set<WWServiceDetails> services = wpw.requestServices();
		Iterator<WWServiceDetails> svcIterator = services.iterator();
		WWServiceDetails svc = svcIterator.next();
		assertEquals(config.getServiceID(), svc.getServiceId());
		assertEquals(config.getServiceName(), svc.getServiceName());
		assertEquals(config.getServiceDescription(), svc.getServiceDescription());
		assertEquals(config.getServiceType(), svc.getServiceType());
		// get service prices
		Set<WWPrice> servicePrices = wpw.getServicePrices(svc.getServiceId());
		WWPrice servicePrice = servicePrices.iterator().next();
		assertEquals(config.getPriceID(), servicePrice.getId());
		assertEquals(config.getPriceDescription(), servicePrice.getDescription());
		assertEquals(config.getUnitID(), servicePrice.getUnitId());
		assertEquals(config.getUnitDescription(), servicePrice.getUnitDescription());
		assertEquals(config.getUnitPriceAmount(), servicePrice.getPricePerUnit().getAmount());
		assertEquals(config.getUnitPriceCurrency(), servicePrice.getPricePerUnit().getCurrencyCode());

		// get service prices quote
		WWTotalPriceResponse selectedService = wpw.selectService(svc.getServiceId(), config.getUnitsToSupply(),
				servicePrice.getId());
		assertNotNull(selectedService.getClientId());
		assertNotNull(selectedService.getServerId());
		assertNotNull(selectedService.getPaymentReferenceId());
		assertEquals(config.getPriceID(), selectedService.getPriceId());
		assertEquals(config.getUnitsToSupply(), selectedService.getUnitsToSupply());
		assertEquals(config.getUnitsToSupply() * config.getUnitPriceAmount(), selectedService.getTotalPrice());
		assertEquals(config.getPspConfig().get("merchant_client_key"), selectedService.getMerchantClientKey());
		assertEquals(config.getUnitPriceCurrency(), selectedService.getCurrencyCode());

		// make payment
		WWPaymentResponse payment = wpw.makePayment(selectedService);
		WWServiceDeliveryToken token = payment.getServiceDeliveryToken();
		assertNotNull(payment.getClientId());
		assertNotNull(payment.getServerId());
		assertNotNull(token.getKey());
		assertNotNull(token.getIssued());
		assertNotNull(token.getExpiry());
		assertNotNull(token.isRefundOnExpiry());
		// TODO not yet implemented in go
		// assertNotNull(payment.getServiceDeliveryToken().getSignature());
		assertEquals(config.getUnitsToSupply() * config.getUnitPriceAmount(), payment.getTotalPaid());

		// begin service delivery
		WWServiceDeliveryToken asd = wpw.beginServiceDelivery(config.getServiceID(), token, config.getUnitsToOrder());
		wpw.endServiceDelivery(config.getServiceID(), asd, config.getUnitsToOrder());
		try {
			waiter.await(20000, 6);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static EventListener wpWithinEventListener = new EventListener() {

		@Override
		public void onBeginServiceDelivery(int serviceID, int servicePriceID,
				WWServiceDeliveryToken wwServiceDeliveryToken, int unitsToSupply) throws WPWithinGeneralException {
			waiter.assertEquals(config.getServiceID(), serviceID);
			waiter.assertEquals(config.getPriceID(), servicePriceID);
			waiter.assertEquals(config.getUnitsToOrder(), unitsToSupply);
			waiter.assertNotNull(wwServiceDeliveryToken);
			waiter.resume();
		}

		@Override
		public void onEndServiceDelivery(int serviceID, WWServiceDeliveryToken wwServiceDeliveryToken,
				int unitsReceived) throws WPWithinGeneralException {
			waiter.assertEquals(config.getServiceID(), serviceID);
			waiter.assertEquals(config.getUnitsToOrder(), unitsReceived);
			waiter.assertNotNull(wwServiceDeliveryToken);
			waiter.resume();
		}

		@Override
		public void onServiceDiscoveryEvent(String remoteAddr) throws WPWithinGeneralException {
			waiter.assertNotNull(remoteAddr);
			waiter.resume();
		}

		@Override
		public void onServicePricesEvent(String remoteAddr, int serviceID) throws WPWithinGeneralException {
			waiter.assertEquals(config.getServiceID(), serviceID);
			waiter.assertNotNull(remoteAddr);
			waiter.resume();
		}

		@Override
		public void onServiceTotalPriceEvent(String remoteAddr, int serviceID, WWTotalPriceResponse totalPriceResponse)
				throws WPWithinGeneralException {
			waiter.assertEquals(config.getServiceID(), serviceID);
			waiter.assertNotNull(remoteAddr);
			waiter.assertNotNull(totalPriceResponse);
			waiter.resume();

		}

		@Override
		public void onMakePaymentEvent(int totalPrice, String orderCurrency, String clientToken,
				String orderDescription, String uuid) throws WPWithinGeneralException {
			DecimalFormat formatter = new DecimalFormat("0.00");
			String orderDsc = config.getServiceName() + " - " + config.getUnitsToSupply() + " units @ "
					+ config.getUnitPriceCurrency() + " " + formatter.format(config.getUnitPriceAmount() / 100)
					+ " per unit.";
			waiter.assertNotNull(uuid);
			waiter.assertEquals(config.getUnitsToSupply() * config.getUnitPriceAmount(), totalPrice);
			waiter.assertEquals(config.getUnitPriceCurrency(), orderCurrency);
			waiter.assertEquals(orderDsc, orderDescription);
			waiter.assertNotNull(clientToken);
			waiter.resume();

		}

		@Override
		public void onErrorEvent(String msg) throws WPWithinGeneralException {
			waiter.assertNotNull(msg);
			waiter.resume();
		}
	};
	private static final Listener rpcAgentListener = new Listener() {
		@Override
		public void onApplicationExit(int exitCode, String stdOutput, String errOutput) {

			System.out.printf("RPC Agent process did exit.");
			System.out.printf("ExitCode: %d", exitCode);
			System.out.printf("stdout: \n%s\n", stdOutput);
			System.out.printf("stderr: \n%s\n", errOutput);
		}
	};

	/**
	 * Loads config and path to logfile
	 */
	private static void loadConfig() {
		Gson gson = new Gson();
		InputStream stream = Config.class.getResourceAsStream("/testcfg.json");
		String result = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));
		config = gson.fromJson(result, Config.class);
	}
}
