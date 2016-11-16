var dialog;
$(function() {
	monitorList();
});

function monitorList() {
	var monitorTable = $('#monitorList');
	monitorTable.dataTable().fnClearTable(false);
	monitorTable.dataTable().fnDestroy();
	monitorTable.dataTable({
		"bAutoWidth": false,
		"bDestory": true,
		"bFilter": false,
		"bPaginate": true,
		"sAjaxSource": rootPath + "/monitor/findByPage2.shtml",
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
				"mDataProp": 'cpuUsage',
				"sTitle": "cpu使用率",
				"sWidth": '10%',
				"mRender": function (data, type, full) {
					var cpuUsage = data;
					if (cpuUsage) {
						return cpuUsage;
					} else {
						return "";
					}
				}
			}, {
				"mDataProp": 'setCpuUsage',
				"sTitle": "预设cpu使用率",
				"sWidth": '10%'
			}, {
				"mDataProp": 'jvmUsage',
				"sTitle": "Jvm使用率",
				"sWidth": '10%'
			}, {
				"mDataProp": 'setJvmUsage',
				"sTitle": "预设Jvm使用率",
				"sWidth": '10%'
			}, {
				"mDataProp": 'ramUsage',
				"sTitle": "Ram使用率",
				"sWidth": '10%'
			}, {
				"mDataProp": 'setRamUsage',
				"sTitle": "预设Ram使用率",
				"sWidth": '10%'
			},
			{
				"mDataProp": 'email',
				"sTitle": "发送的邮件",
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
			},
			{
				"mDataProp": 'mark',
				"sTitle": "备注",
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