<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>GPS Tracking System</title>
</head>
<body>
	<h1>Welcome to GPS with Friends!</h1><br><h2>WARNING: ALL PASSWORDS ARE STORED IN PLAINTEXT IN THE DATABASE! DO NOT USE A REAL PASSWORD!</h2><hr>
	<div id="startForms">
            <%
                out.println("<h4>"+request.getParameter("message")+"<h4>");
            %>
		<form id="loginForm" action="authenticate.jsp" method="POST">
			<label for="username">Email: </label><input type="text" name="username"><br>
			<label for="password">Password: </label><input type="text" name="password"><br>
                        <input type="hidden" name="formType" value="login">
			<input type="submit">
		</form>
		<form id="registrationForm" action="authenticate.jsp" method="POST">
			First Name: <input type="text" name="fName"><br>
			Last Name: <input type="text" name="lName"><br>
			Email: <input type="text" name="email"><br>
			Password: <input type="password" name="pass"><br>
			Verify Password: <input type="password" name="vPass"><br>
                        <input type="hidden" name="formType" value="register">
			<input type="submit">
		</form>
		<input type="button" id="switch" onclick="startFormsToggle()" value="Register">
		<script>
			function startFormsToggle(){
				if($('#loginForm').is(":visible")){
					$('#loginForm').toggle();
					$('#registrationForm').toggle();	
					$('#switch').val('Login');
				}
				else{
					$('#registrationForm').toggle();
					$('#loginForm').toggle();
					$('#switch').val('Register');
				}
			}
		</script>
	</div>
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
</body>
</html>