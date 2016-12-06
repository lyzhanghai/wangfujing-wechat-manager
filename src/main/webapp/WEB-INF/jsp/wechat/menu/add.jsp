<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <script type="text/javascript" src="${ctx}/js/wechat/menu/add.js"></script>
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
      action="${pageContext.request.contextPath}/menu/addEntity.shtml">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">上级菜单</label>

                <div class="col-sm-9">
                    <select id="parentId" name="menuParentId" class="form-control m-b"
                            tabindex="-1">
                    </select>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">菜单名称</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入菜单名称" name="menuName" id="menuName">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">菜单内容</label>

                <div class="col-md-8">
                    <label class="radio-inline">
                        <input type="radio" name="radioMenuType" id="menuType1"
                               value="click" checked> 发送消息 </label>
                    <label class="radio-inline">
                        <input type="radio" name="radioMenuType" id="menuType2"
                               value="view"> 跳转网页 </label>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group" id="divView">
                <label class="col-sm-3 control-label">页面地址</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入页面地址" name="menuUrl" id="resUrl">
                </div>
            </div>
            <%--<div class="form-group" id="divClick">
                <label class="col-sm-3 control-label">消息内容</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入消息内容" name="menuContent" id="resContent">
                </div>
            </div>--%>
            <div class="form-group" id="divClick">
                <label class="col-sm-3 control-lable">消息内容</label>

                <div class="col-sm-9">
                   <textarea name="menuContent" id="resContent" placeholder="请输入消息内容"
                             class="form-control readLength exlimit"
                             rows="4"
                             maxlength="256"></textarea>
                </div>
            </div>
        </div>
        </div>
        <footer class="panel-footer text-right bg-light lter">
            <button type="submit" class="btn btn-success btn-s-xs">提交</button>
        </footer>
    </section>
</form>
</body>
</html>