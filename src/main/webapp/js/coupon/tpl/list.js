/**
 * Created by kongqf on 16-12-8.
 */
$(function () {
    $("#addFun").click("click", function () {
        addFun();
    });
});

function addFun() {
    pageii = layer.open({
        title: "新增",
        type: 2,
        area: ["650px", "80%"],
        content: rootPath + '/tpl/coupontpl.shtml'
    });
}