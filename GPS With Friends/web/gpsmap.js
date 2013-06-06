function initialize(){
	this.map = new google.maps.Map(document.getElementById('map-canvas'), this.options);
}

function changeCenter(latLng){		
	this.map.setCenter(latLng);
}

function makeLatLng(lat, lng){
	var loc = new google.maps.LatLng(lat, lng);
	return loc;
}

function addMarker(name, latLng){
	var boxText = document.createElement("div");
    boxText.style.cssText = "border: 1px solid black; background: white; padding: 5px;";
    boxText.innerHTML = name;
    
	var myOptions = {
			 content: boxText
			,disableAutoPan: false
			,maxWidth: 0
			,pixelOffset: new google.maps.Size(16, -32)
			,zIndex: null
			,boxStyle: { 
			  background: "url('tipbox.gif') no-repeat"
			  ,opacity: 0.75
			  ,width: "150px"
			 }
			,closeBoxMargin: "10px 2px 2px 2px"
			,closeBoxURL: "http://www.google.com/intl/en_us/mapfiles/close.gif"
			,infoBoxClearance: new google.maps.Size(1, 1)
			,isHidden: false
			,pane: "floatPane"
			,enableEventPropagation: false
		};
	
	this.markers[name] = new google.maps.Marker({	
		position: latLng,
		map: this.map,
		title: name
	});
	
	var ib = new InfoBox(myOptions);
	ib.open(this.map, this.markers[name]);
}

function showGroup(group){
	var length = group.length;
	var element = null;
	for(var i=0; i < length; i++){
		element = group[i];
		var newloc = new google.maps.LatLng(element.lat, element.lng);
		this.markLoc(element.name, newloc);
	}
	this.setView(newloc);
}

function mymap(){
	this.map;
	this.markers = [];
	this.options = {
		zoom: 4,
		center: new google.maps.LatLng(44.0522, -123.0856),
		mapTypeId: google.maps.MapTypeId.ROADMAP
	}
	this.start = initialize;
	this.setView = changeCenter;
	this.markLoc = addMarker;
	this.displayGroup = showGroup;
	this.createLatLng = makeLatLng;
}

function setCoordinates(){
	var group = [
			{ name: 'Dan', lat: 22, lng: 22 },
			{ name: 'Josh', lat: 21, lng: 21 },
			{ name: 'Ahmad', lat: 20, lng: 20 }
	]
	m.displayGroup(group);
	//var newloc = new google.maps.LatLng(document.getElementById("coord").lat.value, 
		//document.getElementById("coord").lng.value);

	//m.setView(newloc);
	//m.markLoc(document.getElementById("coord").person.value, newloc);

	document.getElementById("coord").person.value = "";
	document.getElementById("coord").lat.value = "";
	document.getElementById("coord").lng.value = "";
}