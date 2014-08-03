<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="css/register.css"/>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:700' rel='stylesheet' type='text/css'>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
</head>
<body class="back">
	<div class="line">
		<form method="POST" id="register-form">
			<h2>CREATE YOUR ACCOUNT</h2>
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title">Personal Information</h3>
				</div>
				<div class="panel-body">
					<table>
						<tr>
							<td>Username:</td>
							<td><input type="text" name="username" class="field"
								size="15" id="un" maxlength="20"/><span id="userExist">Username exists.</span></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" name="password" class="field"
								size="15" id="pwd" maxlength="50"/></td>
						</tr>
						<tr>
							<td>Retype password:</td>
							<td><input type="password" size="15" id="rep" maxlength="50"/><span id="notMatch">
							Passwords don't match!</span></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><input type="email" name="email" class="field" size="15"
								id="email" onblur="validateEmail()" maxlength="40"/> <span id="invalidEmail">Invalid
									email.</span><span id="emailExist">Email exists.</span></td>
						</tr>
						<tr>
							<td>Phone:</td>
							<td><input type="text" name="phone" class="field" size="10"
								id="phn" maxlength="10"/></td>
						</tr>
						<tr>
							<td>First Name:</td>
							<td><input type="text" name="firstName" class="field"
								size="20" id="fn" maxlength="20"/></td>
						</tr>
						<tr>
							<td>Last Name:</td>
							<td><input type="text" name="lastName" class="field"
								size="20" id="ln" maxlength="20"/></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title">Address Information</h3>
				</div>
				<div class="panel-body">
					<table>
						<tr>
							<td>Address:</td>
							<td><input type="text" name="street" class="field" size="30"
								id="st" maxlength="20"/></td>
						</tr>
						<tr>
							<td>City:</td>
							<td><input type="text" name="city" class="field" size="10"
								id="city" maxlength="20"/></td>
						</tr>
						<tr>
							<td>State:</td>
							<td><select name="state" class="field" id="sl">
									<option value=""></option>
									<option value="AL">AL</option>
									<option value="AK">AK</option>
									<option value="AZ">AZ</option>
									<option value="AR">AR</option>
									<option value="CA">CA</option>
									<option value="CO">CO</option>
									<option value="CT">CT</option>
									<option value="DE">DE</option>
									<option value="FL">FL</option>
									<option value="GA">GA</option>
									<option value="HI">HI</option>
									<option value="ID">ID</option>
									<option value="IL">IL</option>
									<option value="IN">IN</option>
									<option value="IA">IA</option>
									<option value="KS">KS</option>
									<option value="KY">KY</option>
									<option value="LA">LA</option>
									<option value="ME">ME</option>
									<option value="MD">MD</option>
									<option value="MA">MA</option>
									<option value="MI">MI</option>
									<option value="MN">MN</option>
									<option value="MS">MS</option>
									<option value="MO">MO</option>
									<option value="MT">MT</option>
									<option value="NE">NE</option>
									<option value="NV">NV</option>
									<option value="NH">NH</option>
									<option value="NJ">NJ</option>
									<option value="NM">NM</option>
									<option value="NY">NY</option>
									<option value="NC">NC</option>
									<option value="ND">ND</option>
									<option value="OH">OH</option>
									<option value="OK">OK</option>
									<option value="OR">OR</option>
									<option value="PA">PA</option>
									<option value="RI">RI</option>
									<option value="SC">SC</option>
									<option value="SD">SD</option>
									<option value="TN">TN</option>
									<option value="TX">TX</option>
									<option value="UT">UT</option>
									<option value="VT">VT</option>
									<option value="VA">VA</option>
									<option value="WA">WA</option>
									<option value="WV">WV</option>
									<option value="WI">WI</option>
									<option value="WY">WY</option>
							</select></td>
						</tr>
						<tr>
							<td>Zip Code:</td>
							<td><input type="text" name="zip_code" class="field"
								size="5" id="zip" maxlength="5"/></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title">Payment Information</h3>
				</div>
				<div class="panel-body">
					<table>
						<tr>
							<td><input type="radio" name="cardType" value="Visa">Visa<br />
								<input type="radio" name="cardType" value="Master Card">Master
								Card<br /> <input type="radio" name="cardType"
								value="American Express">American Express<br /> <input
								type="radio" name="cardType" value="Discover">Discover<br />
								<input type="radio" name="cardType" value="Delta">Delta<br />
								<input type="radio" name="cardType" value="None">None</td>
						</tr>
						<tr>
							<td>Card Number:</td>
							<td><input id="vc" type="text" name="cardNumber"
								class="card" size="16" onblur="wrapperValid()" maxlength="16"/> <span
								id="invalidCC">Invalid credit card.</span></td>
						</tr>
						<tr>
							<td>Expiration Date:</td>
							<td><input placeholder="MM" type="text" name="exMonth"
								class="card" size="2" maxlength="2"> <input placeholder="YYYY"
								type="text" name="exYear" class="card" size="4" maxlength="4"></td>
						</tr>
					</table>
				</div>
			</div>
			<p class="alert" style="display: none;" id="missingInfo">Please
				fill in the missing information.</p>
			<button type="reset" class="butt">Clear</button>
			<button type="submit" class="butt">Register</button>
		</form>
	</div>

	<script>
	$(document).ready(function() {
		$("#register-form").on("submit", loginValidation);
		$("#un").on("blur", function() {
			$("#userExist").hide();
			$.ajax({
				url: "validation",
				type: "get",
				dataType: "html",
				data: {username: $("#un").val()},
				success: function(response) {
					var result = response.toString().trim();
					if (result=="true") {
						$("#userExist").show(500);
					}
				},
				error: function(msg) {
					alert(msg);
				}
			});
		});
		$("#rep").on("blur", function() {
			$("#notMatch").hide();
			if ($("#rep").val() != $("#pwd").val()) {
				$("#notMatch").show(500);
			}
		});
	});
	
	function validateEmail() {
		$("#invalidEmail").hide();
		var email = $("#email").val();
		var index = email.indexOf("@");
		if (index <= 0 || index >= email.length-1 || index != email.lastIndexOf("@")){
			$("#invalidEmail").show(500);
			return;
		}
		$.ajax({
			url: "validation",
			type: "post",
			dataType: "html",
			data: {email: $("#email").val()},
			success: function(response) {
				var result = response.toString().trim();
				if (result=="true") {
					$("#emailExist").show(500);
				}
			},
			error: function(msg) {
				alert(msg);
			}
		});
			
	};

	function loginValidation() {
		$("#missingInfo").hide();
		$(".field").css("border-color", "initial");
		$(".card").css("border-color", "initial");
		if ($('input:radio[name=cardType]:checked').val() != "None") {
			for ( var i = 0; i <= 2; i++ ) {
				if($(".card").eq(i).val().length == 0) {
					$(".card").eq(i).css("border-width", "1px");
			  		$(".card").eq(i).css("border-color", "red");
				};
			};
		}
		for ( var i = 0; i <= 9; i++ ) {
			if($(".field").eq(i).val().length == 0) {
				$(".field").eq(i).css("border-width", "1px");
		  		$(".field").eq(i).css("border-color", "red");
			};
		}
		if ($('input:radio[name=cardType]:checked').val() != "None") {
			for ( var i = 0; i <= 2; i++ ) {
				if($(".card").eq(i).val().length == 0) {
			  		$("#missingInfo").show(1000);
			  		return false;
				};
			};
		}
		for ( var i = 0; i <= 9; i++ ) {
			if($(".field").eq(i).val().length == 0) {
		  		$("#missingInfo").show(1000);
		  		return false;
			};
		}
	  	return true;
	};
	
	function wrapperValid() {
		$("#invalidCC").hide();
		var result = validateCCN($("#vc").val());
		if (!result) {
			$("#invalidCC").show(500);
		};
	}
	
	function validateCCN(ccNumb) {
		var valid = "0123456789";
		var len = ccNumb.length;
		var iCCN = parseInt(ccNumb);
		var sCCN = ccNumb.toString();
		sCCN = sCCN.replace (/^\s+|\s+$/g,'');
		var iTotal = 0;
		var bNum = true;
		var bResult = false;
		var temp;
		var calc;
		for (var j=0; j<len; j++) {
		  temp = "" + sCCN.substring(j, j+1);
		  if (valid.indexOf(temp) == "-1"){bNum = false;};
		}
		if(!bNum){
		  bResult = false;
		}
		if((len == 0)&&(bResult)){
		  bResult = false;
		} else{
		  if(len >= 15){
		    for(var i=len;i>0;i--){
		      calc = parseInt(iCCN) % 10;
		      calc = parseInt(calc);
		      iTotal += calc;
		      i--;
		      iCCN = iCCN / 10;           
		      calc = parseInt(iCCN) % 10; 
		      calc = calc*2;
		      switch (calc){
		        case 10: calc = 1; break;   
		        case 12: calc = 3; break;     
		        case 14: calc = 5; break;   
		        case 16: calc = 7; break;   
		        case 18: calc = 9; break;     
		        default: calc = calc;         
		      }                                            
		    iCCN = iCCN / 10; 
		    iTotal += calc; 
		  } 
		  if ((iTotal%10)==0){ 
		    bResult = true; 
		  } else {
		    bResult = false; 
		    };
		  };
		}
		  return bResult; 
		};
</script>
</body>
</html>