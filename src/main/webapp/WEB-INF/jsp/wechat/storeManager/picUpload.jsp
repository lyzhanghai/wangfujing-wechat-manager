<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <script type="text/javascript" src="${ctx}/js/wechat/storeManager/picUpload.js">
    </script>
    <style type="text/css">
        .col-sm-3 {
            width: 15%;
            float: left;
        }

        .col-sm-9 {
            width: 85%;
            float: left;
        }

        label[class^="btn btn-default"] {
            margin-top: -4px;
        }
    </style>
</head>
<body>
<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
<form id="form" name="form" class="form-horizontal" method="post" enctype="multipart/form-data"
      action="${ctx}/storeSyn/picUpload.shtml">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">门店编码</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           name="storecode" id="storecode" value="${store.storecode}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <%--<div class="form-group">
                <label class="col-sm-3 control-label">AppID</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           name="appid" id="appid" value="${store.appid}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">AppSecret</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           name="appsecret" id="appsecret" value="${store.appsecret}" readonly="readonly">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>--%>
            <div class="form-group">
                <label class="col-sm-3 control-label">图片</label>
                <div class="col-sm-9">
                    <input type="file" name="file" id="file">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <img id="photoUrl" style="display: none; width: 200px; height: 200px;" src="">
        </div>
        <footer class="panel-footer text-right bg-light lter">
            <button type="submit" class="btn btn-success btn-s-xs">提交</button>
        </footer>
    </section>
</form>
</body>
</html>