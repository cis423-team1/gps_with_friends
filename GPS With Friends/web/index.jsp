<%-- 
    Document   : index
    Created on : May 18, 2013, 12:16:51 PM
    Author     : ahmad
--%>

<%@page import="edu.uoregon.cs.client.Location"%>
<%@page import="java.lang.Long"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
   <%!
    public Location getLoc(String uid){
        try {
            edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
            edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
            // TODO process result here
            return port.getLocation(0);
        } catch (Exception ex) {
            return new Location();
        }
    }
    %>
    <%-- end web service invocation --%><hr/>
    
    		<div id="user-input">
			<form id="coord" action="#" onsubmit="setCoordinates(); return false">
				U ID: <input type="number" name="uid">
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
                                //change this line to use form data in future. For now it looks up with uid=0
                                <%Location loc = getLoc("0");%>
                                var newloc = new google.maps.LatLng(<%=loc.getLatitude()%>, <%=loc.getLongitude()%>);
				
				m.setView(newloc);
				m.markLoc(document.getElementById("coord").uid.value, newloc);
				
				document.getElementById("coord").uid.value = "";
			}
		</script>
    <%-- end web service invocation --%><hr/>

    </body>
</html>
