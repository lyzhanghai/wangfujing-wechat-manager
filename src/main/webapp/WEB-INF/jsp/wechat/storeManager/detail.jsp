<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/common/common.jspf" %>
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
<form id="form" name="form" class="form-horizontal">
    <input type="hidden" class="form-control checkacc" value="${store.sid}"
           name="sid" id="id">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">门店编码</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           name="storeCode" id="storeCode" value="${store.storeCode}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">门店名称</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           name="businessName" id="businessName" value="${store.businessName}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">分店名称</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           name="branchName" id="branchName" value="${store.branchName}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">省</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="province" id="province" value="${store.province}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">市</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="city" id="city" value="${store.city}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">区</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="district" id="district" value="${store.district}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">街道地址</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="address" id="address" value="${store.address}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">电话</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="telephone" id="telephone" value="${store.telephone}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">门店类型</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="categories" id="categories" value="${store.categories}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">坐标类型</label>
                <div class="col-sm-9">
                    <select id="offsetType" name="offsetType" disabled="disabled">
                        <option value="1" <c:if test="${store.offsetType == 1}">selected="selected"</c:if>>火星坐标</option>
                        <option value="2" <c:if test="${store.offsetType == 2}">selected="selected"</c:if>>sogou经纬度</option>
                        <option value="3" <c:if test="${store.offsetType == 3}">selected="selected"</c:if>>百度经纬度</option>
                        <option value="4" <c:if test="${store.offsetType == 4}">selected="selected"</c:if>>mapbar经纬度</option>
                        <option value="5" <c:if test="${store.offsetType == 5}">selected="selected"</c:if>>GPS坐标</option>
                        <option value="6" <c:if test="${store.offsetType == 6}">selected="selected"</c:if>>sogou墨卡托坐标</option>
                    </select>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">经度</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="longitude" id="longitude" value="${store.longitude}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">纬度</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="latitude" id="latitude" value="${store.latitude}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">特色服务</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="special" id="special" value="${store.special}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">营业时间</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="openTime" id="openTime" value="${store.openTime}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">人均价格</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="avgPrice" id="avgPrice" value="${store.avgPrice}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">商户简介</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="introduction" id="introduction" value="${store.introduction}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">推荐品</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="recommend" id="recommend" value="${store.recommend}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
        </div>
    </section>
</form>
</body>
</html>