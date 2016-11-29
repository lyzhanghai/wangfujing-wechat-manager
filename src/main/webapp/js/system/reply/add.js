$(function() {
	$("li").click(function() {
		alert("aa");
		var index = $(this).index();
		alert(index);
	});
});