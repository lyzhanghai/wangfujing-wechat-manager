<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wechat/menu/list.js"></script>
<header class="panel-heading">
    <div class="doc-buttons">

    </div>
</header>
<div class="table-responsive">
    <div class="col-md-6">
        <div class="portlet box blue-hoki">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-weixin"></i>微信自定义菜单管理
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
                            <i class="fa fa-weixin"></i>菜单详情
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
                                    <div class="col-md-12" id="divMenuType">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">菜单内容
                                            </label>

                                            <div class="col-md-8">

                                                <label class="radio-inline">
                                                    <input type="radio" name="radioMenuType" id="menuType1"
                                                           value="click" disabled> 发送消息 </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="radioMenuType" id="menuType2"
                                                           value="view" disabled> 跳转网页 </label>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12" id="divClick" style="display: none">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">消息内容
                                            </label>

                                            <div class="col-md-8">
                                                <input type="text" class="form-control" name="resKeyDetail"
                                                       id="resKeyDetail" readonly="readonly">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12" id="divView" style="display: none">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">页面地址
                                            </label>

                                            <div class="col-md-8">
                                                <input type="text" class="form-control" name="resUrlDetail"
                                                       id="resUrlDetail" readonly="readonly">
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