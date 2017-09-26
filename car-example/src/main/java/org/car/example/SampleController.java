package org.car.example;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;

@Controller
public class SampleController {
	private WPWithinWrapper wpw;
	private int batteryLevel;
	private static SmartCar smartCar;
	private Thread plugin;

	public SampleController() {
		this.plugin = new Thread() {
			public void run() {
				try {
					wpw = new WPWithinWrapperImpl("127.0.0.1", 10005, true, rpcAgentListener, "car-example.log");
					smartCar = new SmartCar(wpw);
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
				} catch (InterruptedException v) {
					System.out.println(v);
				}
			}
		};
	}
	@RequestMapping("/setCharge")
	public @ResponseBody String setCharge(@RequestParam("data") int batteryLevel) {
		this.batteryLevel = batteryLevel;
		return "html/index";
	}

	@RequestMapping("/")
	String getIndex() {
		return "html/index";
	}

	@RequestMapping("/plugin")
	synchronized String plugin() throws InterruptedException {
		if(!plugin.isAlive()) {
			plugin.start();
		}
		return "html/index";
	}

	@RequestMapping(value = "/getStatus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getStatus() {
		return smartCar.getJsonObject().toJSONString();
	}

	private static final Listener rpcAgentListener = new Listener() {
		@Override
		public void onApplicationExit(int exitCode, String stdOutput, String errOutput) {

			System.out.printf("RPC Agent process did exit.\n\r");
			System.out.printf("ExitCode: %d\n\r", exitCode);
		}
	};
}