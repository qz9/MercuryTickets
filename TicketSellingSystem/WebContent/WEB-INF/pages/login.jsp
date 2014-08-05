<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
<link rel="stylesheet" type="text/css" href="css/login.css"/>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:700' rel='stylesheet' type='text/css'>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
</head>
<body class="back">
<div class="line">
	<form class="form-signin" action="<c:url value='j_spring_security_check'/>" method="POST">
		<img class="form-signin-heading" src="img/logo.png">
		<input class="f" type="text" class="form-control" placeholder="Username" name="j_username" id="j_username" required autofocus/>
		<input class="f" type="password" class="form-control" placeholder="Password" name="j_password" id="j_password" required/>
		<p class="alert" style="display:none;" id="wrongCredentials">The username or password supplied is incorrect</p>
		<button class="loginButton" type="submit">Sign in</button>
		<div class="form-signin-link">
			<a href="register.html">First time here? Click to register!</a>
		</div>
	</form>
</div>
<script>
	$(document).ready(function() {
		if ("<c:out value='${param.login_error}'/>" != "") {
		  	$('#wrongCredentials').show(1000);
		}	
	});
</script>
</body>
</html>