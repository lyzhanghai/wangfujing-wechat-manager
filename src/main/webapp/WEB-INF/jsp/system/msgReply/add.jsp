<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
table {
	text-align: center
	overflow: hidden;
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
				<li><a href="#article" data-toggle="tab">图文</a></li>
			</ul>
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
				<br><br><br>
				<table class="table table-striped table-bordered table-hover table-condensed">
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
</html>

