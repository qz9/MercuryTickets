<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
</head>
<body>

	<div style="background-color:#00CC99;width:500px">
		<h1>Admin Page</h1>
	</div>

	<div style="background-color:#99FFCC;width:500px;">
	 
		<div style="margin-bottom:20px">
		<p><b>User Activities</b></p>
		Click to see <a href="ads_report.html">user statistics</a>.
		<p><b>Add Ticket Amount</b></p>
		Ticket ID: <input type="text" size="1" class="amount" name="id">
		Amount: <input type="text" size="4" class="amount" name="amount">
		<button id="addAmount">Add</button>
		</div>
		<div>
		<p><b>Add New Ticket</b></p>
		From: <select name="from" class="ticket">
				<option value=""></option>
				<option value="0">Princeton Junction</option>
				<option value="1">Hoboken Terminal</option>
				<option value="2">Penn Station</option>
				<option value="3">New Brunswick</option>
				<option value="4">Hudson</option>
				<option value="5">Buffalo-Depew</option>
			</select>
		To: <select name="to" class="ticket">
				<option value=""></option>
				<option value="0">Princeton Junction</option>
				<option value="1">Hoboken Terminal</option>
				<option value="2">Penn Station</option>
				<option value="3">New Brunswick</option>
				<option value="4">Hudson</option>
				<option value="5">Buffalo-Depew</option>
			</select><br/>
		Number of tickets: <input type="text" size="3" class="ticket" name="max">
		Price: <input type="text" size="5" class="ticket" name="price"><br/>
		Start time: <input type="datetime-local" size="4" class="ticket" name="start"><br/>
		Arrive time: <input type="datetime-local" size="3" class="ticket" name="arrive">
		<button id="addTicket" style="margin-left:20px">Add</button>
		</div>
	
	</div>
	
	<div style="background-color:#EEEEEE;width:500px;">
		<button id="display">Display</button>
		<table id="itemList" border="1" style="border-collapse:collapse">
			<tr>
				<th>Ticket ID</th>
				<th>From station</th>
				<th>To station</th>
				<th>Price</th>
				<th>Start time</th>
				<th>Arrive time</th>
				<th>Left</th>
			</tr>
			<tbody id="tickets">
			</tbody>
		</table>
	</div>
<a href="<c:url value='/j_spring_security_logout'/>">Logout</a>

<script>
	$(document).ready(function() {
		$("#itemList").hide();
		$("#display").click(function() {		
			$.ajax({
				url: "display",
				type: "post",
				dataType: "xml",
				success:showData
			});	
		});
		$("#addAmount").click(function() {
			var params=$(".amount").serialize(); 		
			$.ajax({
				url: "add",
				type: "get",
				dataType: "xml",
				data: params,
				success:addAmount
			});	
		});
		$("#addTicket").click(function() {
			var params=$(".ticket").serialize(); 		
			$.ajax({
				url: "add",
				type: "post",
				dataType: "xml",
				data: params,
				success:addTicket
			});	
		});
	});
	function addAmount(){}
	function addTicket(){}
	function showData(data) {
		var rows = "";
		$("#tickets").empty();
		$(data).find("ticket").each(function() {
			var id = $(this).find("id").text();
			var from = $(this).find("from").text();
			var to = $(this).find("to").text();
			var price = $(this).find("price").text();
			var start = $(this).find("start").text();
			var arrive = $(this).find("arrive").text();
			var left = $(this).find("left").text();
			rows = "<tr><td>" + id + "</td><td>" + from + "</td>" +
				"<td>" + to + "</td><td>" + price + "</td>" +
				"<td>" + start + "</td><td>" + arrive + "</td>" +
				"<td>" + left + "</td></tr>";
			$(rows).appendTo("#tickets");
		})	
		$("#itemList").fadeIn(500);
	}
</script>

</body>
</html>