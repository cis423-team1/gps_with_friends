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
        <title>JSP Page</title>
    </head>
    <body>
        
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
    try {
	edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
	edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
	 // TODO initialize WS operation arguments here
	int uid = user.getUid();
	// TODO process result here
	edu.uoregon.cs.client.Location result = port.getLocation(uid);
	out.println("Result = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    </body>
</html>
