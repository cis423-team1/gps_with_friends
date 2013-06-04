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
        <h1>GPS for Friends</h1>
        
        
    <%-- start web service invocation --%><hr/>
    <%
        java.util.List<edu.uoregon.cs.client.User> result = new java.util.ArrayList<edu.uoregon.cs.client.User>();
    try {
	edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
	edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
	 // TODO initialize WS operation arguments here
	int gid = Integer.parseInt(request.getParameter("glist"));
        out.println("Group ID = "+gid);
	// TODO process result here
	result = port.getMembers(gid);
	out.println("First Member = "+result.get(0).getEmail());
    } catch (Exception ex) {
	out.println(ex.getMessage());
    }
    %>
    
     <form action="groupAction.jsp" method="POST">
        <select name="userSelected">
    <%
    
    for (int i = 0;i<result.size();i++)
    {
        edu.uoregon.cs.client.User g = result.get(i);
        out.println("<option value='" + g.getEmail() + "'>"+ g.getEmail() + "</option>");
    }
    
    %>
        </select>
    <input type="hidden" value="<%= request.getParameter("glist")%>" name="hiddenGName">
    <input type="submit" name="action" value="View on Map" />
    <input type="submit" name="action" value="Remove From Group" />
    <input type="submit" name="action" value="Show History" />

           
    
    <%-- end web service invocation --%><hr/>
    </form>
    
    
    <form name ="<%= request.getParameter("glist") %>" action="addMember.jsp" method="POST">
        
        <input type="text" value="enter the email" name="emailUser" />
        <input type="hidden" value="<%= request.getParameter("glist")%>" name="hiddenGName">
        <input type="submit" value="invite" />
    </form>
    <%-- end web service invocation --%><hr/>
    </body>
</html>
