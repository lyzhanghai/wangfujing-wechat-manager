<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/role/list.js"></script>
	<div class="m-b-md" style="padding-left: 15px;">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">角色名:</span></label> <input
					class="input-medium ui-autocomplete-input" id="name"
					name="roleFormMap.roleFormMap.name">
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
		</form>
	</div>
	<header class="panel-heading">
	<div class="doc-buttons">

	</div>
	</header>
	<div class="table-responsive">
		<div class="">
			<div class="col-md-12">
				<div class="portlet box blue-hoki">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>角色列表
						</div>
						<div class="actions" id="btnAdd">
							<c:forEach items="${res}" var="key">
								${key.description}
							</c:forEach>
						</div>
					</div>
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable no-footer"
							   id="roleList">
							<thead>
							<tr role="row">
								<th></th>
								<th>角色名</th>
								<th>状态</th>
								<th>roleKey</th>
								<th>描述</th>
							</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
