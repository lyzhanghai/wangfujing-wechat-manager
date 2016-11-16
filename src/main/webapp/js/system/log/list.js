var dialog;
var grid;
$(function() {
	logList();
	$("#searchForm").click("click", function() {//绑定查询按扭
		var searchParams = $("#fenye").serializeJson();
		grid.setOptions({
			data : searchParams
		});
	});
});

function logList() {
	var logTable = $('#logList');
	logTable.dataTable().fnClearTable(false);
	logTable.dataTable().fnDestroy();
	logTable.dataTable({
		"bAutoWidth": false,
		"bDestory": true,
		"bFilter": false,
		"bPaginate": true,
		"sAjaxSource": rootPath + "/log/findByPage2.shtml",
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
				"mDataProp": 'accountName',
				"sTitle": "账号",
				"sWidth": '10%'
			}, {
				"mDataProp": 'module',
				"sTitle": "模块",
				"sWidth": '10%'
			}, {
				"mDataProp": 'methods',
				"sTitle": "方法",
				"sWidth": '10%'
			}, {
				"mDataProp": 'actionTime',
				"sTitle": "响应时间",
				"sWidth": '10%',
				"mRender": function (data, type, full) {
					var time = data;
					if (time) {
						return new Date(time).format("yyyy-MM-dd hh:mm:ss");
					} else {
						return "";
					}
				}
			}, {
				"mDataProp": 'userIP',
				"sTitle": "IP地址",
				"sWidth": '10%'
			}, {
				"mDataProp": 'operTime',
				"sTitle": "执行时间",
				"sWidth": '10%',
				"mRender": function (data, type, full) {
					var time = data;
					if (time) {
						return new Date(time).format("yyyy-MM-dd hh:mm:ss");
					} else {
						return "";
					}
				}

			},
			{
				"mDataProp": 'description',
				"sTitle": "执行描述",
				"sWidth": '10%'
			},{
				"mDataProp": 'operTime',
				"sTitle": "发送的时间",
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