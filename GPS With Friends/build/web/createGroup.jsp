<%-- 
    Document   : createGroup
    Created on : Jun 3, 2013, 10:29:56 PM
    Author     : Andrew
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            
            try {
                edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
                edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
                 // TODO initialize WS operation arguments here
                java.util.List<java.lang.Integer> uids = new java.util.ArrayList<java.lang.Integer>();
                int owner = ((edu.uoregon.cs.client.User)session.getAttribute("user")).getUid();
                uids.add(owner);
                java.lang.String name = request.getParameter("groupName");
                // TODO process result here
                edu.uoregon.cs.client.Status result = port.createGroup(uids, owner, name);
                out.println("Result = "+result);
            
            if (result.isSuccess()) {
                %>
               <jsp:forward page='main.jsp'>
                   <jsp:param name='message' value="Group Created Succesfully"/>
               </jsp:forward>
                <%
            }
            else {
                %>
               <jsp:forward page='main.jsp'>
                   <jsp:param name='message' value="<%= result.getError()%>"/>
               </jsp:forward>
                <%
            }
            } catch (Exception ex) {
                out.println("exception: " + ex.getMessage());
            }
            %>
    </body>
</html>
