var pageii = null;
var id = null;
var res = new Map();
$(function() {
	tree();
	$.ajax({
		url: rootPath + '/resources/reslists.shtml',
		type: 'post',
		dataType: 'json',
		data: {
		},
		async: false,
		success: function (data) {
			if (data != "") {
				for(var i in data){
					res.put(data[i]["id"], data[i]);
				}
			} else {
				bootbox.alert("查询资源列表出错！");
			}
		}
	});
	$("#seach").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();
		grid.setOptions({
			data : searchParams
		});
	});
	$("#addFun").click("click", function() {
		addFun();
	});
	$("#editFun").click("click", function() {
		editFun();
	});
	$("#delFun").click("click", function() {
		delFun();
	});
	$("#lyGridUp").click("click", function() {// 上移
		var jsonUrl=rootPath + '/background/resources/sortUpdate.shtml';
		grid.lyGridUp(jsonUrl);
	});
	$("#lyGridDown").click("click", function() {// 下移
		var jsonUrl=rootPath + '/background/resources/sortUpdate.shtml';
		grid.lyGridDown(jsonUrl);
	});
});
function editFun() {
	/*var cbox = grid.getSelectedCheckbox();*/
	var idd;
	if(id != null && id != ""){
		idd = id.get("id");
	}
	if (idd == null || idd == "") {
		layer.alert("请选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/resources/editUI.shtml?id=' + idd
	});
}
function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/resources/addUI.shtml'
	});
}
function delFun() {
	/*var cbox = grid.getSelectedCheckbox();*/
	var ids = [];
	if (id == "" || id == null) {
		layer.alert("请选择删除项！！");
		return;
	}
	ids.push(id.get("id"));
	ids.push(id.get("children"));
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/resources/deleteEntity.shtml';
		var s = CommnUtil.ajax(url, {
			ids : ids.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('删除成功');
			tree();
		} else {
			layer.msg('删除失败');
		}
	});
}

function tree() {
	$("#menuTree").jstree({
		"core": {
			"themes": {
				"responsive": false
			},
			// so that create works
			"check_callback": true,
			'data': {
				'url': rootPath + '/resources/treelists2.shtml',
				'dataType': 'JSON'
			}
		},
		"types": {
			"0" : {
				"icon" : "jstree-folder"
			},
			"1" : {
				"icon" : "jstree-folder"
			},
			"2" : {
				"icon" : "jstree-node-online"
			}/*,
			"default": {
				"icon": "fa fa-folder icon-state-warning icon-lg"
			},
			"file": {
				"icon": "fa fa-file icon-state-warning icon-lg"
			}*/
		},
		"state": {"key": "demo2"},
		"plugins": ["dnd", "state", "types"]
	})./*on('move_node.jstree', function (e, data) {
		$.get(contextPath + '/menuManage/move.json', {'sid': data.node.id, 'parent': data.parent})
			.done(function (d) {
				data.instance.refresh();
			})
			.fail(function () {
				data.instance.refresh();
			});
	}).*/on('select_node.jstree', function (e, data) {
		id = new Map();
			var idd = data.node.id;
		id.put("id",idd);
		var children = data.node.children_d;
		id.put("children",children);
			var res_id = res.get(parseInt(idd));
			$("#nameDetail").val(res_id.name.trim());
			$("#resKeyDetail").val(res_id.resKey);
			var type = res_id.type;
			var typeName;
			if(type == 0){
				typeName = "目录";
			}else if (type == 1){
				typeName = "菜单";
			}else{
				typeName = "按钮";
			}
			$("#typeDetail").val(typeName);
			$("#iconDetail").val(res_id.icon);
			$("#parentDetail").val(res_id.parentId);
			$("#resUrlDetail").val(res_id.resUrl);
			var isHide = res_id.ishide;
			if(isHide == 0){
				$("#isHide").val("否");
			}else{
				$("#isHide").val("是");
			}

	}).on("ready.jstree", function (e, data) {
		data.instance.open_all();
	});
}

function refreshTree() {
	console.debug("11111111111");
	$("#menuTree").jstree(true).refresh();
}