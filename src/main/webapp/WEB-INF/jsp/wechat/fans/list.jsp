<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="m-b-md" style="padding-left: 15px;">
	<form class="form-inline" role="form" id="searchForm" name="searchForm">
		<div class="form-group">
			<label class="control-label"> <span
				class="h4 font-thin v-middle">用户昵称:</span></label> <input
				class="input-medium ui-autocomplete-input" id="accountName"
				name="userFormMap.accountName">
		</div>
		<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
	</form>
	
</div>
<header class="panel-heading">
	<input type="hidden" id="menuId" value="${menuId}">
	<div class="doc-buttons"></div>
</header>
<div class="table-responsive">
	<%--<div id="paging" class="pagclass"></div>--%>
	<div class="">
		<div class="col-md-8">
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>粉丝列表
					</div>
					<div class="actions" id="btnAdd">
						<c:forEach items="${res}" var="key">
								${key.description}
							</c:forEach>
					</div>
				</div>
				<div class="portlet-body">
					<table
						class="table table-striped table-bordered table-hover dataTable no-footer"
						id="fansList">
						<thead>
							<tr role="row">
								<th></th>
								<th>用户</th>
								<th>标签</th>
								<th>修改备注</th>
							</tr>
						</thead>
						<tbody>
							<tr role="row">
								<td></td>
								<td>123123</td>
								<td>组别1</td>
								<td><button type="button" id="editFun"
										class="btn btn-info marR10">点击修改备注</button></td>
							</tr>
							<tr role="row">
								<td></td>
								<td>123123</td>
								<td>组别1</td>
								<td><button type="button" id="editFun"
										class="btn btn-info marR10">点击修改备注</button></td>

							</tr>
							<tr role="row">
								<td></td>
								<td>123132</td>
								<td>组别1</td>
								<td><button type="button" id="editFun"
										class="btn btn-info marR10">点击修改备注</button></td>

							</tr>
							<tr role="row">
								<td></td>
								<td>7657</td>
								<td>组别1</td>
								<td><button type="button" id="editFun"
										class="btn btn-info marR10">点击修改备注</button></td>

							</tr>
							<tr role="row">
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>粉丝组别管理
					</div>
				</div>
				<div class="portlet-body">
					<table
						class="table table-striped table-bordered table-hover dataTable no-footer"
						id="userList">
						<thead>
							<tr role="row">
								<th>id</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>123123</td>
							</tr>
							<tr>
								<td>123123</td>
							</tr>
							<tr>
								<td>123123</td>
							</tr>
							<tr>
								<td>123123</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


