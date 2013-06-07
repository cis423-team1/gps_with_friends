<%-- 
    Document   : groupAction
    Created on : Jun 3, 2013, 4:23:03 PM
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
            
        //if View on Map was selected show last known location on map
        if (request.getParameter("action").equals("View on Map")) {
            //initialize user object
            edu.uoregon.cs.client.User user = new edu.uoregon.cs.client.User();
            //get user from soap
            try {
                edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
                edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
                //get email from form
                java.lang.String email = request.getParameter("userSelected");
                user = port.getUser(email);
            } catch (Exception ex) {}
            //initilize location object
            
            //get last location from server
            edu.uoregon.cs.client.Location lastLocation = new edu.uoregon.cs.client.Location();
            try {
                edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
                edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
                int uid = user.getUid();
                // TODO process result here
                lastLocation = port.getLocation(uid);


            } catch (Exception ex) {}
            //display top of page
            out.println("<h1 id='title'>Last Know Location of "+user.getFName()+" "+user.getLName()+"</h1>");
            out.println("<div id='map-canvas'/>");
            //print javascript to display location on map
            out.println("<script>\n");
            out.println("var m = new mymap();");
            out.println("m.start();");
            out.println("var group = [{ name: '" + lastLocation.getDate() + "', lat: " + lastLocation.getLatitude()+ ", lng: " + lastLocation.getLongitude() + " }];");
            out.println("m.displayGroup(group);");
            out.println("</script>");
        }
        
        else if (request.getParameter("action").equals("Remove From Group")) {
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
            try {
                edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
                edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();

                 // TODO initialize WS operation arguments here
                int uid =user.getUid();
                int gid =Integer.parseInt(session.getAttribute("gid").toString());
                // TODO process result here
                edu.uoregon.cs.client.Status result = port.removeMember(uid, gid);

                response.sendRedirect("refreshParent.html");
                
            } catch (Exception ex) {
                out.println("An error occured. Message: " + ex.getMessage());
            }
        }

        else if (request.getParameter("action").equals("Show History")) {
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
            java.util.List<edu.uoregon.cs.client.Location> locations = new java.util.ArrayList<edu.uoregon.cs.client.Location>();
            try {
                edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
                edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
                 // TODO initialize WS operation arguments here
                int uid = user.getUid();
                int number =100 ;
                // TODO process result here
                locations = port.getHistory(uid, number);
            } catch (Exception ex) {}
            //display top of page
            out.println("<h1 id='title'>Last "+locations.size()+" recorded locations of "+user.getFName()+" "+user.getLName()+"</h1>");
            out.println("<div id='map-canvas'/>");
            //print javascript to display location on map
            out.println("<script>\n");
            out.println("var m = new mymap();");
            out.println("m.start();");
            out.println("var group = [");
            for (int i = 0; i < locations.size(); i++) {
                edu.uoregon.cs.client.Location curLoc = locations.get(i);
                out.print("{ name: '" + curLoc.getDate() + "', lat: " + curLoc.getLatitude()+ ", lng: " + curLoc.getLongitude() + " }");
                if (i < locations.size() - 1) {
                    out.println(",");
                }
            }
            out.println("];");
            out.println("m.displayGroup(group);");
            out.println("</script>");
        }
        %>
        
        
    </body>
</html>
