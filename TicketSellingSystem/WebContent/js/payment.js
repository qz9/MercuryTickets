var arr_cards = [];
var trHTML = "";
$(document).ready(function() {
	$("#cards table tr").on("click", "#update_card_submit", function() {
		var tr = $(this).closest("tr");
		if (verifyUpdate(tr)) {
			$("#card_update_form").submit();
		}
	});
	
//	$(".update_card_link").click(function() {
	$("#cards table tr").on("click", ".update_card_link", function() {
		var id = $(this).attr('id');
		var id_num = id.replace(/update_card_/i, '');
		var cardNum = arr_cards[id_num];
		var tr = $(this).closest('tr');
		trHTML = tr.html();
		var exDate = tr.children().eq(2).text();
		var cardType = tr.children().eq(3).text();
		var month = parseInt(exDate.split('-')[0]);
		var year = parseInt(exDate.split('-')[1]);

		$(".update_card_link").each(function() {
			$(this).text("");
		});
		$(".delete_card_link").each(function() {
			$(this).text("");
		});

		var table = $(tr).closest('table');
		table.wrap("<form method='post' action='payment.html' id='card_update_form'></form>");
		//console.log($("#hahaha").html());
		$("#card_number_"+id_num).html("<input type='text' name='cardNum' value='"+
			cardNum+"'>"+"</input><input type='text' name='originalCardNum' value='"+
			cardNum+"' style='display:none;'></input>");
		tr.children().eq(1).html("");
		var monthHTMLStr = "<select name='exMonth'><option>1</option><option>2</option>"+
		"<option>3</option><option>4</option><option>5</option><option>6</option>"+
		"<option>7</option><option>8</option><option>9</option><option>10</option>"+
		"<option>11</option><option>12</option></select>";
		var yearHTMLStr = "<input name='exYear' value='"+year+"'></input>";
		tr.children().eq(2).html(monthHTMLStr+yearHTMLStr);
		var cardTypeHTMLStr = "<select name='card_type'><option>Visa</option>"+
		"<option>MasterCard</option><option>American Express</option>"+
		"<option>Discover</option><option>Delta</option></select>";
		tr.children().eq(3).html(cardTypeHTMLStr);
		$("select[name='exMonth']").val(month);
		$("select[name='card_type']").val(cardType);

		tr.children().eq(4).html("<a href='javascript:void(0)' "+
			"id='update_card_cancel'>Cancel</a> <a href='javascript:void(0)' "+
			"id='update_card_submit'>Submit</a>");
	});

	$("#cards table tr").on('click', '#update_card_cancel', function() {
		var table = $(this).closest("table");
		if (table.parent().is("form")) {
			table.unwrap();
		}
		$(".update_card_link").each(function() {
			$(this).text("Update");
		});
		$(".delete_card_link").each(function() {
			$(this).text("Delete");
		});
		$(this).closest('tr').html(trHTML);
	});

	$('#cards table tr').on('click', '#update_card_submit', function(event) {

	});
	
//	$(".delete_card_link").click(function() {
	$("#cards table tr").on("click", ".delete_card_link", function() {
		var r = confirm("Confirm to Delete this Credit Card?");
		if (r == true) {
			var id = $(this).attr('id');
			var id_num = id.replace(/delete_card_/i, '');
			$("<form method='post' action='payment.html'><input name='cardNumDelete' value='"
					+arr_cards[id_num]+"'></input></form>").appendTo("body").submit();
		}
	});
	
	var created = getParameterByName('card');
	if (created == 'success') {
		showWarningMsg("New Card Added!", 2000);
	} else if (created == 'fail') {
		showWarningMsg("Card Already Existed!", 2000);
	} else if (created == 'deleted') {
		showWarningMsg("Card Deleted!", 2000);
	} else if (created == 'updated') {
		showWarningMsg("Card Info Updated!", 2000);
	}
	
	$(".card_number").each(function() {
		var origin = $(this).text();
		var length = origin.length;
		var last4 = origin.substring(length - 4, length);
		var id = $(this).attr('id');
		var index = id.replace(/card_number_/g, "");
		arr_cards[index] = origin;
		var new_card_num = Array(length-4).join('*') + last4;
		$(this).text(new_card_num);
	});

//	$(".show_card").click(function(){
	$("#cards").on("click", ".show_card", function() {
		var id = $(this).attr('id');
		var id_num = id.replace(/show_card_/i, '');
		var card_id = "#card_number_" + id_num;
		if ($(this).text() == 'Show Card') {
			var r = confirm("Confirm to Show Card Number?");
			if (r == true) {
				var origin = arr_cards[id_num];
				$(card_id).text(origin);
				$(this).text('Hide Card');
			}
		} else if ($(this).text() == 'Hide Card') {
			var origin = $(card_id).text();
			var length = origin.length;
			var last4 = origin.substring(length - 4, length);
			var new_card_num = Array(length-4).join('*') + last4;
			$(card_id).text(new_card_num);
			$(this).text('Show Card');
		}
	});

	$("#new_card_link").click(function() {
		$("#new_card").css("display", "none");
		$("#new_card_div").css("display", "inline");
	});

	$("#card_num_input").blur(function() {
		verifyNumAndYear();
	});

	$("#ex_year_id").blur(function() {
		verifyNumAndYear();
	});

	$("#new_card_cancel").click(function(event) {
		event.preventDefault();
		$("#warning_message").text(" ");
		$("#new_card_div").css("display", "none");
		$("#new_card").css("display", "inline");
		$("#new_card_form")[0].reset();
	});

	$("#new_card_form").submit(function(event) {
		if (verifyNumAndYear() == true) {
			//$("#new_card_form").submit();
			return true;
		}
		event.preventDefault();
		return false;
	});
	
	$("#card_num_input").keypress(function(event) {
		if (event.which == 13) {
			event.preventDefault();
			if (verifyNumAndYear() == true) {
				$("#new_card_form").submit();
			}
		}
	});
	
	$("#ex_year_id").keypress(function(event) {
		if (event.which == 13) {
			event.preventDefault();
			if (verifyNumAndYear() == true) {
				$("#new_card_form").submit();
			}
		}
	});
});

