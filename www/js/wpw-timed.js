var car = "http://10.0.0.3:8000";
var charger = "http://10.0.0.2:8008";
var broadcasting = true;

var oldStatus = '';
var o = document.getElementById("anim");
var a1,a2,a3,a4,a5,a6,a7;
var A = [];
var svg = null;
var prevCarStatus = null;
var prevChargerStatus = null;

function toggleBroadcast(){
	if(broadcasting){
		$.getJSON(charger+"/stopBroadcasting", function(data, testStatus, jqXHR){});
	}else{
		$.getJSON(charger+"/startBroadcasting", function(data, testStatus, jqXHR){});
	}
}
function plugItIn(){
	// CORS disallowed...
	//$.getJSON(car+"/setCharge?data=90", function(data, testStatus, jqXHR){});
	$.getJSON(car+"/plugin", function(data, testStatus, jqXHR){});
}

function refreshStatus() {
	processCarJSON(car+"/getStatus");
	processChargerJSON(charger+"/getChargerStatus");
}
function processCarJSON(jsonPath) {
	if(svg === null) {
		svg = o.contentDocument;
	} // FIXME: svg might still be unavailable yet
	$.getJSON(jsonPath, function(data, testStatus, jqXHR) {
		if(JSON.stringify(data) !== prevCarStatus){
			//console.log("Car status has changed", data);
			prevCarStatus = JSON.stringify(data);
		}
		/*if(data.hasOwnProperty('flow')){
			if(data['flow'] !== oldStatus) {
				oldStatus = data['flow'];
			}
			if(svg !== null){
				setChargerOpacity(svg, data);
			}
		}else{
			console.log("Cannot read flow status");
		}*/
		if(svg !== null){
			setCarDisplay(svg, data);
		}
	});
}
function processChargerJSON(jsonPath) {
	if(svg === null) {
		svg = o.contentDocument;
	} // FIXME: svg might still be unavailable yet
	$.getJSON(jsonPath, function(data, testStatus, jqXHR) {
		if(JSON.stringify(data) !== prevChargerStatus){
			//console.log("Charger status has changed", data);
			prevChargerStatus = JSON.stringify(data);
		}
		/*if(data.hasOwnProperty('flow')){
			if(data['flow'] !== oldStatus) {
				oldStatus = data['flow'];
			}
			if(svg !== null){
				setChargerOpacity(svg, data);
			}
		}else{
			console.log("Cannot read flow status");
		}*/
		if(svg !== null){
			setChargerDisplay(svg, data);
		}
	});
}
function setArrow(arrow, style, name){
	switch(style){
		case "on":
			arrow.children[0].style.stroke = "rgb(0, 255, 0)";
			arrow.children[0].style.fill = "rgba(0, 255, 0, 0.5)";
			arrow.children[1].style.stroke = "rgb(0, 255, 0)";
			arrow.style.opacity = 1;
			//arrow.style.animation="pulse";
			arrow.style.animation="blink";
			arrow.style.animationDuration = "1s";
			arrow.style.animationIterationCount="infinite";
			//arrow.style.transformOrigin = "center";
			break;
		case "off":
			arrow.children[0].style.stroke = "rgb(0, 0, 0)";
			arrow.children[0].style.fill = "rgba(0, 0, 0, 0.2)";
			arrow.children[1].style.stroke = "rgb(0, 0, 0)";
			arrow.style.opacity = 0.2;
			//arrow.style.animation="pulse";
			arrow.style.animation="blink";
			arrow.style.animationIterationCount="0";
			//arrow.style.transformOrigin = "center";
			//arrow.style.animation="";
			break;
		case "err":
			arrow.children[0].style.stroke = "rgb(255, 0, 0)";
			arrow.children[0].style.fill = "rgba(255, 0, 0, 0.5)";
			arrow.children[1].style.stroke = "rgb(255, 0, 0)";
			arrow.style.opacity = 1;
			arrow.style.animation="";
			break;
		case "none":
			arrow.children[0].style.stroke = "rgb(0, 0, 127)";
			arrow.children[0].style.fill = "rgba(0, 0, 0, 0.5)";
			arrow.children[1].style.stroke = "rgb(0, 0, 127)";
			arrow.style.opacity = 0.2;
			arrow.style.animation="";
			break;
		default:
			arrow.children[0].style.stroke = "rgb(255, 255, 0)";
			arrow.children[0].style.fill = "rgba(255, 255, 0, 0.5)";
			arrow.children[1].style.stroke = "rgb(255, 255, 0)";
			arrow.style.opacity = 1;
			arrow.style.animation="";
			break;
	}
}

