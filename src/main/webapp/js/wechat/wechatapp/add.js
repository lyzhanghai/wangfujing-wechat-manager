$(function () {
    // 异步加载所有菜单列表
    $("form").validate({
        submitHandler: function (form) {// 必须写在验证前面，否则无法ajax提交
            $.ajax({
                type: "post",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                url: rootPath + '/wechatapp/addEntity.shtml',
                dataType: "json",
                data: $("#form").serialize(),
                success: function (response) {
                    if (response == "success") {
                        layer.confirm('添加成功!是否关闭窗口?', function (index) {
                            window.parent.userList();
                            parent.layer.close(parent.pageii);
                            return false;
                        });
                        $("#form")[0].reset();
                    } else {
                        layer.alert(response, 3);
                    }
                },
                error: function () {
                }
            });
        },
        rules: {
           /* "resFormMap.name": {
                required: true,
                remote: { // 异步验证是否存在
                    type: "POST",
                    url: rootPath + '/resources/isExist.shtml',
                    data: {
                        name: function () {
                            return $("#name").val();
                        }
                    }
                }
            },
            "appid": {
                required: true,
                remote: { // 异步验证是否存在
                    type: "POST",
                    url: rootPath + '/resources/isExist.shtml',
                    data: {
                        resKey: function () {
                            return $("#resKey").val();
                        }
                    }
                }
            },*/
            "appsecret": {
                required: true
            }
        },
        messages: {
          /*  "resFormMap.name": {
                required: "菜单名称不能为空",
                remote: "该菜单名称已经存在"
            },
            "appid": {
                required: "appid不能为空",
                remote: "该标识已经存在"
            },*/
            "appsecret": {
                required: "appsecret不能为空"
            }
        },
        errorPlacement: function (error, element) {// 自定义提示错误位置
            $(".l_err").css('display', 'block');
            // element.css('border','3px solid #FFCCCC');
            $(".l_err").html(error.html());
        },
        success: function (label) {// 验证通过后
            $(".l_err").css('display', 'none');
        }
    });
    var url = rootPath + '/storeInfo/findStoreInfoList2.shtml';
    var data = CommnUtil.ajax(url, null, "json");
    if (data != null) {
        var h = "<option value='0'></option>";
        for (var i = 0; i < data.list.length; i++) {
            console.debug(data.list[i]);
            h += "<option value='" + data.list[i].storeCode + "'>" + data.list[i].businessName + "</option>";
        }
        $("#storeCode").html(h);
    } else {
        layer.msg("获取菜单信息错误，请联系管理员！");
    }
});
