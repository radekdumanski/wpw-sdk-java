// var car = "http://10.0.0.3:8000";
// var charger = "http://10.0.0.2:8008";

var car = "http://127.0.0.1:8000";
var charger = "http://127.0.0.1:8008";

// var car = "http://192.168.1.25:8000";
// var charger = "http://192.168.1.25:8008";

var broadcasting = true;

//var ACTIVATE_COLOR = "rgb(240,30,20)";

var COLOR_VALUE = "48,184,138";
var ACTIVATE_COLOR = "rgb(" + COLOR_VALUE + ")";

//var ACTIVATE_COLOR = "rgb(220,220,220)";
var DEACTIVATE_COLOR = "rgb(220,220,220)";
var ERROR_COLOR = "rgb(255, 255, 0)";
var MAX_OPACITY = 0.9;
var MIN_OPACITY = 0.4;


var oldStatus = '';
var o = document.getElementById("anim");
var a1,a2,a3,a4,a5,a6,a7;
var A = [];
var svg = null;
var prevCarStatus = null;
var prevChargerStatus = null;


// Arrow-click functions
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
		if(svg === null){
			return
		}
	} // FIXME: svg might still be unavailable yet
	$.getJSON(jsonPath, function(data, testStatus, jqXHR) {
		if(JSON.stringify(data) !== prevCarStatus){
			prevCarStatus = JSON.stringify(data);
		}
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
			prevChargerStatus = JSON.stringify(data);
		}
		if(svg !== null){
			setChargerDisplay(svg, data);
		}
	});
}

function setArrow(arrow, style, name){
	return;
	switch(style){
		case "on":

			var s = arrow.getAttribute("state");
			if(s === "on"){
				//console.log("Already on");
			}else{
				
				arrow.setAttribute("state","on");
				arrow.children[0].style.stroke = "rgb(0, 255, 0)";
				//arrow.children[0].style.fill = "rgba(0, 255, 0, 0.5)";
				arrow.children[1].style.stroke = "rgb(0, 255, 0)";
				//arrow.children[1].style.fill = "rgb(0, 255, 0, 0.5)";
				arrow.style.opacity = 1;
				
			}
			break;
		case "off":
			arrow.setAttribute("state","off");
			arrow.children[0].style.stroke = "rgb(0, 0, 0)";
			//arrow.children[0].style.fill = "rgba(0, 0, 0, 0.2)";
			arrow.children[1].style.stroke = "rgb(0, 0, 0)";
			arrow.style.opacity = 0.2;
			//arrow.style.animation="blink";
			//arrow.style.animationIterationCount="0";
			break;
		default:
			arrow.setAttribute("state","off");
			arrow.children[0].style.stroke = "rgb(255, 255, 0)";
			//arrow.children[0].style.fill = "rgba(255, 255, 0, 0.5)";
			arrow.children[1].style.stroke = "rgb(255, 255, 0)";
			arrow.style.opacity = 1;
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
	}
	if(data.hasOwnProperty('battery')){
		a7.textContent = data['battery']+"%";
	}
	updateConsumerState(data);
}

