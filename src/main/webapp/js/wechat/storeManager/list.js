var pageii = null;
$(function() {
	storeManagerList();
	$("#search").click("click", function() {// 绑定查询按扭
		storeManagerList();
	});
	$("#addFun").click("click", function() {
		addFun();
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
function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "40%", "80%" ],
		content : rootPath + '/storeManager/addUI.shtml'
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
			storeManagerList();
		} else {
			layer.msg('删除失败');
		}
	});
}

function storeManagerList() {
	var storeManagerTable = $('#storeManagerList');
	storeManagerTable.dataTable().fnClearTable(false);
	storeManagerTable.dataTable().fnDestroy();
	storeManagerTable.dataTable({
		"bAutoWidth": false,
		"bDestory": true,
		"bFilter": false,
		"bPaginate": true,
		"sAjaxSource": rootPath + "/storeManager/findByPage2.shtml",
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
				"sTitle": '<div class="checker"><span class=""><input id="check_all" onclick="checkAll(this);" type="checkbox" class="group-checkable" data-set="#storeManagerList .checkboxes"></span></div>',
				"mDataProp": null,
				"sWidth": "3%",
				"bSortable": false,
				"mRender": function (data, type, full) {
					var id = full["id"];
					return '<div class="checker"><span><input name="id" onclick="checkThis(this);" type="checkbox" class="checkboxes" value="' + id + '"></span></div>';
				}
			},
			{
				"mDataProp": 'storeCode',
				"sTitle": "门店编码",
				"sWidth": '10%'
			}, {
                "mDataProp": 'businessName',
                "sTitle": "门店名称",
                "sWidth": '10%',
                "mRender": function (data, type, full) {
                    var business_name = data;
                    if (business_name) {
                        return business_name;
                    } else {
                        return "";
                    }
                }
            }, {
				"mDataProp": 'branchName',
				"sTitle": "分店名称",
				"sWidth": '10%'
			}, {
				"mDataProp": 'province',
				"sTitle": "省",
				"sWidth": '10%'
			}, {
				"mDataProp": 'city',
				"sTitle": "市",
				"sWidth": '10%'
			}, {
                "mDataProp": 'district',
                "sTitle": "区",
                "sWidth": '10%'
            }, {
                "mDataProp": 'address',
                "sTitle": "街道地址",
                "sWidth": '10%'
            }, {
                "mDataProp": 'telephone',
                "sTitle": "电话",
                "sWidth": '10%'
            }, {
                "mDataProp": 'categories',
                "sTitle": "门店类型",
                "sWidth": '10%'
            }, {
                "mDataProp": 'offsetType',
                "sTitle": "坐标类型",
                "sWidth": '10%'
            }, {
                "mDataProp": 'longitude',
                "sTitle": "经度",
                "sWidth": '10%'
            }, {
                "mDataProp": 'latitude',
                "sTitle": "纬度",
                "sWidth": '10%'
            }, {
                "mDataProp": 'special',
                "sTitle": "特色服务",
                "sWidth": '10%'
            }, {
                "mDataProp": 'openTime',
                "sTitle": "营业时间",
                "sWidth": '10%'
            }, {
                "mDataProp": 'avgPrice',
                "sTitle": "人均价格",
                "sWidth": '10%'
            }, {
                "mDataProp": 'introduction',
                "sTitle": "商户简介",
                "sWidth": '10%'
            }, {
                "mDataProp": 'recommend',
                "sTitle": "推荐品",
                "sWidth": '10%'
            }],
		"aoColumnDefs": [{
			sDefaultContent: '',
			aTargets: ['_all']
		}],
		"fnServerData": function (sSource, aoData, fnCallback) {
			if (!aoData) {
				aoData = [];
			}
			aoData.push({
				name: "businessName",
				value: $("#businessName").val().trim()
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
						storeManagerTable.dataTable().fnProcessingIndicator(false);
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
					storeManagerTable.dataTable().fnProcessingIndicator(false);
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