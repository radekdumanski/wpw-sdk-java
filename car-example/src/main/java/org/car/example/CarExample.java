package org.car.example;

import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;

public class CarExample {

	private static WPWithinWrapper wpw;
	private static int batteryLevel;
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Please specify car battery level.");
		} else if (args.length == 1) {
			try {
				batteryLevel = Integer.parseInt(args[0]);
				// define log file name for the rpc agent (based on the package name),
				// e.g. "rpc-within-consumerex.log";
				String[] splitedPkgName = CarExample.class.getPackage().getName().split("\\.");
				String rpcLogFile = "rpc-within-" + splitedPkgName[splitedPkgName.length - 1] + ".log";
				wpw = new WPWithinWrapperImpl("127.0.0.1", 10005, true, rpcAgentListener, rpcLogFile);
				SmartCar smartCar = new SmartCar(wpw);
				smartCar.setup("smart-car", "Smart car example.");
				// setchargeXXX
				Thread.sleep(8000);
				smartCar.setChargeLevel(batteryLevel);
				// flow ~plugin
				Thread.sleep(8000);
				smartCar.discoverDevices();
				
				Thread.sleep(5000);
				smartCar.connectToDevice();
				
				Thread.sleep(7000);
				smartCar.getAvailableServices();
				
				Thread.sleep(3000);
				smartCar.selectChargingService();
				
				Thread.sleep(10000);
				smartCar.selectChargingOption();
				

				Thread.sleep(3000);
				smartCar.getServicePriceQuote();
				Thread.sleep(7000);
				smartCar.purchaseService();
				
				// start_service
				Thread.sleep(9000);
				smartCar.startCharging();
			} catch (NumberFormatException e) {
				System.out.println("Please specify a valid integer in a range between 1 - 100.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			System.out.println("This program accepts only 1 argument - battery level.");
		}

	}

	private static final Listener rpcAgentListener = new Listener() {
		@Override
		public void onApplicationExit(int exitCode, String stdOutput, String errOutput) {

			System.out.printf("RPC Agent process did exit.\n\r");
			System.out.printf("ExitCode: %d\n\r", exitCode);
			// System.out.printf("stdout: \n%s\n", stdOutput);
			// System.out.printf("stderr: \n%s\n", errOutput);
		}
	};
}
