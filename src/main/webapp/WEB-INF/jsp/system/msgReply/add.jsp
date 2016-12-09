<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

button{
}
</style>
</head>
<body>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a id="subscribe" href="#home" data-toggle="tab">被关注后自动回复</a></li>
		<li><a id="disReply" href="#home" data-toggle="tab">消息自动回复</a></li>
		<li><a href="#java" data-toggle="tab">关键词自动回复</a></li>
	</ul>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="home">
			<div>
				<form action="${pageContext.request.contextPath}/upload/photoUpload.shtml" method="post" enctype="multipart/form-data">
					<div id="msgKeyDiv">
						规则名称：<input id="ruleName" name="ruleName" />
						<br>
						关键字：<input id="msgKey" name="msgKey" />
					</div>
					<button id="btnText" type="button" class="btn btn-default">文本</button>
					<button id="btnImage" type="button" class="btn btn-default">图片</button>
					<button id="btnVideo" type="button" class="btn btn-default">视频</button>
					<button id="btnVoice" type="button" class="btn btn-default">语音</button>
					<br>
					<textarea id="textID" name="textContent" rows="10" cols="50"></textarea>
					<div class="input-group" id="titleDiv">
			            <input type="text" name="title" class="form-control" placeholder="视频标题">
			            <input type="text" name="introduction" class="form-control" placeholder="视频描述">
			        </div>
			        <div id="fileID">
			        	<div id="imageDiv">
			        		
			        	</div><br>
						<input id="fileId" type="file" name="file" />
						<img id="preview_size_fake"/>
			        </div>
					<input type="hidden" id="fileType" name="fileType" value="text">
					<input type="hidden" id="eventType" name="eventType" value="subscribe" >
					<br>
					<div style=" position: fixed;bottom: 0px;margin-bottom: 100px;">
						<button type="submit" class="btn btn-success">保存</button>
						<button type="button" class="btn btn-danger">删除</button>
					</div>
				</form>
			</div>
		</div>
		<div class="tab-pane fade" id="java">
			<div>
				<a href="#home" data-toggle="tab" id="addRule">新建规则</a>
				<br> <br> <br>
				<table
					class="table table-striped table-bordered table-hover table-condensed">
					<tr>
						<th>规则名称</th>
						<th>关键字</th>
						<th>回复消息类型</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${msgReplyList}" var="msgReply">
						<tr>
							<td>${msgReply.ruleName}</td>
							<td>${msgReply.msgKey}</td>
							<td>
								<c:if test="${msgReply.msgType == 0}">
									文本
								</c:if>
								<c:if test="${msgReply.msgType == 1}">
									图片
								</c:if>
								<c:if test="${msgReply.msgType == 2}">
									语音
								</c:if>
								<c:if test="${msgReply.msgType == 3}">
									视频
								</c:if>
								<c:if test="${msgReply.msgType == 4}">
									音频
								</c:if>
								<c:if test="${msgReply.msgType == 5}">
									图文
								</c:if>
							</td>
							<td>删除</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$('#fileId').change(function (){
			$("#imageDiv *").remove();
			var objPreview = document.getElementById('preview_size_fake');
			var sender = document.getElementById('fileId');
			objPreview.style.display = 'block';
	        objPreview.style.width = '200px';
	        objPreview.style.height = '200px';
			objPreview.src = window.URL.createObjectURL(sender.files[0]);
		});
		$('#fileID').hide();
		$('#titleDiv').hide();
		$('#msgKeyDiv').hide();
		$('#addRule').click(function(){
			$('#msgKeyDiv').hide();
			$('#eventType').attr("value",'ruleKey');
		});
		$('#subscribe').click(function(){
			$('#msgKeyDiv').hide();
			$('#eventType').attr("value",'sunscribe');
		});
		$('#disReply').click(function(){
			$('#msgKeyDiv').hide();
			$('#eventType').attr("value",'disReply');
		});
		$('#btnText').click(function(){
			$('#titleDiv').hide();
			$('#fileType').attr("value",'test');
			$('#fileID').hide();
			$('#textID').show();
		});
		$('#btnImage').click(function(){
			$.ajax({
				type : "POST",
				dataType: "json",
				contentType : 'application/json',
				url : "${pageContext.request.contextPath}/material/getMaterial.shtml",
				data : JSON.stringify({
					'offset':'0',
					'eventType':'image',
					'count':'10'
				}),
				success:function (paramMap) {
					$("#imageDiv *").remove();
					var list = paramMap.materialList;
					for(var i =0;i<list.length;i++){
						var img = '<div style="width: 200px;float: left;"><img src="' + list[i].url +'" /><br><input type="radio" name="imgMedia" value="' + list[i].media_id + '" /></div>';
						$("#imageDiv").append(img);
					} 
	            }
			});
			$('#preview_size_fake').hide();
			$('#titleDiv').hide();
			$('#fileType').attr("value",'image');
			$('#fileID').attr("accept",'image/jpeg,image/png,image/gif');//PNG\JPEG\JPG\GIF 2M
			$('#fileID').show();
			$('#textID').hide();
		});
		$('#btnVideo').click(function(){
			$('#titleDiv').show();
			$('#fileType').attr("value",'video');
			$('#fileID').attr("accept",'video/mp4');//MP4 10M
			$('#fileID').show();
			$('#textID').hide();
		});
		$('#btnVoice').click(function(){
			$('#titleDiv').hide();
			$('#fileType').attr("value",'voice');
			$('#fileID').attr("accept",'audio/mpeg');//AMR\MP3 2M
			$('#fileID').show();
			$('#textID').hide();
		});
	});
</script>
</html>

