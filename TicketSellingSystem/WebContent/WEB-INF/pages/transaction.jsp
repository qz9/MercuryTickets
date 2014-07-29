<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link rel='stylesheet' type='text/css' href='css/main.css'>
	<link rel='stylesheet' type='text/css' href='css/transaction.css'>
	<link rel='stylesheet' type='text/css' href='css/table.css'>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	table {
		border-collapse:collapse;
	}
	table td, th {
		border: 1px solid grey;
	}
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="js/return_order.js"></script>
<title>Your Transaction</title>
</head>
<body>
<div class="hidden">
		<div class="wrapper">
		<header>
			<img src="./img/logo.png" height="60px">
			<div id = "login_register_link">
				Hello, ${username }!
				<a href="<c:url value='/j_spring_security_logout'/>">Logout</a>
			</div>
			<div style="clear:both;"></div>
		</header>
		<nav id="cssmenu">
			<ul>
				<li><a href="./hello.html">Home</a></li>
				<li><a href="./stations">Stations</a></li>
				<li><a href="./about">About Us</a></li>
				<li><a href="./contact">Contact</a></li>
			</ul>
		</nav>
		<div id = "transaction_payment_wrapper">
			<div id = "personalInfo">
				<fieldset>
					<legend>My Account</legend>
					<label for="username">Username:</label>
					<input type="text" name="username" value="${username }" disabled></input>
					<label for="email">Email:</label>
					<input type="text" name="email" value="${email }" disabled></input>
					<label for="firstName">First Name:</label>
					<input type="text" name="firstName" value="${firstName }" disabled></input>
					<label for="lastName">Last Name:</label>
					<input type="text" name="lastName" value="${lastName }" disabled></input>
					<label for="street">Street:</label>
					<input type="text" name="street" value="${street }" disabled></input>
					<label for="state">State:</label>
					<input type="text" name="state" value="${state }" disabled></input>
					<label for="zipCode">Zip Code:</label>
					<input type="text" name="zipCode" value="${zipCode }" disabled></input>
					<div>See your <a href="./payment.html">payment information</a>.</div>
				</fieldset>
			</div>
			<div id = "transactions">
			<fieldset>
			<legend>My Orders</legend>
				<table>
					${ordersTableHTML}
				</table>
			</fieldset>
			</div>
			<div style="clear:both;"></div>
		</div>
	</div>
</div>
</body>
</html>