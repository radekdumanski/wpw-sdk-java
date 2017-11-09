package org.car.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;

@Controller
public class SampleController {
	private static String rpcLogFile;
	private static Config config;
	private SmartCar smartCar;
	private WPWithinWrapper wpw;

	public SampleController() {
		loadConfig();
		this.wpw = new WPWithinWrapperImpl(config.getHost(), config.getPort(), true, rpcAgentListener, rpcLogFile);
		smartCar = new SmartCar(wpw, config);
	}

	@RequestMapping("/setCharge")
	@ResponseBody
	public String setCharge(@RequestParam("data") int batteryLevel) throws InterruptedException {
		smartCar.setChargeLevel(batteryLevel);
		return "Charge set to: "+batteryLevel;
	}

	@RequestMapping("/")
	String getSmartCarPage() {
		return "html/index";
	}

	@CrossOrigin
	@RequestMapping("/plugin")
	synchronized String plugin() throws ConflictException {
		new CarController(smartCar);
		return "html/index";
	}

	@CrossOrigin
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

	/**
	 * Loads config and path to logfile
	 */
	private static void loadConfig() {
		rpcLogFile = "rpc-within-car-example.log";
		Gson gson = new Gson();
		InputStream stream = Config.class.getResourceAsStream("/car-example.json");
		String result = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));
		config = gson.fromJson(result, Config.class);
	}

}