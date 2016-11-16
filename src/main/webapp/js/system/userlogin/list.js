var pageii = null;
/*var grid = null;*/
$(function() {
	uesrLoginList();
	/*grid = lyGrid({
		id : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
			width : "50px",
			hide : true
		}, {
			colkey : "accountName",
			name : "账号"
		},{
			colkey : "loginTime",
			name : "登入时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		} , {
			colkey : "loginIP",
			name : "登入IP"
		}],
		jsonUrl : rootPath + '/userlogin/findByPage.shtml',
		checkbox : true
	});*/
	/*$("#searchForm").click("click", function() {// 绑定查询按扭
		var searchParams = $("#fenye").serializeJson();
		grid.setOptions({
			data : searchParams
		});
	});*/
});

function uesrLoginList() {
	var userLoginTable = $('#userLoginList');
	userLoginTable.dataTable().fnClearTable(false);
	userLoginTable.dataTable().fnDestroy();
	userLoginTable.dataTable({
		"bAutoWidth": false,
		"bDestory": true,
		"bFilter": false,
		"bPaginate": true,
		"sAjaxSource": rootPath + "/userlogin/findByPage2.shtml",
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
		"aoColumns": [{
				"mDataProp": 'accountName',
				"sTitle": "账号",
				"sWidth": '10%'
			}, {
				"mDataProp": 'loginIP',
				"sTitle": "登入IP",
				"sWidth": '10%'
			}, {
				"mDataProp": 'loginTime',
				"sTitle": "登入时间",
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
						userLoginTable.dataTable().fnProcessingIndicator(false);
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
					userLoginTable.dataTable().fnProcessingIndicator(false);
				}
			});
		},
		"fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
			//console.log(nRow, aData, iDisplayIndex, iDisplayIndexFull);
		}
	});
}