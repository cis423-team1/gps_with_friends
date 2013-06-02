<%-- 
    Document   : showOnMap
    Created on : Jun 2, 2013, 1:17:30 PM
    Author     : ahmad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="./gpsmap.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div id="map-canvas" />
       <%
        edu.uoregon.cs.client.User user = new edu.uoregon.cs.client.User();
    try {
	edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
	edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
	 // TODO initialize WS operation arguments here
	java.lang.String email = "";
	// TODO process result here
	user = port.getUser(request.getParameter("userSelected"));
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %> 
        
    <%-- start web service invocation --%><hr/>
    <%
    
    edu.uoregon.cs.client.Location result = new edu.uoregon.cs.client.Location();
    try {
	edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
	edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
	 // TODO initialize WS operation arguments here
	int uid = user.getUid();
	// TODO process result here
	result = port.getLocation(uid);
	
        
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    
    <script type="text/javascript">
        
        var map = new mymap();
        map.start();
        map.addMarker(<%= user.getEmail() %>, makeLatLng(<%=result.getLatitude() %>, <%= result.getLongitude() %>));
        
        </script>
    <%-- end web service invocation --%><hr/>
    </body>
</html>
