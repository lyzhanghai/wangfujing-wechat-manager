<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <script type="text/javascript" src="${ctx}/js/wechat/wechatapp/edit.js"></script>
    <style type="text/css">
        #but button {
            margin-bottom: 5px;
            margin-right: 5px;
        }

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
<form id="form" name="form" class="form-horizontal" method="post"
      action="${pageContext.request.contextPath}/wechatapp/addEntity.shtml">
    <input type="hidden" value="${app.sid}" name="sid"
           id="id">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">门店</label>

                <div class="col-sm-9">
                    <select id="storeCode" name="storeCode" class="form-control m-b"
                            tabindex="-1" disabled>
                    </select>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">APPID</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入appid" name="appid" id="appid" value="${app.appid}">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">APPSECRET</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入appsecret" name="appsecret" id="appsecret" value="${app.appsecret}">
                </div>
            </div>
        </div>
        <footer class="panel-footer text-right bg-light lter">
            <button type="submit" class="btn btn-success btn-s-xs">提交</button>
        </footer>
    </section>
</form>
<script type="text/javascript">
    loadStoreSelect("${app.storecode}");
</script>
</body>
</html>