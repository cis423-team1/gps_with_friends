<%-- 
    Document   : addMember
    Created on : Jun 2, 2013, 1:36:33 PM
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
        edu.uoregon.cs.client.User user = new edu.uoregon.cs.client.User();
    try {
	edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
	edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
	 // TODO initialize WS operation arguments here
	java.lang.String email = "";
	// TODO process result here
	user = port.getUser(request.getParameter("emailUser"));
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    
    <form action="listGroupMembers.jsp" method="POST">
        
    
    <%-- start web service invocation --%><hr/>
    <%
    try {
	edu.uoregon.cs.client.GPSwfriends_Service service = new edu.uoregon.cs.client.GPSwfriends_Service();
	edu.uoregon.cs.client.GPSwfriends port = service.getGPSwfriendsPort();
        
	 // TODO initialize WS operation arguments here
	int uid =user.getUid()  ;
	int gid =Integer.parseInt(request.getParameter("hiddenGName"));
	// TODO process result here
	edu.uoregon.cs.client.Status result = port.addMember(uid, gid);

        
        if (result.isSuccess())
        {
           System.out.println("The invitation happened successfully.");
            System.out.println("<input type='hidden' name='glist' value='"+gid+"'>");
        }
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <input type ="submit" value="back" />
    <%-- end web service invocation --%><hr/>

    </form>
    </body>
</html>
