<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/monitor/list.js"></script>
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
							   id="monitorList">
							<thead>
							<tr role="row">
								<th>cpu使用率</th>
								<th>预设cpu使用率</th>
								<th>Jvm使用率</th>
								<th>预设Jvm使用率</th>
								<th>Ram使用率</th>
								<th>预设Ram使用率</th>
								<th>发送的邮件</th>
								<th>发送的时间</th>
								<th>备注</th>
							</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
