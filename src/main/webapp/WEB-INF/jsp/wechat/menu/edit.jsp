<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/common/common.jspf" %>
    <script type="text/javascript" src="${ctx}/js/wechat/menu/edit.js"></script>
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
      action="${pageContext.request.contextPath}/resources/editEntity.shtml">
    <input type="hidden" value="${resources.sid}" name="sid"
           id="id">
    <section class="panel panel-default">
        <div class="panel-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">菜单名称</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入菜单名称" name="menuName" id="name"
                           value="${resources.name}">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">菜单内容</label>

                <div class="col-sm-9">
                    发送消息<input type="radio" name="radioMenuType" id="menuType1" value="click"
                <c:if test="${resources.type eq 'click'}"> checked="checked"</c:if>>
                    &nbsp;&nbsp;跳转网页<input type="radio" name="radioMenuType" id="menuType2" value="view"
                <c:if test="${resources.type eq 'view'}"> checked="checked"</c:if>>
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">菜单url</label>

                <div class="col-sm-9">
                    <input type="text" class="form-control checkacc"
                           placeholder="请输入菜单url" name="menuUrl" id="resUrl"
                           value="${resources.viewurl}">
                </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label">上级菜单</label>

                <div class="col-sm-9">
                    <select id="parentId" name="menuParentId"
                            class="form-control m-b">
                    </select>
                </div>
            </div>
            <footer class="panel-footer text-right bg-light lter">
                <button type="submit" class="btn btn-success btn-s-xs">提交</button>
            </footer>
        </div>
    </section>
</form>
<script type="text/javascript">
    byRes("${resources.parentSid}");
</script>
</body>
</html>