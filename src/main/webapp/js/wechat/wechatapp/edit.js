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
                        layer.confirm('更新成功!是否关闭窗口?', function (index) {
                            window.parent.userList();
                            parent.layer.close(parent.pageii);
                            return false;
                        });
                        $("#form")[0].reset();
                    } else {
                        layer.alert('添加失败！', 3);
                    }
                },
                error: function () {
                }
            });
        },
        rules: {
            resKey: {
                required: true
            },
            resUrl: {
                required: true
            }
        },
        messages: {
            resKey: {
                required: "菜单标识不能为空"
            },
            resUrl: {
                required: "url连接不能为空"
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
});

function loadStoreSelect(storeCode) {
    var url = rootPath + '/storeInfo/findStoreInfoList2.shtml';
    var data = CommnUtil.ajax(url, null, "json");
    if (data != null) {
        var h = "<option value='0'></option>";
        for (var i = 0; i < data.list.length; i++) {
            if (parseInt(storeCode, 10) == parseInt(data.list[i].storeCode, 10)) {
                h += "<option value='" + data.list[i].storeCode + "' selected='selected'>"
                    + data.list[i].businessName + "</option>";
            } else {
                h += "<option value='" + data.list[i].storeCode + "'>" + data.list[i].businessName + "</option>";
            }
        }
        $("#storeCode").html(h);
    } else {
        bootbox.alert("获取门店信息错误，请联系管理员！");
    }
}