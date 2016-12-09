<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>弹框</title>
    <style>
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
            /*margin-left:100px;*/
        }
        .listStyle li{
            width: 100%;
            line-height: 30px;
            border-bottom: #ccc solid 1px;
        }
        .listStyle th,
        .listStyle td {
            border-bottom:#ccc solid 1px;
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
        table{
            border-collapse:collapse;
            border-spacing:0;
        }
    </style>
</head>
<div class="popUp_content">
    <form name="fromname">
        <table class="listStyle">
            <thead>
            <tr>
                <th>是否授权</th>
                <th>门店名称</th>
                <th>门店编码</th>
            </tr>
            </thead>
            <tbody id="userListIN">
            <c:forEach items="${userAuthorizatioStoreList}" var="UserAuthorizationStoreDto">
                <tr  class="userId" name="${UserAuthorizationStoreDto.userId}">
                    <td><input class="isLoseEfficacy" name="${UserAuthorizationStoreDto.isLoseEfficacy == 0}" type="checkbox" <c:if test="${UserAuthorizationStoreDto.isLoseEfficacy == 0}">checked="checked"</c:if>/></td>
                    <td class="businessName"> ${UserAuthorizationStoreDto.businessName } </td>
                    <td class="storeCode"> ${UserAuthorizationStoreDto.storeCode }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>

</div>
<div class="btnBox">
    <input class="button_blue" id="saveBtn" type="submit" value="保存" name="Confirm" />
    <input class="button_green ml15 cancel" type="button" value="取消" name="cancel" />
</div>
<%--</div>--%>
<script type="text/javascript"  language="javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.3.js"></script>
<script>
    $(function(){
        var userAuthorizatioStoreList = [];
        var userListIN = $("#userListIN");
        var businessName = '';
        var storeCode = '';
        var userId = '';
        var a = {};
        var isLoseEfficafy = '';

        $("#saveBtn").click(function () {
            userListIN.find("tr").each(function(){
                var checkbox = $(this).find('td:eq(0) input');
                // var attrState = checkbox.attr("checked");
                // isLoseEfficacy = $(".isLoseEfficacy").attr("name");
                // checkbox.change(function(){           // 这里是对复选框进行点击操作
                if(checkbox.is(':checked')){        // 如果是被选中状态
                    isLoseEfficacy ="0";           // 将其状态改为未选中状态
                }else{                           // 如果是未被选中状态
                    isLoseEfficacy ="1";          // 将其状态改为被选中状态
                }
                // });

                // var isLoseEfficacyState = $(".isLoseEfficacy").attr("name");
                businessName = $(this).find("td").eq(1).text();
                storeCode = $(this).find("td").eq(2).text();
                userId = $(".userId").attr("name");

                a = {
                    "storeCode":storeCode,
                    "isLoseEfficacy":isLoseEfficacy,
                    "userId":userId,
                    "businessName":businessName
                }

                userAuthorizatioStoreList.push(a);
                console.log(userAuthorizatioStoreList);
            });
            var pathName = document.location.pathname;
            var index = pathName.substr(1).indexOf("/");
            var result = pathName.substr(0, index + 1);
            $.ajax({
                type: "POST",
                url : result+"/userAuthorizatioStore/adduserAuthorizationStore.shtml",
                data: JSON.stringify(userAuthorizatioStoreList),
                contentType:"application/json",
                success: function(msg){
                    alert( "数据保存: " + msg );
                }
            });
        });


//        $("#saveBtn").click(function(){
//
//            var pathName = document.location.pathname;
//            var index = pathName.substr(1).indexOf("/");
//            var result = pathName.substr(0, index + 1);
//
//            alert(result);
//            alert(userAuthorizatioStoreList);
//            $.post(result+ '/userAuthorizatioStore/adduserAuthorizationStore.shtml',{userAuthorizatioStoreList},function(json){
//                // 这里是赵同学进行的保存后台操作
//            }, "json")
//        })

    })

</script>

</body>
</html>

