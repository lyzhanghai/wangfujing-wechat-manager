<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建图文素材</title>
<script src="http://cdn.gbtags.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.gbtags.com/twitter-bootstrap/3.2.0/js/bootstrap.js"></script>
<script src="http://cdn.gbtags.com/summernote/0.5.2/summernote.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/layer-v1.9.2/layer/layer.js"></script>
<style type="text/css">
	@import url('http://cdn.gbtags.com/twitter-bootstrap/3.2.0/css/bootstrap.css');
    @import url('http://cdn.gbtags.com/font-awesome/4.1.0/css/font-awesome.min.css');
    @import url('http://cdn.gbtags.com/summernote/0.5.2/summernote.css');
</style>
<style type="text/css">
table {
	overflow: hidden;
}

tr {
	overflow: hidden;
	height: 50px;
}

.inputBorder {
	border: 1px;
	background: transparent;
	border-style: none;
}
.width {
	width:50%;
}
.zd {
	position:fixed; 
	_position:absolute;
	_top:expression(eval(document.documentElement.scrollTop));
	z-index:1000;
	top:0;
}
.zhenzhao{
	background-color: #000000; 
	filter:alpha(opacity=10);
 	-moz-opacity:0.5;
 	opacity:0.5;
 	position:absolute;
}
.multi-media-li{
	
}
.Absolute-Center {  
  margin: auto;  
  position: absolute;  
  top: 0; left: 0; bottom: 0; right: 0;  
}
</style>
<title>Insert title here</title>
</head>
<body>
	<table style="margin-left: 5%;margin-top: 5%;">
		<tr>
			<td style="width: 20%;text-align: center;">
				<div class="zd">
					<h4>图文信息预览</h4>
					<div style="width: 210px; height: 142px; border: 2px solid #00CC33; margin-left: 35px;margin-top: 50px;">
						<div id="yl_img">
							<div style="margin-top:10px; width: 188px;height: 120px;margin-left: 8px;background-color: #DDDDDD">
								<span class="glyphicon glyphicon-picture" style="font-size: 35px; margin-top: 42.5px; color: #808080"></span>
							</div>				
						</div>
						<div style="width: 206px;height: 40px;">
							<div class="zhenzhao" style="width: 188px;height: 30px;margin-left: 8px;margin-top: -30px;text-align: left;">
								<span id="fontbt" style="font-size: 20px;color: #FFFFFF">标题</span>
							</div>
							<input id="mediaId" type="hidden" />
						</div>
					</div>
				</div>
			</td>
			<td style="width: 50%;">
				<form
					action="${pageContext.request.contextPath}/resources/addEntity.shtml"
					method="post" enctype="multipart/form-data">
					<div class="input-group input-group-lg">
						<input id="inputbt" type="text" class="form-control inputBorder" style="width:600px;margin-bottom: 5px;"
							placeholder="请在这里输入标题"> <br>
					</div>
					<div class="input-group input-group-sm">
						<input type="text" class="form-control inputBorder" style="width:600px;"
							placeholder="请输入作者">
					</div>
					<hr>
					<div id="editor">
					</div>
					<input type="checkbox" id="checkbox1" />原文链接
					<br>
					<input type="text" id="orgUrlInp" class="width" style="display:none;" />
					<br>
					<hr>
					<label>封面</label>&nbsp;&nbsp;<label style="color: #808080">大图片建议尺寸：900像素 * 500像素</label>
					<br>
					<button class="btn btn-primary">从正文选择</button>
					<button id="material_get" type="button" class="btn btn-primary">从图片库选择</button>
					<br><br>
					<label>摘要</label>&nbsp;&nbsp;<label style="color: #808080">选填，如果不填写会默认抓取正文前54个字</label>
					<br>
					<textarea rows="8" cols="73"></textarea>
					<br><br><br><br>
					<div style="text-align: center;">
						<button type="submit" class="btn btn-success" style="margin-right: 30px;">保存</button>
						<button id="close_btn" type="button" class="btn btn-danger" style="margin-left: 30px;">取消</button>
					</div>
				</form>
			</td>
			<td style="width: 20%;text-align: center;">
				<div class="zd" style="text-align: center;">
					<h4>多媒体</h4>
					<div>
						<ul class="list-unstyled">
							<li>
								<a href="#" class="btn btn-info btn-lg" style="width: 200px;margin-top: 20px;">
									<span class="glyphicon glyphicon-picture"></span> Picture
								</a>
							</li>
							<li>
        						<a href="#" class="btn btn-info btn-lg" style="width: 200px;margin-top: 20px;">
									<span class="glyphicon glyphicon-facetime-video"></span> Video
								</a>
							</li>
							<li>
								<a href="#" class="btn btn-info btn-lg" style="width: 200px;margin-top: 20px;">
									<span class="glyphicon glyphicon-music"></span> Music
								</a>
							</li>
						</ul>
					</div>
				</div>
			</td>
		</tr>
	</table>
	
</body>
<script type="text/javascript">
</script>
<script type="text/javascript">
	$(function(){
		var pageii = null;
		$('#material_get').click(function(){
			pageii = layer.open({
		        title: "图片素材",
		        type: 2,
		        area: ["90%", "85%"],
		        content: '${pageContext.request.contextPath}/material/imageList.shtml'
		    });
		});
		$('#editor').summernote({
			toolbar:[
				['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']]
			],
			width: 600,
			height: 300,                 // set editor height
			minHeight: null,             // set minimum height of editor
			maxHeight: null,             // set maximum height of editor
			focus: true                  // set focus to editable area after initializing summernote
		});
		$('#close_btn').click(function(){
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		});
		$('#inputbt').bind('input propertychange', function() {
			$('#fontbt').css("font-size",'20px');
			$('#fontbt').html($('#inputbt').val());
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