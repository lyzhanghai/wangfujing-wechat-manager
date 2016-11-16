<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/userlogin/list.js"></script>
	<div class="m-b-md">
		<form class="form-inline" role="form" id="form"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">账号:</span></label> <input
					class="input-medium ui-autocomplete-input" id="accountName"
					name="userLoginFormMap.accountName">
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="searchForm">查询</a>
		</form>
	</div>
	<div class="table-responsive">
		<div class="">
			<div class="col-md-12">
				<div class="portlet box blue-hoki">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>用户登录记录
						</div>
						<div class="actions" id="btnAdd">

						</div>
					</div>
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable no-footer"
							   id="userLoginList">
							<thead>
							<tr role="row">
								<th>账号</th>
								<th>登入时间</th>
								<th>登入IP</th>
							</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
