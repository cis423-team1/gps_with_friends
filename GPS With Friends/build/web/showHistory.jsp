<%-- 
    Document   : showHistory.jsp
    Created on : Jun 2, 2013, 2:25:36 PM
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
        <h1>Hello World!</h1>
        
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
	int number =100 ;
	// TODO process result here
	java.util.List<edu.uoregon.cs.client.Location> result = port.getHistory(uid, number);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    </body>
</html>
