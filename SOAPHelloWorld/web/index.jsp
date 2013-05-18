<%-- 
    Document   : index
    Created on : May 18, 2013, 12:16:51 PM
    Author     : ahmad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    	<style type="text/css">
      		html { height: 100% }
      		body { height: 100%; margin: 0; padding: 0 }
			#user-input { width:200px; padding:50px; }
			#map-canvas { height:600px; width:800px; position:absolute; right:50px; top:50px; }
    	</style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>This is my page</title>

    </head>
    <body>
        <h1></h1>
    <%-- start web service invocation --%><hr/>
    
    
        <%-- start web service invocation --%><hr/>
    <%
        long result=12;
    try {
	edu.uoregon.client.HelloWorld_Service service = new edu.uoregon.client.HelloWorld_Service();
	edu.uoregon.client.HelloWorld port = service.getHelloWorldPort();
	// TODO process result here
	result= port.getLocation();
	out.println("Result = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    
    		<div id="user-input">
			<form id="coord" action="#" onsubmit="setCoordinates(); return false">
				Name: <input type="string" name="person">
				Latitude (-90:90): <input type="number" step="any" name="lat">
				Longitude (-180:180): <input type="number" step="any" name="lng">
				<input type="submit">
			</form>
		</div>
		<div id="map-canvas"/>

		<script 
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB5U_RwN3gt5ZpvGNIWyZEb1MgqP5kx05k&sensor=false">
		</script>
    	<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
		<script src="gmap3v5.0b/gmap3.min.js"></script>
		<script>
			
			function initialize(){
				this.map = new google.maps.Map(document.getElementById('map-canvas'), this.options);
			}

			function changeCenter(latLng){		
				this.map.setCenter(latLng);
			}

			function addMarker(name, latLng){
				this.markers[name] = new google.maps.Marker({	
					position: latLng,
      				map: this.map,
      				title: name
  				});
			}
		
			function mymap(){
				this.map;
				this.markers = [];
				this.options = {
					zoom: 6,
					center: new google.maps.LatLng(44.0522, -123.0856),
					mapTypeId: google.maps.MapTypeId.ROADMAP
				}
				this.start = initialize;
				this.setView = changeCenter;
				this.markLoc = addMarker;
			}

			var m = new mymap();
			m.start();

			function setCoordinates(){
				var newloc = new google.maps.LatLng(<%=result %>, 
					<%=result %>);
				
				m.setView(newloc);
				m.markLoc(document.getElementById("coord").person.value, newloc);
				
				document.getElementById("coord").person.value = "";
				document.getElementById("coord").lat.value = "";
				document.getElementById("coord").lng.value = "";
			}
		</script>
    <%
    try {
	edu.uoregon.client.HelloWorld_Service service = new edu.uoregon.client.HelloWorld_Service();
	edu.uoregon.client.HelloWorld port = service.getHelloWorldPort();
	 // TODO initialize WS operation arguments here
	java.lang.String name = "ahmad";
	// TODO process result here
	java.lang.String out1 = port.hello(name);
	out.println("Result = "+out1);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>

    </body>
</html>
