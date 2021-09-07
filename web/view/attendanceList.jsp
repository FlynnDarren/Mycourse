<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>逾期列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-icons.css">



	<style type="text/css">

		.datagrid-header-row,.datagrid-row {
			height: 45px !important;
		}

		.datagrid-cell, .datagrid-cell-group, .datagrid-header-rownumber, .datagrid-cell-rownumber{
			font-size: 20px !important;
			height: 30px !important;
			text-align: center !important;
			line-height: 30px !important;
		}

		.datagrid-header .datagrid-cell span{
			font-size: 16px !important;
		}


		.panel-header {
			height: 35px !important;
		}

		.pagination span{
			font-size: 16px !important;
		}

		.panel-title {
			font-size: 18px !important;
		}

		.datagrid-toolbar, .datagrid-pager{
			height: 50px !important;
		}

		.btn{
			/*width: 80px !important;*/
			height: 40px !important;
			font-size: 16px !important;
		}

		.textbox{
			width: 175px !important;
			height: 30px !important;
		}
	</style>


	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>

	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'逾期列表',
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible: false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"AttendanceServlet?method=AttendanceList&t="+new Date().getTime(),
	        idField:'id',
			singleSelect: true,//是否单选
	        pagination: true,//分页控件
	        rownumbers: true,//行号 
	        sortName:'id',
	        sortOrder:'DESC', 
	        remoteSort: false,
	        columns: [[  
				{field:'chk',checkbox:true,width:50},
				{field:'id',title:'序号',width:120, sortable:true},
				{field:'studentId',title:'学号',width:150, sortable:false},
 		        {field:'student_name',title:'学生',width:200,
 		        	formatter: function(value,row,index){
 						if (row.studentId){
 							var studentList = $("#studentList").combobox("getData");
 							for(var i=0;i<studentList.length;i++ ){
 								//console.log(clazzList[i]);
 								if(row.studentId == studentList[i].id)
								{
 									return studentList[i].name;

								}

 							}
 							return row.studentId;
 						} else {
 							return 'not found';
 						}
 					}	
 		        },
 		       	// {field:'courseId',title:'课程',width:200,
 		        // 	formatter: function(value,row,index){
 				// 		if (row.courseId){
 				// 			var courseList = $("#courseList").combobox("getData");
 				// 			for(var i=0;i<courseList.length;i++ ){
 				// 				//console.log(clazzList[i]);
 				// 				if(row.courseId == courseList[i].id)return courseList[i].name;
 				// 			}
 				// 			return row.courseId;
 				// 		} else {
 				// 			return 'not found';
 				// 		}
 				// 	}
 		       	// },
 		       {field:'type',title:'是否逾期',width:75, sortable: false , },
 		       {field:'date',title:'请假开始时间',width:240, sortable: false},
				{field:'over_date',title:'请假截止时间',width:240, sortable: false,  //可以直接读到id，数据库里面，有的字段，通过修改‘id’ 都可以拿到
				 //  formatter: function(value,row,index){
 		      	// console.log("@@@@@@"+value);
					//   if (row.user){
					// 	  return row.user.name;
					//   } else {
					// 	  return value;
					//   }
				 //  }
				},

				{field:'back_date',title:'返校时间',width:240, sortable: false},
	 		]],
	        toolbar: "#toolbar",
	        onBeforeLoad : function(){
	        	try{
	        		$("#studentList").combobox("getData")
	        	}catch(err){
	        		preLoadClazz();
	        	}
	        }
	    });


	    console.log($('#dataList').innerHTML);
		//提前加载学生和课程信息
	    function preLoadClazz(){
	  		$("#studentList").combobox({
		  		width: 80,
		  		height: 25,
		  		valueField: "id",
		  		textField: "name",
		  		multiple: false, //可多选

		  		editable: false, //不可编辑
		  		method: "post",
		  		url: "StudentServlet?method=StudentList&t="+new Date().getTime()+"&from=combox",
		  		
		  	});
	  		$("#courseList").combobox({
		  		width: 80,
		  		height: 25,
		  		valueField: "id",
		  		textField: "name",
		  		multiple: false, //可多选
		  		editable: false, //不可编辑
		  		method: "post",
		  		url: "CourseServlet?method=CourseList&t="+new Date().getTime()+"&from=combox",
		  		
		  	});
	  	}
		
	  //设置分页控件 
	    var p = $('#dataList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    });
	   	
	    //设置工具类按钮
	    $("#add").click(function(){
	    	$("#addDialog").dialog("open");
	    });
	    
	  //设置编辑按钮
	    $("#edit").click(function(){
	    	table = $("#editTable");
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
		    	$("#editDialog").dialog("open");
            }
	    });
	    
	    
	    //删除
	    $("#delete").click(function(){
	    	var selectRow = $("#dataList").datagrid("getSelected");
        	if(selectRow == null){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	var id = selectRow.id;
            	$.messager.confirm("消息提醒", "确认删除吗，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "AttendanceServlet?method=DeleteAttendance",
							data: {id: id},
							success: function(msg){
								if(msg == "success"){
									$.messager.alert("消息提醒","删除成功!","info");
									//刷新表格
									$("#dataList").datagrid("reload");
								}else if(msg == "not found"){
									$.messager.alert("消息提醒","不存在该选课记录!","info");
								}else{
									$.messager.alert("消息提醒","删除失败!","warning");
									return;
								}
							}
						});
            		}
            	});
            }
	    });
	  	
	  	//设置添加窗口
	    $("#addDialog").dialog({
	    	title: "查询窗口",
	    	width: 450,
	    	height: 300,
	    	iconCls: "",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'查询',
					plain: true,
					iconCls:'icon-book-add',
					handler:function(){
						var validate = $("#addForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							$.ajax({
								type: "post",
								url: "AttendanceServlet?method=AddAttendance",
								data: $("#addForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","选课信息添加成功!","info");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#add_name").textbox('setValue', "");
										//刷新
										$('#dataList').datagrid("reload");
									} else{
										$.messager.alert("消息提醒",msg,"warning");
										return;
									} 
								}
							});
						}
					}
				},
				{
					text:'重置',
					plain: true,
					iconCls:'icon-book-reset',
					handler:function(){
						$("#add_name").textbox('setValue', "");
					}
				},
			]
	    });
	  	
	  //下拉框通用属性
	  // 	$("#add_studentList, #add_courseList,#studentList,#courseList,#add_typeList").combobox({
	  	$("#add_studentList, #studentList,#add_typeList").combobox({
	  		width: "200",
	  		height: "30",
	  		valueField: "id",
	  		textField: "name",
	  		multiple: false, //不可多选
	  		editable: true, //不可编辑
	  		method: "post",
	  	});

	  	//添加信息教师选择框
	    $("#add_studentList").combobox({
	  		url: "StudentServlet?method=StudentList&t="+new Date().getTime()+"&from=combox",
	  		onLoadSuccess: function(){
				//默认选择第一条数据
				var data = $(this).combobox("getData");
				$(this).combobox("setValue", data[0].id);
				getStudentSelectedCourseList(data[0].id);
	  		},
	  		onChange:function(id,o){
	  			getStudentSelectedCourseList(id);
	  		}
	  	});
	  	
	  	function getStudentSelectedCourseList(studentId){
	  	//添加信息课程选择框
		    $("#add_courseList").combobox({
		  		url: "AttendanceServlet?method=getStudentSelectedCourseList&t="+new Date().getTime()+"&student_id="+studentId,
		  		onLoadSuccess: function(){
					//默认选择第一条数据
					var data = $(this).combobox("getData");
					console.log(data);
					$(this).combobox("setValue", data[0].id);
		  		}
		  	});
	  	}
	  	var typeData = [{id:"是",text:"是"},{id:"否",text:"否"}];
	  	$("#add_typeList").combobox({
	  		data:typeData,
	  		valueField: 'id',
	  		textField: 'text',
	  		onLoadSuccess: function(){
				//默认选择第一条数据
				var data = $(this).combobox("getData");
				console.log(data)
				$(this).combobox("setValue", data[0].id);
	  		}
	  	});
	  	
	  	$("#typeList").combobox({
	  		data:typeData,
	  		valueField: 'id',
	  		textField: 'text',
	  		width: "80",
	  		height: "25",
	  		onLoadSuccess: function(){
				//默认选择第一条数据
				var data = $(this).combobox("getData");
				$(this).combobox("setValue", data[0].id);
	  		}
	  	});

	  	
	    //搜索按钮监听事件
	  	$("#search-btn").click(function(){
	  		$('#dataList').datagrid('load',{
	  			studentid: $("#studentList").combobox('getValue') == '' ? 0 : $("#studentList").combobox('getValue'),
				// studentid: $("#studentList").val(),

				// courseid: $("#courseList").combobox('getValue') == '' ? 0 : $("#courseList").combobox('getValue'),
				type: $("#typeList").combobox('getValue') == '' ? '' : $("#typeList").combobox('getValue'),
				// date:$("#date").datebox('getValue')
	  		});
	  	});
	    
	    $("#clear-btn").click(function(){
	    	$('#dataList').datagrid("reload",{});
	    	$("#studentList").combobox('clear');
	    	$("#courseList").combobox('clear');
	    });



		///////////////////////
		$($('#dataList').datagrid('getPager')[0].children[1]).css("font-size","20px");
		$($('#dataList').datagrid('getPager')[0].children[0]).attr("cellpadding",4);
		$(".pagination-page-list").css("font-size","16px");


	});
	</script>
	<script type="text/javascript">
		function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){
			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}
	</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table> 
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><button id="add" href="javascript:;" class="btn btn-warning" >
			<i class="bi bi-question-circle"></i>
			查询当日未返校学生</button></div>

		<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left; margin-right: 10px;display: flex"><a id="delete" href="javascript:;" class="btn btn-danger" data-options="iconCls:'icon-some-delete',plain:true">
			<i class="bi bi-trash"></i>

			删除</a></div>
		<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="margin-top: 3px;display: flex">
			<span style="font-size: 15px">学生：</span>

			<input id="studentList" class="easyui-textbox" name="studentList" />
			<span style="font-size: 15px">是否逾期：</span>

			<input id="typeList" class="easyui-textbox" name="typeList" />
			<a id="search-btn" style="margin-left: 10px" href="javascript:;" class="btn btn-primary" data-options="iconCls:'icon-search',plain:true">
				<i class="bi bi-search"></i>

				搜索</a>
			<a id="clear-btn" style="margin-left: 10px" href="javascript:;" class="btn btn-success" data-options="iconCls:'icon-search',plain:true">
				<i class="bi bi-trash2-fill"></i>
				清空搜索</a>
		</div>
	</div>
	
	<!-- 添加数据窗口 -->
