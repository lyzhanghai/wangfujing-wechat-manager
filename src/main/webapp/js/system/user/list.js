var id;
var pageii = null;
var buttonMap = new Map();
$(function () {
	userList();
	$("#search").click("click", function () {// 绑定查询按扭
		/*var searchParams = $("#searchForm").serializeJson();// 初始化传参数*/
		userList();
	});

	$("#callback_test").click("click", function () {
		paging_callback();
	});
	$("#addAccount").click("click", function () {
		addAccount();
	});

	$("#editAccount").click("click", function () {
		editAccount();
	});
	$("#delAccount").click("click", function () {
		delAccount();
	});
	$("#permissions").click("click", function () {
		permissions();
	});
	$("#uthorizationStore").click("click", function () {
		uthorizationStore();
	});
});

function editAccount() {
	var ids = [];
	$("input.checkboxes[name='id']:checkbox").each(function () {
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});
	if (ids.length > 1 || ids == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title: "编辑",
		type: 2,
		area: ["40%", "65%"],
		content: rootPath + '/user/editUI.shtml?id=' + ids
	});
}
function addAccount() {
	pageii = layer.open({
		title: "新增",
		type: 2,
		area: ["40%", "70%"],
		content: rootPath + '/user/addUI.shtml'
	});
}
function delAccount() {
	var ids = [];
	$("input.checkboxes[name='id']:checkbox").each(function () {
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});
	/*var cbox = grid.getSelectedCheckbox();*/
	if (ids == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function (index) {
		var url = rootPath + '/user/deleteEntity.shtml';
		var s = CommnUtil.ajax(url, {
			ids: ids.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('删除成功');
			userList();
			/*grid.loadData();*/
		} else {
			layer.msg('删除失败');
		}
	});
}
function permissions() {
	/*var cbox = grid.getSelectedCheckbox();*/
	var ids = [];
	$("input.checkboxes[name='id']:checkbox").each(function () {
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});
	if (ids.length > 1 || ids == "") {
		layer.msg("请选择一个对象！");
		return;
	}
	var url = rootPath + '/resources/permissions.shtml?userId=' + ids;
	pageii = layer.open({
		title: "分配权限",
		type: 2,
		area: ["40%", "65%"],
		content: url
	});
}

function exportExcel(url) {
	var form = $("<form>");//定义一个form表单
	form.attr("style", "display:none");
	form.attr("target", "");
	form.attr("method", "post");
	form.attr("action", rootPath + url);
	$("body").append(form);//将表单放置在web中
	var input1 = $("<input>");
	input1.attr("type", "hidden");
	input1.attr("name", "exportData");
	input1.attr("value", '[{"colkey":"id","name":"id","width":"auto","theadClass":"","tbodyClass":"","height":"auto","align":"center","hide":false,"isSort":false,"renderData":null},{"colkey":"userName","name":"用户名","isSort":true,"width":"auto","theadClass":"","tbodyClass":"","height":"auto","align":"center","hide":false,"renderData":null},{"colkey":"accountName","name":"账号","isSort":true,"width":"auto","theadClass":"","tbodyClass":"","height":"auto","align":"center","hide":false,"renderData":null},{"colkey":"roleName","name":"所属角色","width":"auto","theadClass":"","tbodyClass":"","height":"auto","align":"center","hide":false,"isSort":false,"renderData":null},{"colkey":"locked","name":"账号状态","width":"90px","isSort":true,"theadClass":"","tbodyClass":"","height":"auto","align":"center","hide":false,"renderData":null},{"colkey":"description","name":"描述","width":"auto","theadClass":"","tbodyClass":"","height":"auto","align":"center","hide":false,"isSort":false,"renderData":null},{"colkey":"createTime","name":"时间","isSort":true,"width":"auto","theadClass":"","tbodyClass":"","height":"auto","align":"center","hide":false}]');
	form.append(input1);

	form.submit();//表单提交
}

function userList() {
	var userTable = $('#userList');
	userTable.dataTable().fnClearTable(false);
	userTable.dataTable().fnDestroy();
	userTable.dataTable({
		"bAutoWidth": false,
		"bDestory": true,
		"bFilter": false,
		"bPaginate": true,
		"sAjaxSource": rootPath + "/user/findByPage2.shtml",
		"bProcessing": true,
		"searching": false, //去掉搜索框
		"bLengthChange": false,// 是否允许自定义每页显示条数.
		"bServerSide": true,
		"iDisplayLength": 10,
		"bSort": false,
		"oLanguage": {//语言设置
			"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
			"sInfoFiltered": "(总共 _MAX_ 条数据)",
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "前一页",
				"sNext": "后一页",
				"sLast": "尾页"
			},
			"sZeroRecords": "抱歉， 没有找到",
			"sInfoEmpty": "没有数据",
			"sLoadingRecords": "加载中...",
			"sProcessing": "处理中..."
		},
		"aoColumns": [
			{
				"sTitle": '<div class="checker"><span class=""><input id="check_all" onclick="checkAll(this);" type="checkbox" class="group-checkable" data-set="#userList .checkboxes"></span></div>',
				"mDataProp": null,
				"sWidth": "3%",
				"bSortable": false,
				"mRender": function (data, type, full) {
					var id = full["id"];
					return '<div class="checker"><span><input name="id" onclick="checkThis(this);" type="checkbox" class="checkboxes" value="' + id + '"></span></div>';
				}
			},
			{
				"mDataProp": 'id',
				"sTitle": "id",
				"sWidth": '10%',
				"mRender": function (data, type, full) {
					var id = data;
					if (id) {
						return id;
					} else {
						return "";
					}
				}
			}, {
				"mDataProp": 'userName',
				"sTitle": "用户名",
				"sWidth": '10%'
			}, {
				"mDataProp": 'accountName',
				"sTitle": "账号",
				"sWidth": '10%'
			}, {
				"mDataProp": 'roleName',
				"sTitle": "所属角色",
				"sWidth": '10%'
			}, {
				"mDataProp": 'locked',
				"sTitle": "账号状态",
				"sWidth": '10%'
			}, {
				"mDataProp": 'description',
				"sTitle": "描述",
				"sWidth": '10%'
			}, {
				"mDataProp": 'createTime',
				"sTitle": "时间",
				"sWidth": '10%',
				"mRender": function (data, type, full) {
					var time = data;
					if (time) {
						return new Date(time).format("yyyy-MM-dd hh:mm:ss");
					} else {
						return "";
					}
				}
			}/*,
			 {
			 "mDataProp": '',
			 "sTitle": "操作",
			 "sWidth": '10%',
			 "mRender": function (data, type, full) {
			 var key = buttonMap.keySet();
			 var button = "";
			 for(var i in key){
			 button += buttonMap.get(key[i]);
			 }
			 return button;
			 }
			 }*/],
		"aoColumnDefs": [{
			sDefaultContent: '',
			aTargets: ['_all']
		}],
		"fnServerData": function (sSource, aoData, fnCallback) {
			if (!aoData) {
				aoData = [];
			}
			aoData.push({
				name: "accountName",
				value: $("#accountName").val()
			});
			$.ajax({
				"dataType": 'json',
				"type": "POST",
				"url": sSource,
				"data": aoData,
				"success": function (json, flag) {
					if (flag && json) {
						fnCallback(json);
					} else {
						userTable.dataTable().fnProcessingIndicator(false);
						bootbox.alert("查询失败，请稍后再试！");
					}
				},
				"timeout": 15000,
				"error": function (xhr, textStatus, error) {
					console.log(xhr, textStatus, error);
					if (textStatus === 'timeout') {
						alert('The server took too long to send the data.');
					} else if (textStatus === 'Not Found') {
						alert('The server not found.');
					} else {
						alert('An error occurred on the server. Please try again in a minute.');
					}
					userTable.dataTable().fnProcessingIndicator(false);
				}
			});
		},
		"fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
			//console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
		}
	});
}


