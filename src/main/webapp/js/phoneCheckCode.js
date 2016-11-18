/**
 * Created by wangxuan on 2016-09-26 0026.
 */
//获取手机验证码
function getPhoneCheckCode(){
	var i = 30;
	var But = $(".v-code .codeBtn");
	But.unbind("click",getPhoneCheckCode);
	But.text(i+"秒后重试");
	var timer = setInterval(function(){
		if(i>1){
			i--;
			But.text(i+"秒后重试");
		} else {
			clearInterval(timer);
			But.bind("click",getPhoneCheckCode);
			But.text("获取验证码");
		}
	},1000);
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		url : getContextPath() + "/memberInfo/getPhoneCheckCode.do",
		async : false,
		data : {
			"phone" : $("#mobile").val(),
			"channelId" : "M2"
		},
		dataType : "json",
		success : function(response) {
			if(response.success){
				
			} else {
				$("#modal_lable").text("抱歉，获取验证码失败！");
				$("#modal_msg").text(response.msg);
				$("#ok_button").attr("onclick", "modalShowOrHide(0, 0)");
				modalShowOrHide(1);
			}
		}
	});
}
//验证手机验证码
function checkPhoneCheckCode(functionName){
	if(typeof(functionName) == "function"){
		var result = false;
		$.ajax({
    		type : "post",
    		contentType : "application/x-www-form-urlencoded;charset=utf-8",
    		url : getContextPath() + "/memberInfo/checkPhoneCheckCode.do",
    		async : false,
    		data : {
    			"phone" : $("#mobile").val(),
    			"channelId" : "M2",
    			"code" : $("#checkCode").val()
    		},
    		dataType : "json",
    		success : function(response) {
    			if(response.success){
    				functionName();
	            	result = true;
    			} else {
    				$("#modal_lable").text("抱歉，验证失败！");
    				$("#modal_msg").text(response.msg);
    				$("#ok_button").attr("onclick", "modalShowOrHide(0, 0)");
    				modalShowOrHide(1);
    			}
    		}
    	});
		return result;
	} else {
		return false;
	}
	
}