function updateTimestampArrow(obj, ts, genTS){
	var timeoutMs = 2550*2;  // how long it should last
	var diff = (new Date(genTS)) - (new Date(ts));
	if(diff <= 0) { // newer than generation ts = invalid state ?
		color = 255;
		opacity = 1;
	}else if(diff >= timeoutMs){
		color = 0;
		//opacity = 0.2;
		opacity = MIN_OPACITY;
		//console.log(obj.id, "long time ago", diff, ts, new Date());
	}else{
		color = Math.round(255 - ( 255.0 * diff*diff / (timeoutMs*timeoutMs) ));
		opacity = MIN_OPACITY + (1 - MIN_OPACITY) * (color / 255);
		//console.log(">>>", obj.id, color, diff);
	}

	// obj.style.opacity = opacity;
	// obj.children[0].style.stroke = "rgb(0,"+color.toString()+",0)";
	// //obj.children[0].style.fill = "rgba(0,"+color.toString()+",0,0.5)";
	// obj.children[1].style.stroke = "rgb(0,"+color.toString()+",0)";
	
	//var c = "rgb(0," + color.toString() + ",0)";
	var c = "rgb(" + color.toString() + ",0,0)";
	paintEarn("off", c, opacity);
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
		paintCharge("off");
		paintSearch("off");
		paintDiscover("off");
		paintPay("off");
		break;
	case "Charger plugged in.":  // after setup
		paintCharge("off");
		paintSearch("off");
		paintDiscover("off");
		paintPay("off");
		break;
	case "Device discovery phase.":  // before .searchForDevice
		paintCharge("off");
		paintSearch("on");
		paintDiscover("off");
		paintPay("off");
		break;
	case "Trying to establish connection...":  // before .initConsumer
		paintCharge("off");
		paintSearch("on");
		paintDiscover("on");
		paintPay("off");
		break;
	case "Service query phase.":  // before .requestServices
		paintCharge("off");
		paintSearch("off");
		paintDiscover("on");
		paintPay("off");
		break;
	// after .getServicePrices - not strightforward
	case "Price calculation phase.":  // after .selectService
		paintCharge("off");
		paintSearch("off");
		paintDiscover("on");
		paintPay("off");
		break;
	case "Payment phase.":  // after .makePayment
		paintCharge("off");
		paintSearch("off");
		paintDiscover("off");
		paintPay("on");
		break;
	case "Charging the car...":  // after .getServiceDeliveryToken
		paintCharge("on");
		paintSearch("off");
		paintDiscover("off");
		paintPay("off");
		break;
	case "Car charged, waiting for input...":
		paintCharge("off");
		paintSearch("off");
		paintDiscover("off");
		paintPay("off");
		break;
	default:
		switch (data['description']){
			case "Selecting service...":
				paintCharge("off");
				paintSearch("off");
				paintDiscover("on");
				paintPay("off");
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

function paintIt(group, box, arrow, grot, color, opacity) {

	// change opcity
	group.style.opacity = opacity;
	var arrow_opacity = opacity - 0.2;
	
	// if (arrow_opacity >= 0) {
	// 	arrow.style.opacity = arrow_opacity;
	// } else {
	// 	arrow.style.opacity = opacity;
	// }
	//box.style.opacity = opacity;

	arrow.style.background = "rgba(" + COLOR_VALUE + ",0)";
	box.style.background = "rgba(" + COLOR_VALUE + ",0)";
	grot.children[0].style.opacity = opacity;

	// change color
	group.style.fill = color;
	group.style.stroke = color;
	if (color === undefined)
	{
		group.style.fill = DEACTIVATE_COLOR;
		group.style.stroke = DEACTIVATE_COLOR;
	}
	arrow.style.stroke = color;
	grot.children[0].style.fill = color;
	grot.children[0].style.stroke = color;
	box.style.fill = color;
	box.style.stroke = color;
}

function paintCharge(state)
{
	var paint = paintCharge;
	if (typeof paint.grupped == 'undefined') {
        // It has not... perform the initialization
		paint.grupped = svg.getElementById("ChargeCar");
	}
	if (typeof paint.arrow == 'undefined') {
		paint.arrow = svg.getElementById("arrChargeCar");
	}
	if (typeof paint.grot == 'undefined') {
		paint.grot = svg.getElementById("marker8661");
	}
	if (typeof paint.box == 'undefined') {
		paint.box = svg.getElementById("rectChargeCar");
	}

	if (state == "on")
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, ACTIVATE_COLOR, MAX_OPACITY);
	}
	else if (state == "off")
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, 'undefined', MIN_OPACITY);
	}
	else
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, ERROR_COLOR, MIN_OPACITY);
	}
}

function paintSearch(state)
{
	var paint = paintSearch;
	if (typeof paint.grupped == 'undefined') {
        // It has not... perform the initialization
		paint.grupped = svg.getElementById("Search");
	}
	if (typeof paint.arrow == 'undefined') {
		paint.arrow = svg.getElementById("arrSearch");
	}
	if (typeof paint.grot == 'undefined') {
		paint.grot = svg.getElementById("marker11531");
	}
	if (typeof paint.box == 'undefined') {
		paint.box = svg.getElementById("rectSearch");
	}

	if (state == "on")
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, ACTIVATE_COLOR, MAX_OPACITY);
	}
	else
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, 'undefined', MIN_OPACITY);
	}
}

