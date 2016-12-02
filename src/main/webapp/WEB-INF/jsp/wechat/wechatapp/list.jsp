<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wechat/wechatapp/list.js"></script>

	<div class="m-b-md" style="padding-left: 15px;">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">门店:</span></label> <input
					class="input-medium ui-autocomplete-input" id="accountName"
					name="userFormMap.accountName">
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
		</form>
	</div>
	<header class="panel-heading">
		<input type="hidden" id="menuId" value="${menuId}">
	<div class="doc-buttons">
	</div>
	</header>
	<div class="table-responsive">
		<%--<div id="paging" class="pagclass"></div>--%>
		<div class="">
			<div class="col-md-12">
				<div class="portlet box blue-hoki">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-weixin"></i>公众号管理
						</div>
						<div class="actions" id="btnAdd">
							<c:forEach items="${res}" var="key">
								${key.description}
							</c:forEach>
						</div>
					</div>
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable no-footer"
							   id="userList">
							<thead>
							<tr role="row">
								<th></th>
								<th>门店编号</th>
								<th>门店名称</th>
								<th>APPID</th>
								<th>APPSECRET</th>
							</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="callback_div" class="table-responsive" style="display: none;">
		<div id="paging_callback" class="pagclass"></div>
	</div>