function showWarningMsg(str, num) {
	$("#warning_message").text(" ");
	$("#warning_message").css("visibility", "visible");
	$("#warning_message").text(str).show().fadeOut(num, function() {
		$(this).show().css({visibility: "hidden"});
	});
}

verifyUpdate = function(tr) {
	var cardNum = tr.children().eq(0).find("input").val();
	var year = tr.children().eq(2).find("input").val();
	var result = parseInt(year);
	var current_year = new Date().getFullYear();
	if (!luhnCheck(cardNum)) {
		showWarningMsg("Invalid Card Number!", 2000);
		return false;
	} else if (result == NaN || result <= current_year || result >= 3000
			|| result.toString().length != 4) {
		showWarningMsg("Invalid Year!", 2000);
		return false;
	} else {
		$("#warning_message").text(" ");
		return true;
	}
};

verifyNumAndYear = function() {
	var card_num = $("#card_num_input").val();
	var year = $("#ex_year_id").val();

	var current_year = new Date().getFullYear();
	var result = parseInt(year);
	if (!luhnCheck(card_num)) {
		$("#warning_message").text(" ");
		$("#warning_message").css("visibility", "visible");
		$("#warning_message").text("Invalid Card Number!").show().fadeOut(2000, function() {
			$(this).show().css({visibility: "hidden"});
		});
		return false;
	} else if (result == NaN || result <= current_year || result >= 3000
			|| result.toString().length != 4) {
		$("#warning_message").text(" ");
		$("#warning_message").css("visibility", "visible");
		$("#warning_message").text("Invalid Year!").show().fadeOut(2000, function() {
			$(this).show().css({visibility: "hidden"});
		});
		return false;
	} else {
		$("#warning_message").text(" ");
		return true;
	}
};

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
};

luhnCheck = function(card_num) {
	//console.log(card_num);
	if (card_num.length != 16) {
		return false;
	}
	if (/[^0-9-\s]+/.test(card_num)) return false;
	// The Luhn Algorithm. It's so pretty.
	var nCheck = 0, nDigit = 0, bEven = false;
	nDigit;
	card_num = card_num.replace(/\D/g, "");
	for (var n = card_num.length - 1; n >= 0; n--) {
		var cDigit = card_num.charAt(n),
			  nDigit = parseInt(cDigit, 10);
 
		if (bEven) {
			if ((nDigit *= 2) > 9) nDigit -= 9;
		}
 
		nCheck += nDigit;
		bEven = !bEven;
	}
	return (nCheck % 10) == 0;
};
