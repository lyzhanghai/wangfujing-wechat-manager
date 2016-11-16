<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/log/list.js"></script>
	<div class="table-responsive">
		<div class="">
			<div class="col-md-12">
				<div class="portlet box blue-hoki">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>告警列表
						</div>
						<div class="actions" id="btnAdd">

						</div>
					</div>
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable no-footer"
							   id="logList">
							<thead>
							<tr role="row">
								<th>账号</th>
								<th>方法</th>
								<th>模块</th>
								<th>响应时间</th>
								<th>IP地址</th>
								<th>执行时间</th>
								<th>执行描述</th>
							</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
