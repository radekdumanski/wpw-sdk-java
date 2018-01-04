package org.car.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
		loadConfig("car-example.json");
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
	@SuppressWarnings("resource")
	private static void loadConfig(String fileName) {
		rpcLogFile = "rpc-within-car-example.log";
		Gson gson = new Gson();
		String jsonConfig = null;
		String configFilePath = System.getProperty("config");
		if (configFilePath == null) {
			try {
				jsonConfig = new BufferedReader(new FileReader(fileName)).lines()
						.collect(Collectors.joining("\n"));
				System.out.println("Loading config file: "+fileName+ " from current working directory.");
			} catch (FileNotFoundException e) {
				System.out.println("Loading default config from attached resources.");
				InputStream stream = Config.class.getResourceAsStream("/"+fileName);
				jsonConfig = new BufferedReader(new InputStreamReader(stream)).lines()
						.collect(Collectors.joining("\n"));
			}
		}
		else {
			try {
				System.out.println("Loading config file from: "+configFilePath);
				jsonConfig = new BufferedReader(new FileReader(configFilePath)).lines()
						.collect(Collectors.joining("\n"));
			} catch (FileNotFoundException e) {
				System.out.println("Config file was not found in: "+configFilePath);
				e.printStackTrace();
			}
		}
		config = gson.fromJson(jsonConfig, Config.class);
	}
	

}