var lastDiscoveryTimestamp = null;
var lastPaymentReceivedTimestamp = null;
var lastServicePricesTimestamp = null;
var lastServiceTotalPriceTimestamp = null;
var lastSearchTimestamp = null;

function setCarDisplay(svg, data){
	if(a2 === undefined){
		a2 = svg.getElementById("Search");
	}
	if(a7 === undefined){
		a7 = svg.getElementById("batt");
		a7.style.textShadow = "1px 1px 1px black";
	}
	/*
	if(data.hasOwnProperty("timestamp")){
		var genTs = data["timestamp"];
		if(data.hasOwnProperty('lastSearchTimestamp')){
			prop = data['lastSearchTimestamp'];
			updateTimestampArrow(a2, prop, genTs);
			lastSearchTimestamp = prop;
		}
	}
	*/
	if(data.hasOwnProperty('battery')){
		a7.textContent = "batt: "+data['battery']+"%";
	}
	updateConsumerState(data);

}
function setArrowState(obj, state){
}
function updateTimestampArrow(obj, ts, genTS){
	var timeoutMs = 2550*2;  // how long it should last
	var diff = (new Date(genTS)) - (new Date(ts));
	//console.log(">>",obj.id,diff,timeoutMs);
	if(diff <= 0) { // newer than generation ts = invalid state ?
		color = 255;
		opacity = 1;
	}else if(diff >= timeoutMs){
		color = 0;
		opacity = 0.2;
		//console.log(obj.id, "long time ago", diff, ts, new Date());
	}else{
		color = Math.round(255 - ( 255.0 * diff*diff / (timeoutMs*timeoutMs) ));
		opacity = 0.2 + 0.8 * (color / 255);
		//console.log(">>>", obj.id, color, diff);
	}
	//console.log(obj.id, color);
	obj.style.opacity = opacity;
	obj.children[0].style.stroke = "rgb(0,"+color.toString()+",0)";
	obj.children[0].style.fill = "rgba(0,"+color.toString()+",0,0.5)";
	obj.children[1].style.stroke = "rgb(0,"+color.toString()+",0)";
}
function updateProducerState(data){
	if(data.hasOwnProperty("timestamp")){
		var genTs = data['timestamp'];
		if(data.hasOwnProperty('lastPaymentReceivedTimestamp')){
			prop = data['lastPaymentReceivedTimestamp'];
			updateTimestampArrow(a5, prop, genTs);
			lastPaymentReceivedTimestamp = prop;
		}
	}
}
function updateConsumerState(data){
	switch (data['flow']) {
	case "Smart Car Web service.":  // default
		setArrow(a1,"off","");
		setArrow(a2,"off","");
		setArrow(a3,"off","");
		setArrow(a4,"off","");
		break;
	case "Charger plugged in.":  // after setup
		setArrow(a1,"off","");
		setArrow(a2,"off","");
		setArrow(a3,"off","");
		setArrow(a4,"off","");
		break;
	case "Device discovery phase.":  // before .searchForDevice
		setArrow(a1,"off","");
		setArrow(a2,"on","");
		setArrow(a3,"off","");
		setArrow(a4,"off","");
		break;
	case "Trying to establish connection...":  // before .initConsumer
		setArrow(a1,"off","");
		setArrow(a2,"on","");
		setArrow(a3,"off","");
		setArrow(a4,"off","");
		break;
	case "Service query phase.":  // before .requestServices
		setArrow(a1,"off","");
		setArrow(a2,"on","");
		setArrow(a3,"off","");
		setArrow(a4,"off","");
		break;
	// after .getServicePrices - not strightforward
	case "Price calculation phase.":  // after .selectService
		setArrow(a1,"off","");
		setArrow(a2,"off","");
		setArrow(a3,"on","");
		setArrow(a4,"off","");
		break;
	case "Payment phase.":  // after .makePayment
		setArrow(a1,"off","");
		setArrow(a2,"off","");
		setArrow(a3,"off","");
		setArrow(a4,"on","");
		break;
	case "Charging the car...":  // after .getServiceDeliveryToken
		setArrow(a1,"on","");
		setArrow(a2,"off","");
		setArrow(a3,"off","");
		setArrow(a4,"off","");
		break;
	case "Car charged, waiting for input...":
		setArrow(a1,"off","");
		setArrow(a2,"off","");
		setArrow(a3,"off","");
		setArrow(a4,"off","");
		break;
	default:
		switch (data['description']){
			case "Selecting service...":
			setArrow(a1,"off","");
			setArrow(a2,"off","");
			setArrow(a3,"on","");
			setArrow(a4,"off","");
				break;
			default:
				break;
		}
		// Battery set to #
		// Found # services
		// Selected option: ...
		// "Result of make payment is null..."
		break;
	}
}
function setChargerDisplay(svg, data){
	if(svg === null){
		return;
	}
	if(a1 === undefined){
		a1 = svg.getElementById("ChargeCar");
		a2 = svg.getElementById("Search");
		a3 = svg.getElementById("Discover");
		a4 = svg.getElementById("Pay");
		a5 = svg.getElementById("Earn");
		a6 = svg.getElementById("Broadcast");
		a7 = svg.getElementById("batt");
		console.log(a1,a2,a3,a4,a5,a6);
		if(a1 === null){
			return;
		}
		A=[a1,a2,a3,a4,a5,a6];
		A.forEach(function(g){
			g.style.transition="opacity 1s ease-in-out, stroke 1s ease-in-out, fill 1s ease-in-out";
			g.children[0].style.stroke = "rgba(0,0,0)";
			g.children[0].style.fill = "rgba(127,127,127,0.5)";
			g.children[1].style.stroke = "rgba0,0,0)";
			g.style.opacity = 0.2;
		});
		a6.onclick = toggleBroadcast;
		a2.onclick = plugItIn;
		a7.textContent = "";
		a7.style.textShadow = "1px 1px 1px black";
	}
	updateProducerState(data);
	if(data.hasOwnProperty('broadcast')){
		prop = data['broadcast'];
		if(prop === 'broadcasting'){
			setArrow(a6, "on", "broadcast");
			broadcasting = true;
		}else if(prop === 'off'){
			setArrow(a6, "off", "broadcast");
			broadcasting = false;
		}else{
			setArrow(a6, "err", "broadcast");
			broadcasting = false;
		}
	}else{
		setArrow(a6, "none", "broadcast");
	}


	/*
	if(data.hasOwnProperty('service')){
		prop = data['service'];
		if(prop === 'delivering'){
			setArrow(a1, "on", "service");
		}else if(prop === 'standby'){
			setArrow(a1, "off", "service");
		}else{
			setArrow(a1, "err", "service");
		}
	}else{
		setArrow(a1, "none", "service");
	}
	if(data.hasOwnProperty("timestamp")){
		var genTs = data["timestamp"];
		if(data.hasOwnProperty('lastDiscoveryTimestamp')){
			prop = data['lastDiscoveryTimestamp'];
			updateTimestampArrow(a3, prop, genTs);
			lastDiscoveryTimestamp = prop;
		}
		if(data.hasOwnProperty('lastPaymentReceivedTimestamp')){
			prop = data['lastPaymentReceivedTimestamp'];
			updateTimestampArrow(a5, prop, genTs);
			lastPaymentReceivedTimestamp = prop;
		}

		// Which one to use?
		//if(data.hasOwnProperty('lastServicePricesTimestamp')){
		//	prop = data['lastServicePricesTimestamp'];
		//	updateTimestampArrow(a3, lastServicePricesTimestamp, prop);
		//	lastServicePricesTimestamp = prop;
		//}
		if(data.hasOwnProperty('lastServiceTotalPriceTimestamp')){
			prop = data['lastServiceTotalPriceTimestamp'];
			updateTimestampArrow(a4, prop, genTs);
			lastServiceTotalPriceTimestamp = prop;
		}
	}*/
}

$.ajaxSetup({'cache':true, timeout:300});
//refreshStatus();
var stepTimer = setInterval(refreshStatus, 750);
/*
charger.broadcasting -> A6
car.charging -> A1
? pay A4
car.search A2
car.selecting A3
charger.paymentReceived -> A5
*/
