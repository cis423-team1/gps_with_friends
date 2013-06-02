<%-- 
    Document   : listGroupMembers
    Created on : Jun 2, 2013, 1:02:37 PM
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
        
        
    <%-- start web service invocation --%><hr/>
    <%
        java.util.List<edu.uoregon.cs.client.User> result = new java.util.ArrayList<edu.uoregon.cs.client.User>();
    try {
	edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
	edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
	 // TODO initialize WS operation arguments here
	int gid = Integer.parseInt(request.getParameter("glist"));
	// TODO process result here
	result = port.getMembers(gid);
	out.println("Result = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    
     <form action="showOnMap.jsp" method="POST">
        <select name="uList">
    <%
    
    for (int i = 0;i<result.size();i++)
    {
        edu.uoregon.cs.client.User g = result.get(i);
        System.out.println("<option value=' " + g.getUid() + "'> "+ g.getEmail() + "</option>");
    }
    
    %>
        </select>
    <input type="submit" value=" show list" />

    
    <%-- end web service invocation --%><hr/>
    </form>
    
    <%-- end web service invocation --%><hr/>
    </body>
</html>
