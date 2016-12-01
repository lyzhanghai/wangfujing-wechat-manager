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

.textareaBorder {
	border: 1px;
	background: transparent;
	border-style: none;
}

</style>
</head>
<body>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#home" data-toggle="tab">被关注后自动回复</a></li>
		<li><a href="#ios" data-toggle="tab">消息自动回复</a></li>
		<li><a href="#java" data-toggle="tab">关键词自动回复</a></li>
	</ul>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="home">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#txt" data-toggle="tab">文本</a></li>
				<!-- ${pageContext.request.contextPath}/upload/articleAdd.shtml -->
				<li><a href="#article" data-toggle="tab">图文</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade in active" id="txt">
					<div id="editor">
					</div>
					<br><br><br>
					<button type="submit" class="btn btn-default">保存</button>
				</div>
				<div class="tab-pane fade" id="article">
					<button class="btn btn-success" style = "float: right; margin-right: 150px;">新建图文信息</button>
				</div>
				<%-- <div class="tab-pane fade" id="article">
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
						<textarea rows="8" cols="97"></textarea>
					</form>
				</div> --%>
			</div>
		</div>
		<div class="tab-pane fade" id="ios">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#txt" data-toggle="tab">文本</a></li>
				<li><a href="#article" data-toggle="tab">图文</a></li>
			</ul>
		</div>
		<div class="tab-pane fade" id="java">
			<div>
				<button type="button" class="btn btn-success">新建规则</button>
				<br> <br> <br>
				<table
					class="table table-striped table-bordered table-hover table-condensed">
					<tr>
						<th>规则名称</th>
						<th>关键字</th>
						<th>回复消息类型</th>
						<th>操作</th>
					</tr>
					<tr>
						<td>规则名称</td>
						<td>关键字</td>
						<td>回复消息类型</td>
						<td>操作</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
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

