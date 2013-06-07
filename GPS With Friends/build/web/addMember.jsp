<%-- 
    Document   : addMember
    Created on : Jun 2, 2013, 1:36:33 PM
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
    <body>
            <%-- start web service invocation --%><hr/>
    <%
        int gid =Integer.parseInt(session.getAttribute("gid").toString());
        edu.uoregon.cs.client.User user = new edu.uoregon.cs.client.User();
    try {
	edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
	edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
	// TODO process result here
	user = port.getUser(request.getParameter("emailUser"));
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    if (user == null) {
            out.println("That user does not exist!");
    } else {
    %>
    <%-- end web service invocation --%><hr/>
        
    
    <%-- start web service invocation --%><hr/>
    <%
        try {
            edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
            edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();

             // TODO initialize WS operation arguments here
            int uid =user.getUid();
            // TODO process result here
            edu.uoregon.cs.client.Status result = port.addMember(uid, gid);


            if (result.isSuccess()) {
                %>
               <jsp:forward page='group.jsp'>
                   <jsp:param name='message' value="Member added succesfully"/>
               </jsp:forward>
                <%
            }
            else {
                %>
               <jsp:forward page='group.jsp'>
                   <jsp:param name='message' value="<%= result.getError()%>"/>
               </jsp:forward>
                <%
            }
        } catch (Exception ex) {
        }
    }
    %>
    </body>
</html>
