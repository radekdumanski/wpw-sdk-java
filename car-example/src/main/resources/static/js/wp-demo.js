function doGetSmartCarStatus() {
	var els = document.getElementsByName('flow-json-source');
	var lnk = document.getElementById('flow-json-link');
	var uri = "";
	if(els.length==0){
		console.warn("Cannot find flow-json-source element, using default");
		uri="../flow.json";
	}else{
		switch(els[0].value){
			case "ajax-lo":
				uri = "/flow.json";
				break;
			case "ajax-mawk":
				uri = "http://192.168.137.3:8000/flow.json";
				break;
			case "ajax-lo-java":
				uri = "http://127.0.0.1:8000/getStatus";
				break;
			case "ajax-wabe":
				uri = "http://192.168.35.7:8000/getstatus"
				break;
			case "ajax-bamr":
				uri = "http://192.168.34.165:8000/getStatus"
				break;
			case "file":
				uri = "../flow.json";
				break;
			default:
				uri = "../flow.json";
				break;
		}
		document.getElementById('flow-json-link-a').href=uri;
		document.getElementById('flow-json-link-a').innerText=uri;
	}
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
		if(data.hasOwnProperty('flow')){
			document.getElementById('jsonBoxFlow').innerHTML = data['flow'];
		}else{
			document.getElementById('jsonBoxFlow').innerHTML = "";
		}
		if(data.hasOwnProperty('battery')){
			var m = data['battery'].match(/^([0-9]+)$/);
			if(m !== null && m.length>0 && m[1]>=0 && m[1]<=100){
				// TODO: parseInt()
				//document.getElementById('jsonBoxBatt').innerHTML = "charge parse error";
				document.getElementById('jsonBoxBattGreen').style.width = m[1]+'%';
				document.getElementById('jsonBoxBattGray').style.width = (100-m[1])+'%';
			}else{
				console.log("Cannot parse battery charge info", data['battery']);
				document.getElementById('jsonBoxBattGreen').style.width = '0px';
				document.getElementById('jsonBoxBattGray').style.width = '0px';
			}
		}else{
			document.getElementById('jsonBoxBattGreen').style.width = '0';
			document.getElementById('jsonBoxBattGray').style.width = '0';
		}
		if(data.hasOwnProperty('units')) {
			document.getElementById('jsonBoxUnits').innerHTML = data['units'];
		}else{
			document.getElementById('jsonBoxUnits').innerHTML = "";
		}
		document.getElementById('jsonTimestamp').innerHTML = jqXHR.getResponseHeader("Last-Modified");
	})
	.done(function(json){
		//console.log("done", json);
	})
	.fail(function(){
		console.log("Failed to get JSON");
		document.getElementById('jsonBoxPrice').innerHTML = "N/A";
		document.getElementById('jsonBoxDescription').innerHTML = "N/A";
		document.getElementById('jsonBoxFlow').innerHTML = "N/A";
		document.getElementById('jsonBoxBattGreen').style.width = '0';
		document.getElementById('jsonBoxBattGray').style.width = '0';
		document.getElementById('jsonBoxUnits').innerHTML = "N/A";
		document.getElementById('jsonTimestamp').innerHTML = "";
	})
	.always(function(){
		//console.log("always");
		var s = document.querySelector("div.line1").style;
		if(s.borderColor == "black"){
			s.borderColor = "white"
		}else{
			s.borderColor = "black"
		}
	})
	;
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


//prepareCars(10);
$.ajaxSetup({'cache':true, timeout:300});
document.getElementById('jsonBoxBatt').onclick=function(e){
	//console.log(e);
	var val = Math.round(100*e.offsetX/e.target.offsetWidth);
	if(val<0 || val>100){
		console.warn("Computation error: e.offsetX/e.target.offsetWidth=...",e.offsetX,e.target.offsetWidth,val);
		return;
	}
	//console.log('offsetX',e.offsetX);
	//console.log('offsetWidth',e.target.offsetWidth);
	//console.log('clientWidth',e.target.clientWidth);
	var uri = 'http://127.0.0.1:8000/setCharge?data='+val;
	$.ajax({url: uri})
	.done(function(data){
		//console.log("Charge set to "+e.offsetX);
		console.log("Charge set to "+val);
	});
};
document.getElementById('rechargeButton').onclick=function(e){
	console.log(e);
	var uri = "http://127.0.0.1:8000/plugin";
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
var smartCarStatusTimer = setInterval(doGetSmartCarStatus, 750 * 2);
