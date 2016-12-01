<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://cdn.gbtags.com/summernote/0.5.2/summernote.min.js"></script>
<style type="text/css">
   	@import url('http://cdn.gbtags.com/font-awesome/4.1.0/css/font-awesome.min.css');
    @import url('http://cdn.gbtags.com/summernote/0.5.2/summernote.css');
</style>
<style type="text/css">
table {
	text-align: center overflow: hidden;
}

th {
	text-align: center;
	overflow: hidden;
	height: 50px;
}

tr {
	text-align: center;
	overflow: hidden;
	height: 50px;
}

.inputBorder {
	border: 1px;
	background: transparent;
	border-style: none;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>
				图文信息
			</td>
			<td>
				<div class="tab-pane fade" id="article">
				<form
					action="${pageContext.request.contextPath}/resources/addEntity.shtml"
					method="post" enctype="multipart/form-data">
					<div class="input-group input-group-lg">
						<input type="text" class="form-control inputBorder"
							placeholder="请在这里输入标题" style="width: 800px;"> <br>
					</div>
					<div class="input-group input-group-sm">
						<input type="text" class="form-control inputBorder"
							placeholder="请输入作者" style="width: 800px;">
					</div>
					<hr>
					<div id="editor">
					</div>
					<hr>
					<input type="checkbox" id="checkbox1" />原文链接
					<br>
					<input type="text" id="orgUrlInp" style="display:none; width: 300px" />
					<label>封面</label>&nbsp;&nbsp;<label style="color: #808080">大图片建议尺寸：900像素 * 500像素</label>
					<br>
					<button class="btn btn-info">从正文选择</button>
					<button class="btn btn-info">从图片库选择</button>
					<br><br>
					<label>摘要</label>&nbsp;&nbsp;<label style="color: #808080">选填，如果不填写会默认抓取正文前54个字</label>
					<br>
					<textarea rows="8" cols="97"></textarea>
					<input type="submit" value="保存">
				</form>
			</div>
			</td>
			<td>
			
			</td>
		</tr>
	</table>
	
</body>
<script type="text/javascript">
	$(function(){
		$('#editor').summernote({
			toolbar:[
				['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']]
			],
			width: 800,
			height: 300,                 // set editor height
			minHeight: null,             // set minimum height of editor
			maxHeight: null,             // set maximum height of editor
			focus: true                  // set focus to editable area after initializing summernote
		});
		$('#checkbox1').click(function(){
			var check = $('#checkbox1').is(':checked');
			if(check == true){
		    	$('#orgUrlInp').show();
			}else{
		    	$('#orgUrlInp').hide();
			}
		});
	});
</script>
</html>