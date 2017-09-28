package org.car.example;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;

@Controller
public class SampleController {
	private SmartCar smartCar;
	private WPWithinWrapper wpw;

	public SampleController() {
		this.wpw = new WPWithinWrapperImpl("127.0.0.1", 10005, true, rpcAgentListener, "car-example.log");
		smartCar = new SmartCar(wpw);
	}

	@CrossOrigin
	@RequestMapping("/setCharge")
	public String setCharge(@RequestParam("data") int batteryLevel) throws InterruptedException {
		// setchargeXXX
		smartCar.setChargeLevel(batteryLevel);
		return "html/index";
	}

	@RequestMapping("/")
	String getIndex() {
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

}