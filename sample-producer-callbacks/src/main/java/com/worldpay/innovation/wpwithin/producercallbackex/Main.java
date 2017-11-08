package com.worldpay.innovation.wpwithin.producercallbackex;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.worldpay.innovation.wpwithin.PSPConfig;
import com.worldpay.innovation.wpwithin.WPWithinGeneralException;
import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.eventlistener.EventListener;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;
import com.worldpay.innovation.wpwithin.types.WWPrice;
import com.worldpay.innovation.wpwithin.types.WWPricePerUnit;
import com.worldpay.innovation.wpwithin.types.WWService;
import com.worldpay.innovation.wpwithin.types.WWServiceDeliveryToken;
import com.worldpay.innovation.wpwithin.types.WWTotalPriceResponse;

public class Main {
	private static Config config;
	private static String rpcLogFile;
	
	public static void main(String[] args) {

		try {

			System.out.println("WorldpayWithin Sample Producer...");
			loadConfig();
			WPWithinWrapper wpw = new WPWithinWrapperImpl(config.getHost(), config.getPort(), true, wpWithinEventListener, config.getCallbackPort(),
					rpcAgentListener, rpcLogFile);

			wpw.setup("Producer Example", "Example WorldpayWithin producer");

			WWService svc = new WWService();
			svc.setName("Car charger");
			svc.setDescription("Can charge your hybrid / electric car");
			svc.setId(1);

			WWPrice ccPrice = new WWPrice();
			ccPrice.setId(1);
			ccPrice.setDescription("Kilowatt-hour");
			ccPrice.setUnitDescription("One kilowatt-hour");
			ccPrice.setUnitId(1);
			WWPricePerUnit ppu = new WWPricePerUnit();
			ppu.setAmount(250);
			ppu.setCurrencyCode("GBP");
			ccPrice.setPricePerUnit(ppu);

			HashMap<Integer, WWPrice> prices = new HashMap<>(1);
			prices.put(ccPrice.getId(), ccPrice);

			svc.setPrices(prices);

			wpw.addService(svc);

			wpw.initProducer(config.getPspConfig());

			wpw.startServiceBroadcast(1000 * 9999);

		} catch (WPWithinGeneralException e) {

			e.printStackTrace();
		}
	}

	private static EventListener wpWithinEventListener = new EventListener() {

		@Override
		public void onBeginServiceDelivery(int serviceID, int servicePriceID,
				WWServiceDeliveryToken wwServiceDeliveryToken, int unitsToSupply) throws WPWithinGeneralException {

			try {
				System.out.println("event from core - onBeginServiceDelivery()");
				System.out.printf("ServiceID: %d\n", serviceID);
				System.out.printf("UnitsToSupply: %d\n", unitsToSupply);
				System.out.printf("SDT.Key: %s\n", wwServiceDeliveryToken.getKey());
				System.out.printf("SDT.Expiry: %s\n", wwServiceDeliveryToken.getExpiry());
				System.out.printf("SDT.Issued: %s\n", wwServiceDeliveryToken.getIssued());
				System.out.printf("SDT.Signature: %s\n", wwServiceDeliveryToken.getSignature());
				System.out.printf("SDT.RefundOnExpiry: %b\n", wwServiceDeliveryToken.isRefundOnExpiry());
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		@Override
		public void onEndServiceDelivery(int serviceID, WWServiceDeliveryToken wwServiceDeliveryToken,
				int unitsReceived) throws WPWithinGeneralException {

			try {

				System.out.println("event from core - onEndServiceDelivery()");
				System.out.printf("ServiceID: %d\n", serviceID);
				System.out.printf("UnitsReceived: %d\n", unitsReceived);
				System.out.printf("SDT.Key: %s\n", wwServiceDeliveryToken.getKey());
				System.out.printf("SDT.Expiry: %s\n", wwServiceDeliveryToken.getExpiry());
				System.out.printf("SDT.Issued: %s\n", wwServiceDeliveryToken.getIssued());
				System.out.printf("SDT.Signature: %s\n", wwServiceDeliveryToken.getSignature());
				System.out.printf("SDT.RefundOnExpiry: %b\n", wwServiceDeliveryToken.isRefundOnExpiry());
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDiscoveryEvent(String remoteAddr) throws WPWithinGeneralException {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServicePricesEvent(String remoteAddr, int serviceId) throws WPWithinGeneralException {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceTotalPriceEvent(String remoteAddr, int serviceId, WWTotalPriceResponse totalPriceResponse)
				throws WPWithinGeneralException {
			// TODO Auto-generated method stub

		}

		@Override
		public void onMakePaymentEvent(int totalPrice, String orderCurrency, String clientToken,
				String orderDescription, String uuid) throws WPWithinGeneralException {
			// TODO Auto-generated method stub

		}

		@Override
		public void onErrorEvent(String msg) throws WPWithinGeneralException {
			// TODO Auto-generated method stub

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
		// define log file name for the rpc agent (based on the package name),
		// e.g. "rpc-within-consumerex.log";
		String[] splitedPkgName = Main.class.getPackage().getName().split("\\.");
		rpcLogFile = "rpc-within-" + splitedPkgName[splitedPkgName.length - 1] + ".log";
		Gson gson = new Gson();
		InputStream stream = Config.class.getResourceAsStream("/sample-producer-callbacks.json");
		String result = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));
		config = gson.fromJson(result, Config.class);
	}
}
