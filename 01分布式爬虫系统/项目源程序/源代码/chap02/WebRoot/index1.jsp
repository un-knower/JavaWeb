<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>爬虫入口</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
<script type="text/javascript" src="plugins/layui/layui.js"></script>
<script type="text/javascript" src="plugins/layui/lay/lib/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>

<body background="image/71017-106.jpg">
	<div style="margin: 15px;">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 30px;">
			<legend><font style="color: white;">爬虫入口</font></legend>
		</fieldset>

		<form class="layui-form layui-form-pane" action="runnews?i=1&j=1" method="post">
			<div align="center">
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn layui-btn-warm" id="WatchData">查看数据</button>
						<a class="layui-btn layui-btn" href="index.html">返回首页</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<br>
</body>
</html>
