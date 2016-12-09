<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <script type="text/javascript" src="${ctx}/js/coupon/tpl/add.js"></script>
</head>
<body>
<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
<form id="form" name="form" class="form-horizontal" method="post" action="#">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group form-md-line-input">
                <label class="col-xs-3 control-label text-right"><span style="color:red">*</span> 券类型</label>

                <div class="col-xs-6">
                    <select class="form-control" id="selCouponType" name="selCouponType">
                    </select>
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right"><span style="color:red">*</span> 券面值</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control"
                           name="txtCouponValue" id="txtCouponValue">
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">使用门槛</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control" name="txtCouponPriceLimit" id="txtCouponPriceLimit">
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right" >品牌券模板</label>

                <div class="col-xs-6">
                    <input type="text" class="form-control"
                           name="txtCouponName" id="txtCouponName">
                </div>
                <div class="col-xs-3"></div>
            </div>
        </div>
        <footer class="panel-footer text-center bg-light lter">
            <button type="submit" class="btn btn-success btn-s-xs">提交</button>
        </footer>
    </section>
</form>
</body>
</html>