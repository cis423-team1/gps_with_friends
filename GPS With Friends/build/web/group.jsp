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
    <table> 
    <%-- start web service invocation --%><hr/>
    <%
        edu.uoregon.cs.client.Group result = new edu.uoregon.cs.client.Group();
    try {
	edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
	edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
	 // TODO initialize WS operation arguments here
	int gid = Integer.parseInt(request.getParameter("glist"));
	// TODO process result here
	result = port.getGroup(gid);
        out.println("<tr><td><h1>"+result.getName()+"<h1><td></tr>");
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
    
    for (int i = 0;i<result.getUsers().size();i++)
    {
        edu.uoregon.cs.client.User u = result.getUsers().get(i);
        out.println("<option value='" + u.getEmail() + "'>"+ u.getEmail() + "</option>");
    }
    
    %>
        </select>
    <input type="hidden" value="<%= request.getParameter("glist")%>" name="hiddenGName">
    <input type="submit" name="action" value="View on Map" />
    <input type="submit" name="action" value="Remove From Group" />
    <input type="submit" name="action" value="Show History" />

           
    
    <%-- end web service invocation --%><hr/>
    </form>
    </td>
    </tr>
    <tr><td>
    <form name ="<%= request.getParameter("glist") %>" action="addMember.jsp" method="POST">
        
        <input type="text" value="enter the email" name="emailUser" />
        <input type="hidden" value="<%= request.getParameter("glist")%>" name="hiddenGName">
        <input type="submit" value="invite" />
    </form>
        </tr>
        </td>
        <tr><td>
                <h4>Group Information:<h4>
            </td></tr>
        <tr><td>
                Date Created: <%= result.getDateCreated() %>
                </td></tr>
        <tr><td>
                Owner: <%= owner.getEmail() %>
                </td></tr>
    </table>
    <%-- start web service invocation --%><hr/>
    
    <%-- end web service invocation --%><hr/>
    </body>
</html>
