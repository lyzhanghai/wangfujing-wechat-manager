/**
 * Created by XS on 2016/12/7.
 */
function onuser(storeNo,userId){
    $.ajax({
        type: "post",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: rootPath + '/user/testlogin.shtml',
        dataType: "json",
        data: "storeNo="+storeNo+"&userId="+userId,
        success: function (response) {
            window.location.href=rootPath+response;
        },
        error: function () {
            alert("error");
        }
    });

}