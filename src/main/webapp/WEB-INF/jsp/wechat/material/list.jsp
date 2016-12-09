<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<button id="btnImage" type="button" class="btn btn-default" onclick="hidType('image');">图片</button>
			<button id="btnVideo" type="button" class="btn btn-default" onclick="hidType('video');">视频</button>
			<button id="btnVoice" type="button" class="btn btn-default" onclick="hidType('voice');">语音</button>
			<button id="btnArticle" type="button" class="btn btn-default" onclick="hidType('news');">图文</button>
		</div>
		<div>
			<div id="imageDiv">
				<button type="button" id="imageAdd" onclick="imageAdd();" class="btn btn-primary">新建图片素材</button>
				<c:forEach var="material" items="${materialList}">
					<div style="float: left;width:280px;text-align: center;">
						<img alt="" src="${material.url}" style="height: 140px;width: 140px;">
						<br>
						<label>${material.name }</label>
						<br>
						<button type="button" class="btn btn-danger" onclick="del('${material.media_id}');">删除</button>
					</div>
				</c:forEach>
			</div>
			<input type="hidden" value="image" id="eventType" />
		</div>
	</div>
</body>
<script type="text/javascript">
	
	var pageii = null;
	function imageAdd(){
		pageii = layer.open({
	        title: "新增图片素材",
	        type: 2,
	        area: ["50%", "50%"],
	        content: '${pageContext.request.contextPath}/material/imageAdd.shtml'
	    });
	}
	
	function newsAdd(){
		window.open('${pageContext.request.contextPath}/material/newsAdd.shtml');
		//pageii = layer.open({
	    //    title: "图片素材",
	    //    type: 2,
	    //    area: ["100%", "100%"],
	    //    content: '${pageContext.request.contextPath}/material/newsAdd.shtml'
	    //});
	}
	
	function del(media){
		$.ajax({
			type : "POST",
			dataType: "json",
			contentType : 'application/json',
			url : "${pageContext.request.contextPath}/material/del.shtml",
			data : JSON.stringify({
				'mediaId' : media
			}),
			success: function () {
				alert("成功");
				divSwitch();
            },
            error:function () {
				alert("失败");
            }
		});
	}
	
	function hidType(eventy){
		$('#eventType').attr("value",eventy);
		alert('aa');
		divSwitch();
	}
	
	function divSwitch(){
		$.ajax({
			type : "POST",
			dataType: "json",
			contentType : 'application/json',
			url : "${pageContext.request.contextPath}/material/getMaterial.shtml",
			data : JSON.stringify({
				'offset':'0',
				'eventType': $('#eventType').val(),
				'count':'10'
			}),
			success: function (paramMap) {
				$("#imageDiv *").remove();
				var btn = null;
				if($('#eventType').val() == 'news'){
					btn = '<button type="button" id="newsAdd" onclick="newsAdd();" class="btn btn-primary">新建图文素材</button>';
				} else if($('#eventType').val() == 'image'){
					btn = '<button type="button" id="imageAdd" onclick="imageAdd();" class="btn btn-primary">新建图片素材</button>';
				}
				$("#imageDiv").append(btn);
				var list = paramMap.materialList;
					for(var i =0;i<list.length;i++){
						if($('#eventType').val() == 'image'){
							var img = 
							'<div style="float: left;width:280px;text-align: center;">'+
								'<img alt="" src="'+list[i].url+'" style="height: 140px;width: 140px;">'+
								'<br>'+
								'<label>'+list[i].name+'</label>'+
								'<br>'+
								'<button type="button" class="btn btn-danger" onclick="del(\''+list[i].media_id+'\');">删除</button>'+
							'</div>';
							$("#imageDiv").append(img);
						}else if($('#eventType').val() == 'voice'){
							//<audio src="see-you-again.mp3" controls="controls" preload="auto" autoplay="autoplay" loop="loop">
							var audio = 
								'<div style="float: left;width:280px;text-align: center;">'+
									'<audio alt="" src="'+list[i].url+'" controls="controls"></audio>'+
									'<button type="button" class="btn btn-danger" onclick="del(\''+list[i].media_id+'\');">删除</button>'+
								'</div>';
							$("#imageDiv").append(audio);
						}
					}
				},
            error:function () {
				alert("失败");
            }
		});
	}
	
</script>
</html>