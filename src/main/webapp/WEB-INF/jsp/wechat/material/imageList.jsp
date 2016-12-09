<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://cdn.gbtags.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.gbtags.com/twitter-bootstrap/3.2.0/js/bootstrap.js"></script>
<script src="http://cdn.gbtags.com/summernote/0.5.2/summernote.min.js"></script>
<style type="text/css">
	@import url('http://cdn.gbtags.com/twitter-bootstrap/3.2.0/css/bootstrap.css');
    @import url('http://cdn.gbtags.com/font-awesome/4.1.0/css/font-awesome.min.css');
    @import url('http://cdn.gbtags.com/summernote/0.5.2/summernote.css');
</style>
</head>
<body>
	<div>
		<div id="imageDiv">
			<c:forEach var="material" items="${materialList}">
				<div id="wrap" style="float: left;width:20%;text-align: center;">
					<img alt="" src="${material.url}" style="height: 140px;width: 140px;">
					<input type="hidden" id="${material.media_id}" value="${material.url}" />
					<br>
					<input type="radio" value="${material.media_id}" name="image" />
				</div>
			</c:forEach>
		</div>
		<div>
			<button type="button" class="btn btn-success" id="yes_btn">确定</button>
		</div>
	</div>
</body>
<script type="text/javascript">
	$('#yes_btn').click(function(){
		var mediaId = $('#wrap input[name="image"]:checked ').val();
		alert($('#'+ mediaId).val());
		parent.$("#mediaId").attr("value",mediaId);
	});
</script>
</html>