function doGetSmartCarStatus() {
	var lnk = document.getElementById('flow-json-link');
	var uri = document.URL.match(/(^https?:\/\/[^/]+)/)[0] + "/getStatus";
	processJSON(uri);
}

function callBiddingJson(i) {
	var jsonString = "../bidding-status.json?timefactor=" + (new Date());
	//console.log(jsonString);
	$.getJSON(jsonString, function(json) {
		//console.log("Outputting bidding json: " + json); // this will show the info it in firebug debugConsole
		obj = json;
		outputBiddingJson(obj, i);
	});
}

function processJSON(jsonPath){
	$.getJSON(jsonPath, function(data, textStatus, jqXHR){
		var items = [];
		//console.log(data);

		/*
		if(data.hasOwnProperty('price')){
			document.getElementById('jsonBoxPrice').innerHTML = data['price'];
		}else{
			document.getElementById('jsonBoxPrice').innerHTML = "";
		}
		if(data.hasOwnProperty('description')){
			document.getElementById('jsonBoxDescription').innerHTML = data['description'];
		}else{
			document.getElementById('jsonBoxDescription').innerHTML = "";
		}
		*/
		if(data.hasOwnProperty('flow')){
			document.getElementById('commDetails').innerHTML = data['flow'];
		}else{
			//document.getElementById('commDetails').innerHTML = "";
		}
		if(data.hasOwnProperty('battery')){
			var m = data['battery'].match(/^([0-9]+)$/);
			if(m !== null && m.length>0 && m[1]>=0 && m[1]<=100){
				// TODO: parseInt()
				//document.getElementById('jsonBoxBatt').innerHTML = "charge parse error";
				updateChargeMeter(m[1]);
				//document.getElementById('batteryValText').innerText = m[1]+'%';
				//document.getElementById('batteryChargeBar').style.width = m[1]+'%';
				//document.getElementById('jsonBoxBattGray').style.width = (100-m[1])+'%';
			}else{
				console.log("Cannot parse battery charge info", data['battery']);
				updateChargeMeter(null);
				//document.getElementById('batteryValText').innerText = '';
				//document.getElementById('batteryChargeBar').style.width = '0px';
				//document.getElementById('jsonBoxBattGray').style.width = '0px';
			}
		}else{
			updateChargeMeter(null);
			//document.getElementById('batteryValText').innerText = '';
			//document.getElementById('batteryChargeBar').style.width = '0';
			//document.getElementById('jsonBoxBattGray').style.width = '0';
		}
		/*
		if(data.hasOwnProperty('units')) {
			document.getElementById('jsonBoxUnits').innerHTML = data['units'];
		}else{
			document.getElementById('jsonBoxUnits').innerHTML = "";
		}
		document.getElementById('jsonTimestamp').innerHTML = jqXHR.getResponseHeader("Date");
		*/
	})
	.done(function(json){
		//console.log("done", json);
	})
	.fail(function(){
		console.log("Failed to get JSON");
		/*
		document.getElementById('jsonBoxPrice').innerHTML = "N/A";
		document.getElementById('jsonBoxDescription').innerHTML = "N/A";
		document.getElementById('jsonBoxFlow').innerHTML = "N/A";
		document.getElementById('jsonBoxBattVal').innerText = '';
		document.getElementById('jsonBoxBattGreen').style.width = '0';
		document.getElementById('jsonBoxBattGray').style.width = '0';
		document.getElementById('jsonBoxUnits').innerHTML = "N/A";
		document.getElementById('jsonTimestamp').innerHTML = "";
		*/
		/*
		document.getElementById('commDetails').innerHTML = "N/A";
		document.getElementById('batteryValText').innerText = '';
		document.getElementById('batteryChargeBar').style.width = '0';
		*/
	})
	.always(function(){
		//console.log("always");
		/*
		var s = document.querySelector("div.line1").style;
		if(s.borderColor == "black"){
			s.borderColor = "white"
		}else{
			s.borderColor = "black"
		}
		*/
	})
	;
}

var chargeMeterIsLocked = false;
var skipOneUpdate = false;  // Decrease chance of sync issues between display and actual update
function updateChargeMeter(val){
	if(chargeMeterIsLocked){
		skipOneUpdate = true;
		return;
	}
	if(skipOneUpdate){
		skipOneUpdate = false;
		return;
	}
	document.getElementById("battRange").value = val;
	setChargeMeter(val);
}
function setChargeMeter(val){
	// Sets charge meter value (display only)
	// To be called by functions that mangle with value on window or server
	if(val === null){
		// TODO: Add a marker
		return;
	}
	document.getElementById("batteryChargeBar").style.width = val + "%";
	document.getElementById("batteryValText").innerText = val + "%";
}
var batteryChargeBar = document.getElementById("batteryChargeBar");
var batteryValText = document.getElementById("batteryValText");
function lockChargeMeter(){
	chargeMeterIsLocked = true;
	//console.log("meter locked");
	batteryChargeBar.style.transition="background-color 0s ease-out";
	batteryChargeBar.style.backgroundColor="red";
	batteryValText.style.transition="color 0s ease-out";
	batteryValText.style.color="white";
}
function unlockChargeMeter(){
	chargeMeterIsLocked = false;
	//console.log("meter unlocked");
	batteryChargeBar.style.transition="background-color 0.5s ease-out";
	batteryChargeBar.style.backgroundColor="#45ed85";
	batteryValText.style.transition="color 0.5s ease-out";
	batteryValText.style.color="black";
}

