<%-- 0
    Document   : main
    Created on : Jun 2, 2013, 11:54:16 AM
    Author     : ahmad
--%>

<%-- 
    Document   : index
    Created on : May 18, 2013, 12:16:51 PM
    Author     : ahmad
--%>

<%@page import="edu.uoregon.cs.client.Location"%>
<%@page import="java.lang.Long"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
    
<h1>Pick a group to view</h1>
    <%-- start web service invocation --%><hr/>
    <%
        java.util.List<edu.uoregon.cs.client.Group> result = new java.util.ArrayList<edu.uoregon.cs.client.Group> () ;
    try {
	edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
	edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
	 // TODO initialize WS operation arguments here
	int uid = Integer.parseInt(request.getParameter("uid"));
	// TODO process result here
	result = port.getGroups(uid);
        
    } catch (Exception ex) {
	
        out.println(ex.getMessage());
    }
     if (result.size() < 1) {
            out.println("You do not belong to any groups!");
    } else {
    %>
    
    <form action="group.jsp" method="POST">
        <select name="glist">
    <%
   
    for (int i = 0;i<result.size();i++)
    {
        edu.uoregon.cs.client.Group g = result.get(i);
        out.println("<option value='" + g.getGid() + "'> "+ g.getName() + "</option>");
    }
    
    
    %>
        </select>
    <input type="submit" value="View Group" />

    
    <%-- end web service invocation --%><hr/>
    </form>
    
   <% } %>
   
   <h2>Create a Group</h2>
   
   <div id="startForms">
            <%
                String message = "";
                if (request.getParameter("message") != null) {
                    message = request.getParameter("message");
                }
                out.println("<h4>"+message+"<h4>");
            %>
		<form id="createGroupForm" action="createGroup.jsp" method="POST">
			Group Name: <input type="text" name="groupName"><br>
                        <input type="hidden" name="uid" value="<%= request.getParameter("uid") %>">
			<input type="submit" value="Create a Group">
		</form>
	</div>
    
    </body>
</html>
