<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
* {
	margin: 0;
	padding: 0
}

ul, li {
	list-style: none
}

.tabClick {
	background: #f3f3f3;
	overflow: hidden
}

.tabClick2 {
	background: #f3f3f3;
	overflow: hidden
}

.tabClick2 li {
	height: 40px;
	line-height: 40px;
	width: 33%;
	float: left;
	text-align: center
}

.tabClick li {
	height: 40px;
	line-height: 40px;
	width: 10%;
	float: left;
	text-align: center
}

.tabClick li.active {
	color: #099;
	transition: 0.1s;
	font-weight: bold
}

.tabCon {
	overflow: hidden
}

.tabBox {
	position: relative
}

.tabList {
	word-break: break-all;
	width: 100%;
	float: left;
	line-height: 100px;
	text-align: center;
	color: #D3D3D3;
	font-size: 36px;
	font-family: "Arial Black"
}

.lineBorder {
	height: 2px;
	overflow: hidden;
	border-bottom: 1px solid #099;
	background: #f3f3f3
}

.lineDiv {
	background: #099;
	height: 2px;
	width: 10%;
}
</style>
<script type="text/javascript">
	$(function(){
		$("button").click(){
			alert("aaa");
		};	
	});
</script>
</head>
<body>
	<div class="wrap" id="wrap">
		<button>aaaaa</button>
		<ul class="tabClick">
			<li id="tb1" class="active"">文字</li>
			<li>图片</li>
			<li>语音</li>
			<li>视频</li>
		</ul>
		<div class="lineBorder">
			<div class="lineDiv">
				<!--移动的div-->
			</div>
		</div>
		<div class="tabCon">
			<div class="tabBox">
				<div class="tabList">1</div>
				<div class="tabList" style="display: none">2</div>
				<div class="tabList" style="display: none">3</div>
				<div class="tabList" style="display: none">4</div>
			</div>
		</div>
	</div>
</body>
</html>

