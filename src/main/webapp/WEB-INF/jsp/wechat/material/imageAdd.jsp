<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="form1" name="form1" enctype="multipart/form-data">
		<div style="width: 100%;height: 100%;">
			<div id="fileID">
				<input id="fileId" type="file" name="file" accept="image/jpeg,image/png,image/gif" />
				<img id="preview_size_fake"/>
	        </div>
	        <div style="position:absolute; right:0px; bottom:0px; width:100px; height:100px;">
	        	<input type="hidden" value="image" id="fileType" name="fileType">
	        	<button id="sub" type="button">保存</button>
	        </div>
        </div>
	</form>
</body>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/assets/global/plugins/bootstrap/js/bootstrap.min.js"
        type="text/javascript"></script>
<script type="text/javascript">
	$('#fileId').change(function (){
		var objPreview = document.getElementById('preview_size_fake');
		alert(objPreview);
		var sender = document.getElementById('fileId');
		objPreview.style.display = 'block';
	    objPreview.style.width = '200px';
	    objPreview.style.height = '200px';
		objPreview.src = window.URL.createObjectURL(sender.files[0]);
	});
	function fileChange(){
		var objPreview = document.getElementById('preview_size_fake');
		alert(objPreview);
		var sender = document.getElementById('fileId');
		objPreview.style.display = 'block';
	    objPreview.style.width = '200px';
	    objPreview.style.height = '200px';
		objPreview.src = window.URL.createObjectURL(sender.files[0]);
	}
	$('#sub').bind('click',function(){
		var formData = new FormData($( "#form1" )[0]);
		$.ajax({
		    type: 'post',
		    url: '${pageContext.request.contextPath}/material/imageAdd/upload.shtml',
		    data: formData,  
	        async: false,  
	        cache: false,  
	        contentType: false,  
	        processData: false,
		    success: function() {
	    		alert("上传成功");
	    		$('#fileID *').remove();
	    		var img = '<input id="fileId" type="file" name="file" onchange="fileChange();" accept="image/jpeg,image/png,image/gif" />' + 
	    		'<img id="preview_size_fake"/>';
	    		$("#fileID").append(img);
		    }
		});
	});
</script>
</html>