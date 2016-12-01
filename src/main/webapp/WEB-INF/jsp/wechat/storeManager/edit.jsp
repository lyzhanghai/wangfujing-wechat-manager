<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/wechat/storeManager/edit.js"></script>
<style type="text/css">
.col-sm-3 {
	width: 15%;
	float: left;
}

.col-sm-9 {
	width: 85%;
	float: left;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/role/editEntity.shtml">
		<input type="hidden" class="form-control checkacc" value="${store.sid}"
			name="roleFormMap.id" id="id">
		<section class="panel panel-default">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-3 control-label">门店编码</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkacc"
							   placeholder="请输入门店编码" name="storeCode" id="storeCode" value="${store.storeCode}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">门店名称</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkacc"
							   placeholder="请输入门店名称" name="businessName" id="businessName" value="${store.businessName}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">分店名称</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkacc"
							   placeholder="请输入分店名称" name="branchName" id="branchName" value="${store.branchName}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">省</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入省"
							   name="province" id="province" value="${store.province}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">市</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入市"
							   name="city" id="city" value="${store.city}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">区</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入区"
							   name="district" id="district" value="${store.district}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">街道地址</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入街道地址"
							   name="address" id="address" value="${store.address}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">电话</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入电话"
							   name="telephone" id="telephone" value="${store.telephone}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">门店类型</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入门店类型"
							   name="categories" id="categories" value="${store.categories}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">坐标类型</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入坐标类型"
							   name="offsetType" id="offsetType" value="${store.offsetType}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">经度</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入经度"
							   name="longitude" id="longitude" value="${store.longitude}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">纬度</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入纬度"
							   name="latitude" id="latitude" value="${store.latitude}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">特色服务</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入特色服务"
							   name="special" id="special" value="${store.special}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">营业时间</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入营业时间"
							   name="openTime" id="openTime" value="${store.openTime}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">人均价格</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入人均价格"
							   name="avgPrice" id="avgPrice" value="${store.avgPrice}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">商户简介</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入商户简介"
							   name="introduction" id="introduction" value="${store.introduction}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">推荐品</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" placeholder="请输入推荐品"
							   name="recommend" id="recommend" value="${store.recommend}">
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
			</div>
			<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
	</section>
	</form>
	<script type='text/javascript'>
		/* $(function(){
			$("from input[name='enable'][value='${role.enable}']").attr("checked","checked");
			alert("input[name='enable'][value='${role.enable}']");
		}); */
	</script>
</body>
</html>