function callJson(i) {
	var jsonString = "../car-" + i + "-status.json?timefactor=" + (new Date());
	//console.log(jsonString);
	$.getJSON(jsonString, function(json) {
		//console.log("Outputting json: " + json); // this will show the info it in firebug debugConsole
		obj = json;
		outputJson(obj, i);
	});
}


document.getElementById('battRange').ongotpointercapture=function(e){
	lockChargeMeter();};
document.getElementById('battRange').oninput=function(e){
	lockChargeMeter();};
document.getElementById('battRange').ontouchstart=function(e){
	lockChargeMeter();};

document.getElementById('battRange').onlostpointercapture=function(e){
	unlockChargeMeter();};
document.getElementById('battRange').ontouchend=function(e){
	unlockChargeMeter();};

document.getElementById('battRange').ontouchmove=function(e){
	setChargeMeter(e.srcElement.value);};
document.getElementById('battRange').onpointermove=function(e){
	setChargeMeter(e.srcElement.value);};

document.getElementById('battRange').onchange=function(e){
	console.log("onchange",e);
	//e.srcElement e.currentTarget e.target
	// ondrag onscroll onseeking onscroll onselect ontouchmove ontouchstart onwheel
	var val = e.srcElement.value;
	if(val<0 || val>100){
		console.warn("Computation error: e.offsetX/e.target.offsetWidth=...",e.offsetX,e.target.offsetWidth,val);
		return;
	}
	//console.log('offsetX',e.offsetX);
	//console.log('offsetWidth',e.target.offsetWidth);
	//console.log('clientWidth',e.target.clientWidth);
	var uri = document.URL.match(/(^https?:\/\/[^/]+)/)[0] + "/setCharge?data="+val;
	$.ajax({url: uri})
	.done(function(data){
		//console.log("Charge set to "+e.offsetX);
		console.log("Charge set to "+val);
	});

};
/*
//prepareCars(10);
>>>>>>> origin/develop
$.ajaxSetup({'cache':true, timeout:300});
document.getElementById('battery').onclick=function(e){
	var val = Math.round(100*e.offsetX/e.target.offsetWidth);
	if(val<0 || val>100){
		console.warn("Computation error: e.offsetX/e.target.offsetWidth=...",e.offsetX,e.target.offsetWidth,val);
		return;
	}
	//console.log('offsetX',e.offsetX);
	//console.log('offsetWidth',e.target.offsetWidth);
	//console.log('clientWidth',e.target.clientWidth);
	var uri = document.URL.match(/(^https?:\/\/[^/]+)/)[0] + "/setCharge?data="+val;
	$.ajax({url: uri})
	.done(function(data){
		//console.log("Charge set to "+e.offsetX);
		console.log("Charge set to "+val);
	});
};
*/
/*
document.getElementById('chkAdmin').onclick=function(e){
	console.log(e);
	console.log(e.target);
	if(e.target.checked){
		document.getElementById('jsonBoxBatt').classList.add('adminMode');
	}else{
		document.getElementById('jsonBoxBatt').classList.remove('adminMode');
	}
};
*/
/*
document.getElementById('chkSrc').onclick=function(e){
	console.log(e);
	console.log(e.target);
	if(e.target.checked){
		document.getElementById('ctrl-box-src').classList.remove('hidden');
		document.getElementById('jsonTimestamp').classList.remove('hidden');
	}else{
		document.getElementById('ctrl-box-src').classList.add('hidden');
		document.getElementById('jsonTimestamp').classList.add('hidden');
	}
};
*/
var pluginButton = document.getElementById('pluginButton');
pluginButton.onclick=function(e){
	console.log(pluginButton);
	pluginButton.style.transition='all ease 0.4s';
	pluginButton.style.color="white";
	pluginButton.style.backgroundColor ="red";
	setTimeout(function() {
		pluginButton.style.transition='all ease 0.4s';
		pluginButton.style.color="black";
		pluginButton.style.backgroundColor ="white";
	}, 500);
	//var uri = "http://127.0.0.1:8000/plugin";
	var uri = document.URL.match(/(^https?:\/\/[^/]+)/)[0] + "/plugin";
	$.ajax({url: uri})
	.fail(function(jqXHR, textStatus, errorThrown){
		if(jqXHR.status == 409){
			alert("Cannot start charging - possibly already started");
		}else{
			console.log('jqXHR',jqXHR);
			console.log('textStatus',textStatus);
			console.log('errorThrown',errorThrown);
		}
	})
	.done(function(data){
		console.log("Recharge");
	});
};
doGetSmartCarStatus();
//var smartCarStatusTimer = setInterval(doGetSmartCarStatus, 750 * 2);
var smartCarStatusTimer = setInterval(doGetSmartCarStatus, 500);
