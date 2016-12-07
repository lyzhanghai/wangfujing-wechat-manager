
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>弹框</title>
    <style>
        .clickBtn {
            display: block;
            margin: 100px auto;
        }
        .iframe_box {
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            opacity: 0.7;
            z-index: 1000;
            background-color: #000;
        }
        .popUp{
            position: absolute;
            top: 50%;
            left: 50%;
            margin-left: -266px;
            margin-top: -215px;
            width: 532px;
            height: 430px;
            border: #ccc solid 1px;
            z-index: 999999;
            background: #fff;
        }
        .fenpei {
            margin-left: 10px;
            font-size: 14px;
            float: left;
        }
        .close {
            margin: 10px 10px 0 0;
            float: right;
            display: inline-block;
            width: 16px;
            height: 16px;
            /*background: url(images/close.png) 0 0 no-repeat;*/
        }
        .btnBox {
            text-align: center;
        }
        .button_blue,
        .button_green {
            width: 125px;
            height: 35px;
            line-height: 35px;
            color: #fff;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .button_green {
            background: #5BB85D;
        }
        .button_blue {
            background: #5BC0DE;
        }
        .ml15 {
            margin-left: 6px;
        }
        .listStyle {
            width: 400px;
            border: #ccc solid 1px;
            margin: 20px auto;
            list-style: none;
            font-size: 13px;
            font-weight: 600;
            color: #797268;
            padding: 0;
        }
        input[type="checkbox"]{
            vertical-align: middle;
        }
        .listStyle li{
            width: 100%;
            line-height: 30px;
            border-bottom: #ccc solid 1px;
        }
        .listStyle span {
            vertical-align: middle;
        }
        .popUp_content {
            margin-bottom: 20px;
            height: 330px;
            text-align: center;
            overflow-y: auto;
        }
        .popUp_head {
            width: 100%;
            height: 35px;
            line-height: 35px;
            background: #E9E9E9;
            border-bottom: #ccc solid 1px;
        }
    </style>
    <link href="css/base.css" rel="stylesheet" type="text/css" />
</head>
<button class="clickBtn">点击弹出弹框</button>
<!--弹窗iframe-->
<div class="iframe_box"></div>
<!-- 退出弹窗 -->
<div class="popUp">
    <div class="popUp_head clearfix">
        <span class="fenpei">分配权限</span>
        <a href="#" class="close"></a>
    </div>
    <div class="popUp_content">
        <ul class="listStyle">
            <c:forEach items="${userAuthorizatioStoreList}" var="UserAuthorizationStoreDto" >
                <li>
                    <input type="checkbox" <c:if test="${UserAuthorizationStoreDto.isLoseEfficacy == 0}">
                        checked="checked"
                    </c:if>/>
                    <span>${UserAuthorizationStoreDto.businessName }</span>
                 </li>
            </c:forEach>
        </ul>

    </div>
    <div class="btnBox">
        <input class="button_blue" type="button" value="保存" name="Confirm" />
        <input class="button_green ml15 cancel" type="button" value="取消" name="cancel" />
    </div>
</div>
<script type="text/javascript" language="javascript" src="js/jquery-1.9.1.min.js"></script>
</body>
</html>
