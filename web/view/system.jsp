<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>选课系统</title>
    <script>
        function Login_out() {

            window.location.href = "LoginServlet?method=logout";
        }
    </script>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="bookmark" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="easyui/css/default.css"/>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap-icons.css"/>

    <style type="text/css">

        * {
            font-size: 15px;
            font-family: "fonts/bootstrap-icons.woff2";
        }

        .panel-header {
            height: 35px !important;
        }

        .panel-title {
            font-size: 18px !important;
        }

        .tabs-title {
            font-size: 17px !important;
        }

        .tabs {
            height: 35px !important;
        }

        .tabs li.tabs-selected a.tabs-inner {
            height: 35px !important;
            width: 140px !important;
            line-height: 35px !important;
        }
    </style>
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src='easyui/js/outlook2.js'></script>
    <script type="text/javascript" src='js/bootstrap.js'></script>

    <script type="text/javascript">
        var _menus = {
            "menus": [

                {
                    "menuid": "2", "icon": "", "menuname": "学生信息管理",
                    "menus": [
                        {
                            "menuid": "21",
                            "menuname": "学生列表",
                            "icon": "icon-user-student",
                            "url": "StudentServlet?method=toStudentListView"
                        },
                    ]
                },
                <c:if test="${userType == 1 || userType == 3}">
                {
                    "menuid": "4", "icon": "", "menuname": "班级信息管理",
                    "menus": [
                        {
                            "menuid": "42",
                            "menuname": "班级列表",
                            "icon": "icon-house",
                            "url": "ClazzServlet?method=toClazzListView"
                        }
                    ]
                },
                </c:if>
                <c:if test="${userType == 1 || userType == 3}">
                {
                    "menuid": "3", "icon": "", "menuname": "教师信息管理",
                    "menus": [
                        {
                            "menuid": "31",
                            "menuname": "教师列表",
                            "icon": "icon-user-teacher",
                            "url": "TeacherServlet?method=toTeacherListView"
                        },
                    ]
                },
                </c:if>
                <c:if test="${userType == 1 || userType == 3}">
                // {"menuid":"6","icon":"","menuname":"课程信息管理",
                // 	"menus":[
                // 			{"menuid":"61","menuname":"课程列表","icon":"icon-book-open","url":"CourseServlet?method=toCourseListView"},
                // 		]
                // },
                </c:if>
                // {"menuid":"7","icon":"","menuname":"选课信息管理",
                // 	"menus":[
                // 			{"menuid":"71","menuname":"选课列表","icon":"icon-book-open","url":"SelectedCourseServlet?method=toSelectedCourseListView"},
                // 		]
                // },
                {
                    "menuid": "8", "icon": "", "menuname": "请假逾期管理",
                    "menus": [
                        {
                            "menuid": "81",
                            "menuname": "逾期列表",
                            "icon": "icon-book-open",
                            "url": "AttendanceServlet?method=toAttendanceServletListView"
                        },
                    ]
                },
                {
                    "menuid": "9", "icon": "", "menuname": "请假信息管理",
                    "menus": [
                        {
                            "menuid": "91",
                            "menuname": "请假列表",
                            "icon": "icon-book-open",
                            "url": "LeaveServlet?method=toLeaveServletListView"
                        },
                    ]
                },
                {
                    "menuid": "5", "icon": "", "menuname": "修改密码",
                    "menus": [
                        {
                            "menuid": "51",
                            "menuname": "修改密码",
                            "icon": "icon-set",
                            "url": "SystemServlet?method=toPersonalView"
                        },
                    ]
                }
            ]
        };


    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<noscript>
    <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
        <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！'/>
    </div>
</noscript>


<div region="north" split="true" border="false" style="overflow: hidden; height: 45px;
        background:  rgb(63 129 223) repeat-x center 50%;
        padding-top: 6px;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
    <span style="float:right; padding-right:20px;" class="head"><span
            style="color:red; font-weight:bold; font-size: 15px">${user.name}&nbsp;&nbsp;<span
            style="font-size: 15px;color: white">您好</span></span>&nbsp;&nbsp;<button onclick="Login_out()" type="button" class="btn btn-danger">退出</button></span>

    <span style="padding-left:25px; font-size: 30px; margin-top: 15px">
        <i class="bi bi-cloud-fill" style="font-size: 29px"></i>
        <i class="bi bi-lightbulb" style="font-size: 29px"></i>
        选课系统</span>
