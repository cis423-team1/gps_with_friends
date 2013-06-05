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
			#infowindow { width:25px; border:1px solid; }
    	</style>
        <title>CIS 423 Project</title>
    </head>

  	<body>
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
		<script src="infobox.js"></script>
		<script src="gpsmap.js"></script>
		
		<script>
			var m = new mymap();
			m.start();	
				
			
		</script>
  </body>
</html>
