<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link rel='stylesheet' type='text/css' href='css/main.css'>
	<link rel='stylesheet' type='text/css' href='css/transaction.css'>
	<link rel='stylesheet' type='text/css' href='css/table.css'>
	<link rel='stylesheet' type='text/css' href='css/create_card.css'>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/payment.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	table {
		border-collapse:collapse;
	}
	table td, th {
		border: 1px solid grey;
	}
</style>
<title>Your Cards</title>
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
					<input type="text" value="${username }" disabled></input>
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
					<div>See your <a href="./transaction.html">orders</a>.</div>
				</fieldset>
			</div>
			<div id = "cards">
			<fieldset>
			<legend>My Cards</legend>
				<table>
					${cardsTableHTML}
				</table>
				<div id = "new_card">
					click <a href="javascript:void(0)" id="new_card_link">HERE</a> to create a new card.
				</div>
				<div id = "warning_message">
				</div>
				<div id = "new_card_div" style="display: none;">
					<form method="post" id="new_card_form" action="payment.html">
						<label for = "cardNumber">Card Number:</label>
						<input type = "text" name = "cardNumber" id="card_num_input"></input> <br />
						<label for="cardType">Card Type:</label>
						<select name="cardType">
							<option>Visa</option>
							<option>MasterCard</option>
							<option>American Express</option>
							<option>Discover</option>
							<option>Delta</option>
						</select> <br />
						<label for = "exMonth">Expiration Month:</label>
						<select name = "exMonth">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
						</select> <br />
						<label for = "exYear">Expiration Year:</label>
						<input type="text" name="exYear" id="ex_year_id"></input> <br />
						<div id = "new_card_buttons">
							<button id="new_card_cancel">Cancel</button>
							<button>Submit</button>
						</div>
					</form>
				</div>
			</fieldset>
			</div>
			<div style="clear:both;"></div>
		</div>
	</div>
</div>
</body>
</html>