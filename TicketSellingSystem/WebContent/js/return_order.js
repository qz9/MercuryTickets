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
	
	$(".ads_links").click(function() {
		var id = $(this).attr("id");
		var idNum = id.replace(/ads_links_/i, "");
		if (idNum == 1) {
			window.open("http://www.amtrak.com/");
		} else if (idNum == 2) {
			window.open("http://www.njtransit.com/");
		} else if (idNum == 3) {
			window.open("http://www.metrolinktrains.com/");
		}
		$form = $("<form method='post' action='stat.html'><input name='adsId' value='"+idNum+"'/></form>");
		$form.appendTo(document.body).submit();
	});
});