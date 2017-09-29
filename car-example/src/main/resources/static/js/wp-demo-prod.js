function refreshChargerStatus() {
	processJSON("/getChargerStatus");
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
			// In the past the value was passed as "99%"
			// In the future it is likely to be ex. "167kWh"
			if(m !== null && m.length>0 && m[1]>=0 && m[1]<=100){
				// TODO: parseInt()
				document.getElementById('jsonBoxBatt').innerText = m[1];
			}else{
				console.log("Cannot parse battery charge info", data['battery']);
				document.getElementById('jsonBoxBatt').innerText = '';
			}
		}else{
			document.getElementById('jsonBoxBatt').innerText = '';
		}
		if(data.hasOwnProperty('units')) {
			document.getElementById('jsonBoxUnits').innerHTML = data['units'];
		}else{
			document.getElementById('jsonBoxUnits').innerHTML = "";
		}
		document.getElementById('jsonTimestamp').innerHTML = jqXHR.getResponseHeader("Date");
	})
	.done(function(json){
		//console.log("done", json);
	})
	.fail(function(){
		console.log("Failed to get JSON");
		document.getElementById('jsonBoxPrice').innerHTML = "N/A";
		document.getElementById('jsonBoxDescription').innerHTML = "N/A";
		document.getElementById('jsonBoxFlow').innerHTML = "N/A";
		document.getElementById('jsonBoxBatt').innerText = '';
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

$.ajaxSetup({'cache':true, timeout:300});
refreshChargerStatus();
var smartChargerTimer = setInterval(refreshChargerStatus, 750 * 2);
