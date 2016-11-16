<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/resources/list.js"></script>
	<header class="panel-heading">
	<div class="doc-buttons">

	</div>
	</header>
	<div class="table-responsive">
		<div class="col-md-6">
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>菜单管理
					</div>
					<div class="tools">
					</div>
				</div>
				<div class="portlet-body">
					<div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
						<div class="row">
							<div class="col-md-12">
								<div class="btn-group tabletools-dropdown-on-portlet">
									<c:forEach items="${res}" var="key">
										${key.description}
									</c:forEach>
								</div>
							</div>
						</div>
					</div>

					<div class="portlet-body">
						<div id="menuTree" class="tree-demo jstree jstree-3 jstree-default" role="tree"
							 aria-activedescendant="j3_7">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue-hoki">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>菜单详情
							</div>
							<div class="tools">
							</div>
						</div>
						<div class="portlet-body">
							<div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
								<div class="row">
									<div class="col-md-12">
										<div class="btn-group tabletools-dropdown-on-portlet">
										</div>
									</div>
								</div>
							</div>

							<div class="portlet-body">
								<form class="form-horizontal form-bordered form-row-strippe" id=""
									  action=""
									  data-toggle="validator">
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label class="control-label col-md-3">菜单名称
												</label>

												<div class="col-md-8">
													<input type="text" class="form-control" name="nameDetail"
														   id="nameDetail" readonly="readonly">
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label class="control-label col-md-3">resKey
												</label>

												<div class="col-md-8">
													<input type="text" class="form-control" name="resKeyDetail"
														   id="resKeyDetail" readonly="readonly">
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label class="control-label col-md-3">类型
												</label>

												<div class="col-md-8">
													<input type="text" class="form-control" name="typeDetail"
														   id="typeDetail" readonly="readonly">
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label class="control-label col-md-3">图标
												</label>

												<div class="col-md-8">
													<input type="text" class="form-control" name="iconDetail"
														   id="iconDetail" value="" readonly="readonly">
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label class="control-label col-md-3">父节点编码
												</label>

												<div class="col-md-8">
													<input type="text" class="form-control" name="parentDetail"
														   id="parentDetail" readonly="readonly">
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<label class="control-label col-md-3">resUrl
												</label>

												<div class="col-md-8">
													<input type="text" class="form-control" name="resUrlDetail"
														   id="resUrlDetail" readonly="readonly">
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group" style="text-align: center" id="opt">
												<label class="control-label col-md-3">是否隐藏
												</label>

												<div class="col-md-8">
													<input type="text" class="form-control" name="isHide"
														   id="isHide" readonly="readonly">
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
