$(function () {
    $("form").validate({
        submitHandler: function (form) {//必须写在验证前面，否则无法ajax提交
            $.ajax({
                type: "post",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                url: rootPath + '/coupontpl/addCouponTPL.shtml',
                dataType: "json",
                data: $("#form").serialize(),
                success: function (response) {
                    if (response == "success") {
                        layer.confirm('添加成功!是否关闭窗口?', function (index) {
                            //window.parent.userList();
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
        errorPlacement: function (error, element) {//自定义提示错误位置
            $(".l_err").css('display', 'block');
            //element.css('border','3px solid #FFCCCC');
            $(".l_err").html(error.html());
        },
        success: function (label) {//验证通过后
            $(".l_err").css('display', 'none');
        }
    });
    selectCouponType();
    $("#selCouponType").change(function () {
        var obj = $("#selCouponType");
        defaultCouponName(obj.val(), obj.find("option:selected").text());
    });
    $("#txtCouponValue").bind('input propertychange',function () {
        console.debug("22222");
        var obj = $("#selCouponType");
        defaultCouponName(obj.val(), obj.find("option:selected").text());
    });
});

function selectCouponType() {
    var url = rootPath + '/dic/queryDicList.shtml';
    var data = CommnUtil.ajax(url, {"key": "coupon_type"}, "json");
    if (data != null) {
        var h = '';
        for (var i = 0; i < data.list.length; i++) {
            if (h == '') {
                h = "<option selected='selected' value='" + data.list[i].code + "'>" + data.list[i].name + "</option>";
                defaultCouponName(data.list[i].code, data.list[i].name);
            } else {
                h += "<option value='" + data.list[i].code + "'>" + data.list[i].name + "</option>";
            }
        }
        $("#selCouponType").html(h);
    }
    else {
        layer.msg("获券类型错误，请联系管理员！");
    }
}

function defaultCouponName(couponTypeV, couponTypeN) {
    var cv = $("#txtCouponValue").val();
    if (cv == undefined || cv == "" || cv == null) {
        cv = "1";
    }
    var couponName = '';
    if (couponTypeV == 'DJ') {
        couponName = "价值" + cv + "元代金券";
    } else if (couponTypeV == 'ZK') {
        couponName = cv + "%折扣券";
    } else {
        couponName = couponTypeN
    }

    $("#txtCouponName").val(couponName);
}



