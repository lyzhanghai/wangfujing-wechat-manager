$(function () {
    var form1 = $('#form');
    var error1 = $('.alert-danger', form1);
    var success1 = $('.alert-success', form1);
    form1.validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block help-block-error', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "",  // validate all fields including form hidden input
        messages: {
            select_multi: {
                maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
                minlength: jQuery.validator.format("At least {0} items must be selected")
            }
        },
        rules: {
            NoLength: {
                number: true,
                required: true
            },
            email: {
                required: true,
                email: true
            },
            url: {
                required: true,
                url: true
            },
            number: {
                required: true,
                number: true
            },
            digits: {
                required: true,
                digits: true
            },
            creditcard: {
                required: true,
                creditcard: true
            },
            occupation: {
                minlength: 5,
            },
            select: {
                required: true
            },
            select_multi: {
                required: true,
                minlength: 1,
                maxlength: 3
            }
        },

        invalidHandler: function (event, validator) { //display error alert on form submit
            success1.hide();
            error1.show();
            Metronic.scrollTo(error1, -200);
        },

        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
        },

        unhighlight: function (element) { // revert the change done by hightlight
            $(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
        },

        success: function (label) {
            label
                .closest('.form-group').removeClass('has-error'); // set success class to the control group
        },

        submitHandler: function (form) {
            $.ajax({
                type: "post",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                url: rootPath + '/couponrule/addEntity.shtml',
                dataType: "json",
                data: $("#form").serialize(),
                success: function (response) {
                    if (response == "success") {
                        layer.alert('添加成功！', 3);
                    } else {
                        layer.alert('添加失败！', 3);
                    }
                },
                error: function () {
                }
            });
            /*success1.show();
             error1.hide();*/
        }
    });

});

function loadCouponBG(djq) {
    var url = rootPath + '/dic/queryDicList.shtml';
    var data = CommnUtil.ajax(url, {"key": "coupon_bg"}, "json");
    console.debug(djq);
    if (data != null) {
        var h = "<option value='0'></option>";
        for (var i = 0; i < data.list.length; i++) {
            if (djq == data.list[i].name) {
                h += "<option value='" + data.list[i].name + "' selected='selected'>"
                    + data.list[i].name + "</option>";
            } else {
                h += "<option value='" + data.list[i].name + "'>" + data.list[i].name + "</option>";
            }
        }
        $("#colorselector_djq").html(h);
    } else {
        layer.msg("获取背景字典错误，请联系管理员！");
    }
}

