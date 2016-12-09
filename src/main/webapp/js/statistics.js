
var xtext = [];
var data = [];
var ins = [1,2,3];
var chart;  
	$(document).ready(function () {
		$.ajax({
			url : "/notebook/statistics/getCurveData.json",
			type : "POST",
			dataType : "json",
			success : function(delData){
			 	var xstr = delData.xAxis;
			 	var dataStr = delData.data;
			 	xtext = xstr.split(",");
			 	data = dataStr.split(",");
			 	 //柱形图初始化  
			    
			    chart = new Highcharts.Chart({  
			        chart: {  
			            renderTo: 'js_msg_chart',  
			            type: 'column'  
			        },  
			        title: {  
			            text: '数据柱形图表'  
			        },  
			        subtitle: {  
			            text: 'Data column chart'  
			        },  
			        xAxis: {  
			            categories: xtext  
			        },  
			        yAxis: {  
			            min: 0,
			            tickPositions:ins
			        },  
			        legend: {  
			            layout: 'vertical',  
			            backgroundColor: '#FFFFFF',  
			            align: 'left',  
			            verticalAlign: 'top',  
			            x: 100,  
			            y: 70,  
			            floating: true,  
			            shadow: true  
			        },  
			        tooltip: {  
			            formatter: function () {  
			                return '' +  
			            this.x + ': ' + this.y + ' 单位';  
			            }  
			        },  
			        plotOptions: {  
			            column: {  
			                pointPadding: 0.2,  
			                borderWidth: 0  
			            }  
			        },  
			        series: [{  
			            data: data  
			        }]  
			    }); 
			}
			
		});
	    
	});  