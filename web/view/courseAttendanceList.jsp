<%@ page import="com.school.entiey.Admin" %>
<%@ page import="com.school.entiey.CourseAttendance" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>课程考勤列表</title>
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
			width: 80px !important;
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

            var div_='';
        if(sessionStorage.getItem("course_id")==null){


        }else {

            div_='&course_id='+parseInt(sessionStorage.getItem("course_id"));
        }

	$(function() {
		//datagrid初始化
	    $('#dataList').datagrid({
	        title:'课程考勤管理',
	        iconCls:'icon-more',//图标
	        border: true,
	        collapsible: false,//是否可折叠的
	        fit: true,//自动大小
	        method: "post",
	        url:"CourseAttendanceServlet?method=CourseAttendance"+div_,

	        idField:'student_id',
	        singleSelect: false,//是否单选
	        pagination: true,//分页控件
	        rownumbers: true,//行号
	        sortName:'student_id',
	        sortOrder:'DESC',
	        remoteSort: false,
	        columns: [[
				{field:'chk',checkbox: true,width:50},
 		        // {field:'id',title:'序号',width:50, sortable: true},
 		        {field:'course_id',title:'课程ID',width:100,sortable: true},
				{field:'courseName',title:'课程名',width:100},
 		       	{field:'student_id',title:'学号',width:80},
 		       	{field:'studentName',title:'姓名',width:80},
 		       	{field:'date',title:'上课时间',width:100},
 		        {field:'arrdate',title:'签到时间',width:100},
				{field:'type',title:'签到状态',width:200},
 		        {field:'arrnum',title:'已到人数',width:80},
 		        {field:'allnum',title:'应到人数',width:80},

	 		]],
	        toolbar: "#toolbar",
	        onBeforeLoad : function(){
	        	try{
	        		$("#teacherList").combobox("getData")
	        	}catch(err){
	        		preLoadClazz();
	        	}
	        }
	    });
		//提前加载教师信息
	    function preLoadClazz(){
	  		$("#teacherList").combobox({
		  		width: "150",
		  		height: "25",
		  		valueField: "id",
		  		textField: "name",
		  		multiple: false, //可多选
		  		editable: false, //不可编辑
		  		method: "post",
		  		url: "TeacherServlet?method=TeacherList&t="+new Date().getTime()+"&from=combox",
		  		onChange: function(newValue, oldValue){
		  			//加载班级下的学生
		  			//$('#dataList').datagrid("options").queryParams = {clazzid: newValue};
		  			//$('#dataList').datagrid("reload");
		  		}
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

	  //编辑课程信息
	  	$("#editDialog").dialog({
	  		title: "修改课程信息",
	    	width: 450,
	    	height: 400,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'提交',
					plain: true,
					iconCls:'icon-user_add',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							var teacherid = $("#edit_teacherList").combobox("getValue");
							var id = $("#dataList").datagrid("getSelected").id;
							var name = $("#edit_name").textbox("getText");
							var courseDate = $("#edit_course_date").textbox("getText");
							var maxNum = $("#edit_max_num").numberbox("getValue");
							var info = $("#edit_info").val();
							var data = {id:id, teacherid:teacherid, name:name,courseDate:courseDate,info:info,maxnum:maxNum};

							$.ajax({
								type: "post",
								url: "CourseServlet?method=EditCourse",
								data: data,
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","修改成功!","info");
										//关闭窗口
										$("#editDialog").dialog("close");
										//清空原表格数据
										$("#edit_name").textbox('setValue', "");
										$("#edit_course_date").textbox('setValue', "");
										$("#edit_info").val("");

										//重新刷新页面数据
							  			$('#dataList').datagrid("reload");
							  			$('#dataList').datagrid("uncheckAll");

									} else{
										$.messager.alert("消息提醒","修改失败!","warning");
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
					iconCls:'icon-reload',
					handler:function(){
						$("#edit_name").textbox('setValue', "");
						$("#edit_phone").textbox('setValue', "");
						$("#edit_qq").textbox('setValue', "");

						$(table).find(".chooseTr").remove();

					}
				},
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#edit_name").textbox('setValue', selectRow.name);
				$("#edit_course_date").textbox('setValue', selectRow.courseDate);
				$("#edit_max_num").numberbox('setValue', selectRow.maxNum);
				$("#edit_info").val(selectRow.info);
				//$("#edit-id").val(selectRow.id);
				var teacherId = selectRow.teacherId;
				setTimeout(function(){
					$("#edit_teacherList").combobox('setValue', teacherId);
				}, 100);
			},
			onClose: function(){
				$("#edit_name").textbox('setValue', "");
				$("#edit_course_date").textbox('setValue', "");
				$("#edit_info").val("");
				//$("#edit-id").val('');
			}
	    });

	    //删除
	    $("#delete").click(function(){
	    	var selectRow = $("#dataList").datagrid("getSelections");
        	if(selectRow == null){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	var ids = [];
            	$(selectRow).each(function(i, row){
            		ids[i] = row.id;
            	});
            	$.messager.confirm("消息提醒", "将删除与课程相关的所有数据，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "CourseServlet?method=DeleteCourse",
							data: {ids: ids},
							success: function(msg){
								if(msg == "success"){
									$.messager.alert("消息提醒","删除成功!","info");
									//刷新表格
									$("#dataList").datagrid("reload");
								} else{
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
	    	title: "添加课程",
	    	width: 450,
	    	height: 400,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'添加',
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
								url: "CourseServlet?method=AddCourse",
								data: $("#addForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","添加成功!","info");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#add_name").textbox('setValue', "");
										//刷新
										$('#dataList').datagrid("reload");
									} else{
										$.messager.alert("消息提醒","添加失败!","warning");
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
	  	$("#add_teacherList, #edit_teacherList,#teacherList").combobox({
	  		width: "200",
	  		height: "30",
	  		valueField: "id",
	  		textField: "name",
	  		multiple: false, //不可多选
	  		editable: false, //不可编辑
	  		method: "post",
	  	});
	  	//添加信息教师选择框
	    $("#add_teacherList").combobox({
	  		url: "TeacherServlet?method=TeacherList&t="+new Date().getTime()+"&from=combox",
	  		onLoadSuccess: function(){
				//默认选择第一条数据
				var data = $(this).combobox("getData");
				$(this).combobox("setValue", data[0].id);
	  		}
	  	});
	  //编辑信息教师选择框
	    $("#edit_teacherList").combobox({
	  		url: "TeacherServlet?method=TeacherList&t="+new Date().getTime()+"&from=combox",
	  		onLoadSuccess: function(){
				//默认选择第一条数据
				var data = $(this).combobox("getData");
				$(this).combobox("setValue", data[0].id);
	  		}
	  	});


	    //搜索按钮监听事件
	  	$("#search-btn").click(function(){

	  		var course_id = $("#courseName").val();
	  		sessionStorage.setItem("course_id",course_id);
	  		if(course_id<1){
				$.messager.alert("消息提醒","无效的课程号!","info");
				return;
			}

			$.ajax({
				type: "post",
				url: "CourseAttendanceServlet?method=CourseAttendance",
				data: {course_id:course_id},
				success: function(msg){

					var list_obj=JSON.parse(msg);
					//

                    //重新刷新页面数据

                    window.location.href="/CourseAttendanceServlet?method=toCourseServletListView"
					console.log(list_obj);
					var arrnum_ = 0;
					for (let i = 0; i < list_obj.length; i++) {
						if(list_obj[i]['type']=="已签到"||list_obj[i]['type']=="迟到"){
							arrnum_++;
						}
					}


					var arrnum=$("[field='arrnum']");

					for (let i = 1; i < arrnum.length; i++) {

						$(arrnum[i]).html('<div style="height:auto;" class="datagrid-cell datagrid-cell-c1-allnum">'+arrnum_+'</div>');
					}


					var allnum=$("[field='allnum']");

					for (let i = 1; i < allnum.length; i++) {

						$(allnum[i]).html('<div style="height:auto;" class="datagrid-cell datagrid-cell-c1-allnum">'+list_obj.length+'</div>');
					}



					// var courseName=$("[field='courseName']");
                    //
					// for (let i = 1; i < courseName.length; i++) {
                    //
					// 	$(courseName[i]).html('<div style="height:auto;" class="datagrid-cell datagrid-cell-c1-allnum">'+list_obj[i-1]['courseName']+'</div>');
					// }


				}
			});


	  	});

	  	////


		$($('#dataList').datagrid('getPager')[0].children[1]).css("font-size","20px");
		$($('#dataList').datagrid('getPager')[0].children[0]).attr("cellpadding",4);
		$(".pagination-page-list").css("font-size","16px");



	});
	</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0">



	</table>
	<!-- 工具栏 -->
	<div id="toolbar">
<%--		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>--%>
<%--		<div style="float: left;" class="datagrid-btn-separator"></div>--%>
<%--		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>--%>
<%--		<div style="float: left; margin-right: 10px;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>--%>
<%--		<div style="float: left;" class="datagrid-btn-separator"></div>--%>
<%--		<div style="margin-top: 3px;">--%>
<%--			课程名称：<input id="courseName" class="easyui-textbox" name="clazzName" />--%>
<%--			授课老师：<input id="teacherList" class="easyui-textbox" name="clazz" />--%>
<%--			<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>--%>

	<div style="display: flex;width: 65%">


		<div style="width: 40%" class="input-group input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="inputGroup-sizing-sm">请输入课程ID：</span>
			</div>
			<input id="courseName"   class="form-control" type="number"
				   name="clazzName" />

		</div>


		<a id="search-btn" style="margin-left: 20px" href="javascript:;" class="btn btn-primary" data-options="iconCls:'icon-search',plain:true">
			<i class="bi bi-search"></i>

			查询</a>


		<a id="save" style="margin-left: 20px" href="javascript:;" class="btn btn-success" data-options="iconCls:'icon-search',plain:true">
			<i class="bi bi-pencil-square"></i>

			保存</a>

	</div>




		</div>
	</div>

	<!-- 添加数据窗口 -->
	<div id="addDialog" style="padding: 10px">
    	<form id="addForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>课程名称:</td>
	    			<td><input id="add_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name" data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td style="width:40px">授课老师:</td>
	    			<td colspan="3">
	    				<input id="add_teacherList" style="width: 200px; height: 30px;" class="easyui-textbox" name="teacherid" />
	    			</td>
	    			<td style="width:80px"></td>
	    		</tr>
	    		<tr>
	    			<td>上课时间:</td>
	    			<td><input id="add_course_date" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="course_date" data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>最多可选人数:</td>
	    			<td><input id="add_max_num" style="width: 200px; height: 30px;" class="easyui-numberbox" type="text" name="maxnum" data-options="min:0,precision:0,required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>课程介绍:</td>
	    			<td>
	    				<textarea id="info" name="info" style="width: 200px; height: 60px;" class="" ></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>

	<!-- 编辑数据窗口 -->
	<div id="editDialog" style="padding: 10px">
    	<form id="editForm" method="post">
    		<!-- <input type="hidden" name="id" id="edit-id"> -->
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>课程名称:</td>
	    			<td><input id="edit_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name" data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td style="width:40px">授课老师:</td>
	    			<td colspan="3">
	    				<input id="edit_teacherList" style="width: 200px; height: 30px;" class="easyui-textbox" name="teacherid" />
	    			</td>
	    			<td style="width:80px"></td>
	    		</tr>
	    		<tr>
	    			<td>上课时间:</td>
	    			<td><input id="edit_course_date" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="course_date" data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>最多可选人数:</td>
	    			<td><input id="edit_max_num" style="width: 200px; height: 30px;" class="easyui-numberbox" type="text" name="max_num" data-options="min:0,precision:0,required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>课程介绍:</td>
	    			<td>
	    				<textarea id="edit_info" name="info" style="width: 200px; height: 60px;" class="" ></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    </form>

	</div>


<script>

	$("#save").click(function(){

		var course_id = sessionStorage.getItem("course_id");
		if(course_id!=null||course_id!=undefined){
			$.ajax({
				type: "get",
				url: "/CourseAttendanceServlet?method=courseAttendanceHistory",
				data: {course_id:course_id},
				success: function(msg){

					//

				}
			});
		}else {
			$.messager.alert("消息提醒", "请先查询数据!", "warning");
		}


	});

</script>

</body>
</html>