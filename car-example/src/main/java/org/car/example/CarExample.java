package org.car.example;

import com.worldpay.innovation.wpwithin.PSPConfig;
import com.worldpay.innovation.wpwithin.WPWithinGeneralException;
import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;
import com.worldpay.innovation.wpwithin.types.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CarExample {

	private static WPWithinWrapper wpw;
	private static WWDevice wpwDevice;

	public static void main(String[] args) {

		System.out.println("Starting Smart Car Example Written in Java.");

		// define log file name for the rpc agent (based on the package name),
		// e.g. "rpc-within-consumerex.log";
		String[] splitedPkgName = CarExample.class.getPackage().getName().split("\\.");
		String rpcLogFile = "rpc-within-" + splitedPkgName[splitedPkgName.length - 1] + ".log";

		wpw = new WPWithinWrapperImpl("127.0.0.1", 10005, true, rpcAgentListener, rpcLogFile);
		SmartCar asd = new SmartCar(wpw);
		asd.setup("smart-car", "Smart car example.");
		// setchargeXXX
		asd.setChargeLevel(35);

		// flow ~plugin
		asd.discoverDevices();
		asd.connectToDevice();
		asd.getAvailableServices();
		asd.selectChargingService();
		asd.selectChargingOption();
		asd.getServicePriceQuote();
		asd.purchaseService();
		// start_service
		asd.startService();
		wpw.stopRPCAgent();

	}

	private static final Listener rpcAgentListener = new Listener() {
		@Override
		public void onApplicationExit(int exitCode, String stdOutput, String errOutput) {

			System.out.printf("RPC Agent process did exit.");
			System.out.printf("ExitCode: %d", exitCode);
			// System.out.printf("stdout: \n%s\n", stdOutput);
			// System.out.printf("stderr: \n%s\n", errOutput);
		};
	};
}
