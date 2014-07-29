$(document).ready(function() {
	$("#transactions a[href='javascript:void(0)']").click(function() {
		var tr = $(this).closest('tr');
		var id = tr.children().eq(0).text();
		var str = tr.children().eq(3).text();
		var ticketNum = parseInt(str.split(" ")[1]);
		var fromStation = tr.children().eq(1).text();
		var toStation = tr.children().eq(2).text();
		$form = $("<form method='post' action='transaction.html'>"
			+ "<input name='orderId' value='"+id+"'></inpu></form>");
		if (ticketNum > 0 && ticketNum != NaN) {
			var r = confirm("Confirm to return "+ticketNum+" tickets from "
				+ "'"+fromStation+"' to '"+toStation+"'?");
			if (r == true) {
				$form.appendTo(document.body).submit();
			}
		}
	});
});