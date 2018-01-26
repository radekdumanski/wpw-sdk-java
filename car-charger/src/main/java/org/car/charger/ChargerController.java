package org.car.charger;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChargerController {

	private Charger charger;
	public ChargerController() throws Exception {
		this.charger = new Charger();
		this.charger.run();
	}

	@RequestMapping("/")
	public String getProducerPage() {
		return "html/index";
	}
	
	@CrossOrigin
	@RequestMapping(value = "/getChargerStatus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getChargerStatus() {
		return this.charger.getChargerJsonObject().toJSONString();
	}

	@CrossOrigin
	@RequestMapping(value = "/startBroadcasting", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String startBroadcasting() {
		return this.charger.startBroadcasting();
	}

	@CrossOrigin
	@RequestMapping(value = "/stopBroadcasting", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String stopBroadcasting() {
		return this.charger.stopBroadcasting();
	}
}