<%--	<div id="addDialog" style="padding: 10px">  --%>
<%--    	<form id="addForm" method="post">--%>
<%--	    	<table cellpadding="8" >--%>
<%--	    		<tr>--%>
<%--	    			<td style="width:40px">学生:</td>--%>
<%--	    			<td colspan="3">--%>
<%--&lt;%&ndash;	    				<input id="add_studentList" style="width: 200px; height: 30px;" class="easyui-textbox" name="studentid" data-options="required:true, missingMessage:'请选择学生'" />&ndash;%&gt;--%>
<%--	    				<input id="add_studentList" style="width: 200px; height: 30px;" class="easyui-textbox" name="studentid" />--%>
<%--	    			</td>--%>
<%--	    			<td style="width:80px"></td>--%>
<%--	    		</tr>--%>
<%--&lt;%&ndash;	    		<tr>&ndash;%&gt;--%>
<%--&lt;%&ndash;	    			<td style="width:40px">课程:</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;	    			<td colspan="3">&ndash;%&gt;--%>
<%--&lt;%&ndash;	    				<input id="add_courseList" style="width: 200px; height: 30px;" class="easyui-textbox" name="courseid" data-options="required:true, missingMessage:'请选择课程'" />&ndash;%&gt;--%>
<%--&lt;%&ndash;	    			</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;	    			<td style="width:80px"></td>&ndash;%&gt;--%>
<%--&lt;%&ndash;	    		</tr>&ndash;%&gt;--%>
<%--	    		<tr>--%>
<%--	    			<td style="width:40px">是否逾期:</td>--%>
<%--	    			<td colspan="3">--%>
<%--	    				<input id="add_typeList" style="width: 200px; height: 30px;" class="easyui-textbox" name="type" data-options="required:true, missingMessage:'请选择类型'" />--%>
<%--	    			</td>--%>
<%--	    			<td style="width:80px"></td>--%>
<%--	    		</tr>--%>

<%--				<input id="over_date" style="display: none;width: 200px; height: 30px;" name="over_date" />--%>
<%--				<input id="mydate" style="display: none;width: 200px; height: 30px;" name="date" />--%>

<%--			</table>--%>
<%--	    </form>--%>

	</div>
	<script type="text/javascript">

		$("#add").click(function () {

			$.ajax({
				type: "post",
				url: "AttendanceServlet?method=AddAttendance",
				// data: $("#addForm").serialize(),
				success: function (msg) {
					if (msg == "success") {
						// $.messager.alert("消息提醒", "选课信息添加成功!", "info");

						//清空原表格数据
						$("#add_name").textbox('setValue', "");
						//刷新
						$('#dataList').datagrid("reload");
					} else {
						$.messager.alert("消息提醒", msg, "warning");
						return;
					}
				}

			})
		})
	</script>
</body>
</html>