function checkAll(obj) {
	var checked = $(obj).is(":checked");
	if (checked) {
		$(obj).parents("span").addClass("checked");
	} else {
		$(obj).parents("span").removeClass("checked");
	}
	var set = $(obj).attr("data-set");
	$(set).each(function () {
		if (checked) {
			$(this).parents("span").addClass("checked");
			$(this).attr("checked", true);
			$(this).parents('tr').addClass("active");
		} else {
			$(this).parents("span").removeClass("checked");
			$(this).attr("checked", false);
			$(this).parents('tr').removeClass("active");
		}
	});
	$.uniform.update(set);
}

function checkThis(obj) {
	var checked = $(obj).is(":checked");
	if (checked) {
		$(obj).parents("span").addClass("checked");
		$(obj).attr("checked", true);
		$(obj).parents('tr').addClass("active");
	} else {
		$(obj).parents("span").removeClass("checked");
		$(obj).attr("checked", false);
		$(obj).parents('tr').removeClass("active");
	}
}

function uthorizationStore() {
	/*var cbox = grid.getSelectedCheckbox();*/
	var ids = [];
	$("input.checkboxes[name='id']:checkbox").each(function () {
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});
	if (ids.length > 1 || ids == "") {
		layer.msg("请选择一个对象！");
		return;
	}
	var url = rootPath + '/resources/permissions.shtml?userId=' + ids;
	pageii = layer.open({
		title: "授权门店",
		type: 2,
		area: ["40%", "65%"],
		content: url
	});
}