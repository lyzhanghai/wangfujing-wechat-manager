<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/coupon/tpl/list.js"></script>
<div class="table-responsive">
    <div class="col-md-12">
        <div class="portlet box blue-hoki">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-weixin"></i>券模板管理
                </div>
                <div class="actions" id="btnAdd">
                    <c:forEach items="${res}" var="key">
                        ${key.description}
                    </c:forEach>
                </div>
            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable no-footer"
                       id="userList">
                    <thead>
                    <tr role="row">
                        <th></th>
                        <th>模板ID</th>
                        <th>券类型</th>
                        <th>券面值</th>
                        <th>品牌券模板</th>
                        <th>使用门槛</th>
                        <th>创建人</th>
                        <th>创建时间</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>


