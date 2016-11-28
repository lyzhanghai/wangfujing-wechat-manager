//获取项目名称
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
}
//获取地址上的数据
var jsonData;
function getJsonData() {
    var urlPath = decodeURI(document.location.href);
    var index = urlPath.indexOf("?");
    if (index >= 0) {
        var dataList = urlPath.substr(index + 1);
        var keyValues = dataList.split("&");
        var json = "{";
        for (var i = 0; i < keyValues.length; i++) {
            var dataKeyValue = keyValues[i];
            var keyValue = dataKeyValue.split("=");
            json += "'" + keyValue[0] + "':'" + keyValue[1] + "',";
        }
        json = json.substr(0, json.length - 1) + "}";
        jsonData = eval("(" + json + ")");
    } else {
        jsonData = {};
    }
}
function getUrlDataByKey(key) {
    if (typeof(jsonData) == "undefined") {
        getJsonData();
    }
    return jsonData[key];
}
//提示框的控制//确定返回到首页
function modalShowOrHide(flag, isJump) {//flag--1:显示  0:隐藏; isJump--1:跳转  0:不跳转
    if (flag == 1) {
        $("#myModal").show();
        $("body").addClass("modal-open");
        $("#myModal").addClass("in");
        $(".modal-backdrop").show();
    } else {
        $("#myModal").hide();
        $("body").removeClass("modal-open");
        $("#myModal").removeClass("in");
        $(".modal-backdrop").hide();
        if (isJump == 1) {
            loadIndex();
        }
    }
}
//返回到首页
function loadIndex() {
    $.ajax({
        type: "post",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: rootPath + "/memberInfo/getBackIndexUrl.json",
        async: false,
        data: {
            "state": getUrlDataByKey("appId"),
            "openId": getUrlDataByKey("openId")
        },
        dataType: "json",
        success: function (response) {
            response = JSON.parse(response);
            if (response.success) {
                location.href = response.data;
            }
        }
    });
}