</div>
<div region="south" split="true" style="height: 30px; background: rgb(63 129 223); ">
    <div class="footer" style="color: white">南京邮电大学通达学院</div>
</div>
<div region="west" hide="true" split="true" title="导航菜单" style="width:220px;height: 35px;" id="west">
    <%--	<div id="nav" class="easyui-accordion" fit="true" border="false">--%>
    <%--		<!--  导航内容 -->--%>
    <%--	</div>--%>
    <div id="left_side" class="list-group">

        <button type="button" class="list-group-item list-group-item-action">
            <i class="bi bi-people-fill"></i>

            <span class="h5">学生信息管理</span></button>
        <button type="button" class="list-group-item list-group-item-action">
            <i class="bi bi-person-square"></i>
            <span class="h5">班级信息管理</span></button>


        <button type="button" class="list-group-item list-group-item-action">
            <i class="bi bi-book-half"></i>
            <span class="h5">课程信息管理</span></button>
        <button type="button" class="list-group-item list-group-item-action">
            <i class="bi bi-pencil"></i>
            <span  class="h5">选课信息管理</span>
        </button>
        <button style="display: none" type="button" class="list-group-item list-group-item-action">
            <i class="bi bi-card-checklist"></i>
            <span  class="h5">x</span>
        </button>
        <button type="button" class="list-group-item list-group-item-action">
            <i class="bi bi-person-badge-fill"></i>
            <span  class="h5">教师信息管理</span>
        </button>

<%--        <button type="button" class="list-group-item list-group-item-action">--%>
<%--            <i class="bi bi-card-list"></i>--%>
<%--            <span  class="h5">请假逾期管理</span>--%>
<%--        </button>--%>

<%--        <button type="button" class="list-group-item list-group-item-action">--%>
<%--            <i class="bi bi-card-heading"></i>--%>
<%--            <span class="h5">请假信息管理</span></button>--%>
<%--        <button type="button" class="list-group-item list-group-item-action">--%>
<%--            <i class="bi bi-bar-chart-line-fill"></i>--%>
<%--            <span class="h5">数据统计</span></button>--%>
<%--        <button type="button" class="list-group-item list-group-item-action">--%>
<%--            <i class="bi bi-key-fill"></i>--%>
<%--            <span class="h5">修改密码</span></button>--%>

    </div>
</div>
<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <jsp:include page="welcome.jsp"/>
    </div>
</div>

<iframe width=0 height=0 src="refresh.jsp"></iframe>


<script>


    var buttons = $("#left_side button");


    for (let i = 0; i < buttons.length; i++) {
        let $jq_obj = $(buttons[i]);
        $jq_obj.click(function () {
            for (let i = 0; i < buttons.length; i++) {
                let $jq_obj = $(buttons[i]);
                $jq_obj.attr("class", "list-group-item list-group-item-action");
            }

            $jq_obj.attr("class", "list-group-item list-group-item-action active");
            // alert(i);

            switch (i) {
                case 0:
                    addTab($($jq_obj[0].children[1]).html(),"StudentServlet?method=toStudentListView","");
                    break;
                case 1:
                    addTab($($jq_obj[0].children[1]).html(),"ClazzServlet?method=toClazzListView","");
                    break;
                case 2:
                    addTab($($jq_obj[0].children[1]).html(),"CourseServlet?method=toCourseListView","");
                    break;
                case 3:
                    addTab($($jq_obj[0].children[1]).html(),"SelectedCourseServlet?method=toSelectedCourseListView","");
                    break;

                case 4:
                    addTab($($jq_obj[0].children[1]).html(),"CourseAttendanceServlet?method=toCourseServletListView","");
                    break;
                case 5:
                    addTab($($jq_obj[0].children[1]).html(),"TeacherServlet?method=toTeacherListView","");
                    break;
                case 6:
                    addTab($($jq_obj[0].children[1]).html(),"AttendanceServlet?method=toAttendanceServletListView","");
                    break;
                case 7:
                    addTab($($jq_obj[0].children[1]).html(),"LeaveServlet?method=toLeaveServletListView","");
                    break;
                case 8:
                    addTab($($jq_obj[0].children[1]).html(),"/002/index.html","");
                    break;
                case 9:
                    addTab($($jq_obj[0].children[1]).html(),"SystemServlet?method=toPersonalView","");
                    break;


            }
        })
    }

</script>
</body>

</html>