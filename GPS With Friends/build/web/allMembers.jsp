<%-- 
    Document   : allMembers
    Created on : Jun 6, 2013, 6:29:05 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            #title { 
                text-align: center;
            }
            #map-canvas {
                border:2px solid black;
                margin:25px auto 25px auto;
                height:750px; 
                width:1000px;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB5U_RwN3gt5ZpvGNIWyZEb1MgqP5kx05k&sensor=false"></script>
        <script src="gpsmap.js"></script>
        <script src="infobox.js"></script>
        <%
            edu.uoregon.cs.client.Group result = new edu.uoregon.cs.client.Group();
            try {
                edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
                edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
                // TODO initialize WS operation arguments here
                int gid = Integer.parseInt(session.getAttribute("gid").toString());
                // TODO process result here
                result = port.getGroup(gid);
            } catch (Exception ex) {
                out.println(ex.getMessage());
            }
        %>
        <h1 id="title">Current Locations for <%= result.getUsers().size()%> group members</h1>
    <div id='map-canvas'/>
    <script>
            var m = new mymap();
            m.start();
    <%
            //plot each user's last location to the map
            out.println("var group = [");
            for (int i = 0; i < result.getUsers().size(); i++) {
                edu.uoregon.cs.client.User user = result.getUsers().get(i);
                edu.uoregon.cs.client.Location curLoc = user.getLastLoc();
                out.print("{ name: '"+user.getFName()+" "+user.getLName()+"', lat: " + curLoc.getLatitude()+ ", lng: " + curLoc.getLongitude() + " }");
                if (i < result.getUsers().size() - 1) {
                    out.println(",");
                }
            }
            out.println("];");
            %>
                
            m.displayGroup(group);
        
    </script>
  </body>
</html>
