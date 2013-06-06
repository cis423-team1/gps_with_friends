<%-- 
    Document   : listGroupMembers
    Created on : Jun 2, 2013, 1:02:37 PM
    Author     : ahmad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body style='width:1200px;'>
    
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB5U_RwN3gt5ZpvGNIWyZEb1MgqP5kx05k&sensor=false"></script>
        <script src="gpsmap.js"></script>
        <script src="infobox.js"></script>
        
    <table id='grouptable'> 
        <tr><td>
                <form name="back" action="main.jsp">
                    <input type="submit" value="Back"/>
                </form>
            
    <%-- start web service invocation --%>
    <%
        //make sure the correct group is saved in the session
        if (request.getParameter("glist") != null) {
            session.setAttribute("gid", request.getParameter("glist"));
        }
        edu.uoregon.cs.client.Group result = new edu.uoregon.cs.client.Group();
    	try {
				edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
				edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
	 			// TODO initialize WS operation arguments here
				int gid = Integer.parseInt(session.getAttribute("gid").toString());
				// TODO process result here
				result = port.getGroup(gid);
        		out.println("<h1>"+result.getName()+"</h1></td></tr>");
    	} catch (Exception ex) {
			out.println(ex.getMessage());
    	}
    
    	//get owner
    	edu.uoregon.cs.client.User owner = new edu.uoregon.cs.client.User();
    	try {
			edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
			edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
	 		// TODO initialize WS operation arguments here
			int uid = result.getOwner();
			// TODO process result here
	 		owner = port.getUserByID(uid);
    	} catch (Exception ex) {
			// TODO handle custom exceptions here
    	}
    
    %>
	    <tr><td>
	    		<form action="groupAction.jsp" method="POST">
	        		<select name="userSelected">
	    				<%  
	    					for (int i = 0;i<result.getUsers().size();i++) {
	        					edu.uoregon.cs.client.User u = result.getUsers().get(i);
	        					out.println("<option value='" + u.getEmail() + "'>"+u.getFName()+" "+u.getLName()+"</option>");
	    					}
	    
	    				%>
	        		</select>
                                <input type="submit" name="action" value="View on Map" />
                                <% if (owner.getUid() == ((edu.uoregon.cs.client.User)session.getAttribute("user")).getUid()) {%>
	    			<input type="submit" name="action" value="Remove From Group" />
                                <%}%>
	    			<input type="submit" name="action" value="Show History" />
	    	</form>
            <% if (owner.getUid() == ((edu.uoregon.cs.client.User)session.getAttribute("user")).getUid()) {%>
	    </td></tr>
	    <tr><td>
	    		<form name ="<%= session.getAttribute("gid") %>" action="addMember.jsp" method="POST">     
	        		<input type="text" value="enter the email" name="emailUser" />
	        		<input type="submit" value="invite" />
	    		</form>
	    </td></tr>
            <%}%>
	</table>
	<table id='groupinfo'>
	    <tr><td>
	           	<h4>Group Information:</h4>
	    </td></tr>
	    <tr><td>
	            Date Created: <%= result.getDateCreated() %>
	    </td></tr>
	    <tr><td>
	            Owner: <%= owner.getFName()+" "+owner.getLName() %>
	    </td></tr>
            <% if (owner.getUid() == ((edu.uoregon.cs.client.User)session.getAttribute("user")).getUid()) {%>
            <tr><td>
	            <form action="deleteGroup.jsp" method="POST">
                        <input type="submit" value="Delete Group">
                    </form>
	    </td></tr>
            <%}%>
    </table>
                
    <h2>Current Locations for <%= result.getUsers().size()%> group members</h2>
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
