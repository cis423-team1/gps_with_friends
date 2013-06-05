<%-- 
    Document   : authenticate
    Created on : Jun 2, 2013, 11:54:33 AM
    Author     : ahmad
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
        <h1>Hello World!</h1>
        <%
            //handle log in request
            if (request.getParameter("formType").equals("login")) {
                try {
                    //send login request
                    edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
                    edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
                    java.lang.String email = request.getParameter("username");
                    java.lang.String password = request.getParameter("password");
                    edu.uoregon.cs.client.UserStatus result = port.authenticate(email, password);
                    if (result.isSuccess()) {
                        %>
                       <jsp:forward page='main.jsp'>
                           <jsp:param name='uid' value="<%=result.getUser().getUid()%>"/>
                       </jsp:forward>
                        <%
                    }
                    else {
                        %>
                       <jsp:forward page='index.jsp'>
                           <jsp:param name='message' value="<%= result.getError()%>"/>
                       </jsp:forward>
                        <%
                    }
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
            } 
            //handle register request
            else {
                try {
                    edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
                    edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
                     // TODO initialize WS operation arguments here
                    java.lang.String email = request.getParameter("email");
                    java.lang.String password = request.getParameter("pass");
                    java.lang.String fname = request.getParameter("fName");
                    java.lang.String lname = request.getParameter("lName");
                    // TODO process result here
                    edu.uoregon.cs.client.Status result = port.register(email, password, fname, lname);
                    if (result.isSuccess()) {
                        %>
                       <jsp:forward page='index.jsp'>
                           <jsp:param name='message' value="Registered Succesfully"/>
                       </jsp:forward>
                        <%
                    }
                    else {
                        %>
                       <jsp:forward page='index.jsp'>
                           <jsp:param name='message' value="<%= result.getError()%>"/>
                       </jsp:forward>
                        <%
                    }
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
            }
            %>
    </body>
</html>