function paintBroadcast(state)
{
	var paint = paintBroadcast;
	if (typeof paint.grupped == 'undefined') {
        // It has not... perform the initialization
		paint.grupped = svg.getElementById("Broadcast");
	}
	if (typeof paint.arrow == 'undefined') {
		paint.arrow = svg.getElementById("arrBroadcast");
	}
	if (typeof paint.grot == 'undefined') {
		paint.grot = svg.getElementById("Arrow2Mstart");
	}
	if (typeof paint.box == 'undefined') {
		paint.box = svg.getElementById("rectBroadcast");
	}

	if (state == "on")
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, ACTIVATE_COLOR, MAX_OPACITY);
	}
	else
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, 'undefined', MIN_OPACITY);
	}
}

function paintDiscover(state)
{
	var paint = paintDiscover;
	if (typeof paint.grupped == 'undefined') {
        // It has not... perform the initialization
		paint.grupped = svg.getElementById("Discover");
	}
	if (typeof paint.arrow == 'undefined') {
		paint.arrow = svg.getElementById("arrDiscover");
	}
	if (typeof paint.grot == 'undefined') {
		paint.grot = svg.getElementById("marker7839");
	}
	if (typeof paint.box == 'undefined') {
		paint.box = svg.getElementById("rectDiscover");
	}

	if (state == "on")
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, ACTIVATE_COLOR, MAX_OPACITY);
	}
	else
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, 'undefined', MIN_OPACITY);
	}
}

function paintPay(state)
{
	var paint = paintPay;
	if (typeof paint.grupped == 'undefined') {
        // It has not... perform the initialization
		paint.grupped = svg.getElementById("Pay");
	}
	if (typeof paint.arrow == 'undefined') {
		paint.arrow = svg.getElementById("arrPay");
	}
	if (typeof paint.grot == 'undefined') {
		paint.grot = svg.getElementById("marker8194");
	}
	if (typeof paint.box == 'undefined') {
		paint.box = svg.getElementById("rectPay");
	}

	if (state == "on")
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, ACTIVATE_COLOR, MAX_OPACITY);
	}
	else
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, 'undefined', MIN_OPACITY);
	}
}

function paintEarn(state, color, opacity)
{
	var paint = paintEarn;
	if (typeof paint.grupped == 'undefined') {
        // It has not... perform the initialization
		paint.grupped = svg.getElementById("Earn");
	}
	if (typeof paint.arrow == 'undefined') {
		paint.arrow = svg.getElementById("arrEarn");
	}
	if (typeof paint.grot == 'undefined') {
		paint.grot = svg.getElementById("marker9090");
	}
	if (typeof paint.box == 'undefined') {
		paint.box = svg.getElementById("rectEarn");
	}

	if (state == "on")
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, color, opacity);
	}
	else
	{
		paintIt(paint.grupped, paint.box, paint.arrow, paint.grot, 'undefined', opacity);
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

		['on', 'off'].forEach(function(item){
			paintCharge(item);
			paintSearch(item);
			paintDiscover(item);
			paintPay(item);
			paintBroadcast(item);
		});
	
		paintEarn("on", ACTIVATE_COLOR, MAX_OPACITY);
		paintEarn('off', 'undefined', MIN_OPACITY);

		A=[a1,a2,a3,a4,a5,a6];
		A.forEach(function(g){
			g.style.transition="opacity 1s ease-in-out, stroke 1s ease-in-out, fill 1s ease-in-out";
		});
		a6.onclick = toggleBroadcast;
		a2.onclick = plugItIn;
		a7.textContent = "";
	}

	updateProducerState(data);

	if(data.hasOwnProperty('broadcast')){
		prop = data['broadcast'];
		if(prop === 'broadcasting'){
			paintBroadcast("on");
			broadcasting = true;
		}else if(prop === 'off'){
			paintBroadcast("off");
			broadcasting = false;
		}else{
			paintBroadcast("err");
			broadcasting = false;
		}
	}else{
		//setArrow(a6, "none", "broadcast");
		paintBroadcast("none");
	}
}

$.ajaxSetup({'cache':true, timeout:300});
var stepTimer = setInterval(refreshStatus, 750);
