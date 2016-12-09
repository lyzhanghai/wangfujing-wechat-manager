<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.form.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/coupon/rule/add.js"></script>
<style type="text/css">

    .dropdown-colorselector > .dropdown-menu {
        left: -7px;
        max-width: 130px;
        min-width: 130px;
        padding: 4px;
        top: 80%;
    }

    .dropdown-colorselector > .dropdown-menu > li {
        display: block;
        float: left;
        height: 20px;
        margin: 2px;
        width: 20px;
    }

    .dropdown-colorselector > .dropdown-menu > li > .color-btn {
        border-radius: 0;
        display: block;
        height: 20px;
        margin: 0;
        padding: 0;
        position: relative;
        transition: all 0.1s ease 0s;
        width: 20px;
    }

    .dropdown-colorselector > .dropdown-menu > li > .color-btn:hover {
        opacity: 0.8;
        text-decoration: none;
        transform: scale(1.08);
    }

    .dropdown-colorselector > .dropdown-menu > li > .color-btn.selected::after {
        color: #fff;
        content: "";
        display: inline-block;
        font-family: "Glyphicons Halflings";
        font-size: 11px;
        left: 0;
        line-height: 20px;
        position: absolute;
        right: 0;
        text-align: center;
    }

    .btn-colorselector {
        background-color: #ddd;
        border-radius: 0;
        display: inline-block;
        height: 20px;
        vertical-align: middle;
        width: 20px;
    }

    .dropdown-menu.dropdown-caret::before {
        border-bottom: 7px solid rgba(0, 0, 0, 0.2);
        border-left: 7px solid transparent;
        border-right: 7px solid transparent;
        content: "";
        display: inline-block;
        left: 9px;
        position: absolute;
        top: -7px;
    }

    .dropdown-menu.dropdown-caret::after {
        border-bottom: 6px solid #ffffff;
        border-left: 6px solid transparent;
        border-right: 6px solid transparent;
        content: "";
        display: inline-block;
        left: 10px;
        position: absolute;
        top: -6px;
    }

</style>
<div class="m-b-md" style="padding-left: 15px;">
    <div class="portlet light bg-inverse">
        <div class="portlet-title">
            <div class="caption">
                <i class="icon-equalizer font-red-sunglo"></i>
                <span class="caption-subject font-red-sunglo bold uppercase">电子券号生成规则</span>
                <span class="caption-helper"></span>
            </div>
            <div class="actions">
            </div>
        </div>
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form id="form" name="form" action="#" method="post" class="form-horizontal">
                <input type="hidden" value="${coupon.sid}" name="sid"
                       id="sid">
                <div class="form-body">
                    <div class="alert alert-danger display-hide">
                        <button class="close" data-close="alert"></button>
                        You have some form errors. Please check below.
                    </div>
                    <div class="alert alert-success display-hide">
                        <button class="close" data-close="alert"></button>
                        Your form validation is successful!
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">券流水位数
                        </label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   placeholder="请输入券流水位数" name="NoLength" id="NoLength" value="${coupon.nolength}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">券号起始位
                        </label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   placeholder="请输入券号起始位" name="PrefixStr" id="PrefixStr" value="${coupon.prefixstr}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">券号前缀字符
                        </label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   placeholder="请输入券号前缀字符" name="StartNo" id="StartNo" value="${coupon.startno}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">券号后缀长度
                        </label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   placeholder="请输入券号后缀长度" name="SuffixLength" id="SuffixLength" value="${coupon.suffixlength}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">代金券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select class="form-control" name="colorselector_djq" id="colorselector_djq">
                                </select>
                                <a data-toggle="dropdown" href="#">
                                    <span class="btn-colorselector" style="background-color: rgb(99, 179, 89);"></span>
                                </a>
                                <ul class="dropdown-menu dropdown-caret" role="menu" aria-labelledby="dLabel">
                                    <li><a class="color-btn selected" style="background-color: rgb(99, 179, 89);"
                                           title="#63b359"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(44, 159, 103);" href="#"
                                           title="#2c9f67"></a></li>
                                    <li><a class="color-btn " style="background-color: rgb(80, 159, 201);" href="#"
                                           title="#509fc9"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(88, 133, 207);" href="#"
                                           d title="#5885cf"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(144, 98, 192);" href="#"
                                           title="#9062c0"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(208, 154, 69);" href="#"
                                           title="#d09a45"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(228, 177, 56);" href="#"
                                           title="#e4b138"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(238, 144, 60);" href="#"
                                           title="#ee903c"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(240, 133, 0);" href="#"
                                           title="#f08500"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(169, 217, 45);" href="#"
                                           title="#a9d92d"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(221, 101, 73);" href="#"
                                           title="#dd6549"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(204, 70, 61);" href="#"
                                           title="#cc463d"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(207, 62, 54);" href="#"
                                           title="#cf3e36"></a></li>
                                    <li><a class="color-btn" style="background-color: rgb(94, 102, 113);" href="#"
                                           title="#5E6671"></a></li>
                                </ul>
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">折扣券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select class="form-control" id="colorselector_zkq">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">礼品券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select class="form-control" id="colorselector_lpq">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">邀请券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select class="form-control" id="colorselector_yqq">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">促销券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select class="form-control" id="colorselector_cxq">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3" style="vertical-align: middle;">异业券背景
                        </label>

                        <div class="col-sm-4">
                            <div class="dropdown dropdown-colorselector">
                                <select  class="form-control" id="colorselector_yyq">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn green">Submit</button>
                                <button type="button" class="btn default">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>
<script type="text/javascript">
    loadCouponBG("${coupon.djbackground}");
</script>