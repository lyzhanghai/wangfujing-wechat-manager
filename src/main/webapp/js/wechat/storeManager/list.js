var pageii = null;
$(function() {
	roleList();
	$("#search").click("click", function() {// 绑定查询按扭
		roleList();
	});
	$("#addRole").click("click", function() {
		addRole();
	});
	$("#editRole").click("click", function() {
		editRole();
	});
	$("#delRole").click("click", function() {
		delRole();
	});
	$("#permissions").click("click", function() {
		permissions();
	});
});
function editRole() {
	var ids = [];
	$("input.checkboxes[name='id']:checkbox").each(function () {
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});
	/*var cbox = grid.getSelectedCheckbox();*/
	if (ids.length > 1 || ids == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "40%", "40%" ],
		content : rootPath + '/role/editUI.shtml?id=' + ids
	});
}
function permissions() {
	var ids = [];
	$("input.checkboxes[name='id']:checkbox").each(function () {
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});
	/*var cbox = grid.getSelectedCheckbox();*/
	if (ids.length > 1 || ids == "") {
		layer.msg("请选择一个对象！");
		return;
	}
	var url = rootPath + '/resources/permissions.shtml?roleId='+ids;
	pageii = layer.open({
		title : "分配权限",
		type : 2,
		area : [ "40%", "60%" ],
		content : url
	});
}
function addRole() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "40%", "40%" ],
		content : rootPath + '/role/addUI.shtml'
	});
}
function delRole() {
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
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/role/deleteEntity.shtml';
		var s = CommnUtil.ajax(url, {
			ids : ids.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('删除成功');
			roleList();
		} else {
			layer.msg('删除失败');
		}
	});
}

function roleList() {
	var roleTable = $('#roleList');
	roleTable.dataTable().fnClearTable(false);
	roleTable.dataTable().fnDestroy();
	roleTable.dataTable({
		"bAutoWidth": false,
		"bDestory": true,
		"bFilter": false,
		"bPaginate": true,
		"sAjaxSource": rootPath + "/role/findByPage2.shtml",
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
				"sTitle": '<div class="checker"><span class=""><input id="check_all" onclick="checkAll(this);" type="checkbox" class="group-checkable" data-set="#roleList .checkboxes"></span></div>',
				"mDataProp": null,
				"sWidth": "3%",
				"bSortable": false,
				"mRender": function (data, type, full) {
					var id = full["id"];
					return '<div class="checker"><span><input name="id" onclick="checkThis(this);" type="checkbox" class="checkboxes" value="' + id + '"></span></div>';
				}
			},
			{
				"mDataProp": 'name',
				"sTitle": "角色名",
				"sWidth": '10%',
				"mRender": function (data, type, full) {
					var name = data;
					if (name) {
						return name;
					} else {
						return "";
					}
				}
			}, {
				"mDataProp": 'state',
				"sTitle": "状态",
				"sWidth": '10%'
			}, {
				"mDataProp": 'roleKey',
				"sTitle": "roleKey",
				"sWidth": '10%'
			},  {
				"mDataProp": 'description',
				"sTitle": "描述",
				"sWidth": '10%'
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
				name: "name",
				value: $("#name").val()
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
						roleTable.dataTable().fnProcessingIndicator(false);
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
					roleTable.dataTable().fnProcessingIndicator(false);
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