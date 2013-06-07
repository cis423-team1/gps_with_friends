<%-- 
    Document   : deleteGroup
    Created on : Jun 6, 2013, 4:39:43 PM
    Author     : Andrew
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
    if (request.getParameter("delete").equals("yes")) {
        try {
            edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
            edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
             // TODO initialize WS operation arguments here
            int gid = Integer.parseInt(session.getAttribute("gid").toString());
            // TODO process result here
            edu.uoregon.cs.client.Status result = port.deleteGroup(gid);
            if (result.isSuccess()){
                out.println("Group has been succesfully deleted");
            %>
            <form name="back" action="main.jsp">
                    <input type="submit" value="Back"/>
            </form>
    <%
            } else {
                out.println(result.getError());
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
    } else {
        out.println("You do not have permission to delete this group. If you think you do have permission please go to the group page and use the delete button their");
    }
    %>
    <%-- end web service invocation --%><hr/>
    </body>
</html>

