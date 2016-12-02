<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wechat/storeManager/list.js"></script>
<div class="m-b-md" style="padding-left: 15px;">
    <form class="form-inline" role="form" id="searchForm"
          name="searchForm">
        <div class="form-group">
            <label class="control-label">
                <span class="h4 font-thin v-middle">门店编码:</span>
            </label>
            <input class="input-medium ui-autocomplete-input" id="storeCode" name="storeCode">
            <label class="control-label">
                <span class="h4 font-thin v-middle">门店名称:</span>
            </label>
            <input class="input-medium ui-autocomplete-input" id="businessName" name="businessName">
        </div>
        <a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
    </form>
</div>
<header class="panel-heading">
    <div class="doc-buttons">

    </div>
</header>
<div class="table-responsive">
    <div class="">
        <div class="col-md-12">
            <div class="portlet box blue-hoki">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-globe"></i>门店管理
                    </div>
                    <div class="actions" id="btnAdd">
                        <c:forEach items="${res}" var="key">
                            ${key.description}
                        </c:forEach>
                    </div>
                </div>
                <div class="portlet-body">
                    <table class="table table-striped table-bordered table-hover dataTable no-footer"
                           id="storeManagerList">
                        <thead>
                        <tr role="row">
                            <th></th>
                            <th>门店编码</th>
                            <th>门店名称</th>
                            <th>分店名称</th>
                            <th>省</th>
                            <th>市</th>
                            <th>区</th>
                            <th>街道地址</th>
                            <th>电话</th>
                            <th>门店类型</th>
                            <th>坐标类型</th>
                            <th>经度</th>
                            <th>纬度</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
