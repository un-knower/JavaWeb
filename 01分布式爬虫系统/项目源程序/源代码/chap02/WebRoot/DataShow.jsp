<%@page import="model.DataItems"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>显示所有学生信息</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="plugins/layui/css/begtable.css" />
<script type="text/javascript" src="plugins/layui/layui.js"></script>
</head>
<% List<DataItems> reslut=(List<DataItems>)session.getAttribute("SearchReslut"); 
	String error=(String)session.getAttribute("searcherror");
int size=reslut.size();
%>
<input type="hidden" value=<%=size %> id="size">
<input type="hidden" value=<%=error %> id="error">
<body>
	<fieldset class="layui-elem-field" style="margin: 25px;">
		<legend>爬取结果列表</legend>
		<div class="layui-field-box" style="margin: 15px;">
			<div class="layui-btn-group">
				<a class="layui-btn layui-btn-small" id="add2"> <i
					class="layui-icon">&#xe62d;</i> 导入到Excel</a>
			</div>
			<div class="beg-table-box" style="margin: 10px;">
				<div class="beg-table-body">
					<table class="layui-table">
						<thead>
							<tr>
								<th>选择</th>
								<th>编号</th>
								<th>标题</th>
								<th>发布时间</th>
								<th>链接</th>
								<th>摘要</th>
								<th>关键字</th>
							</tr>
							<tr>
							<%
							DataItems dataItems=new DataItems();
							for(int i=0;i<reslut.size();i++)
							{
							    String url=dataItems.getUrl();
							    if(url.length()>=30)
							    {
							  	  url=url.substring(0,29)+"... ...";
							    }
							 	dataItems=reslut.get(i);
								out.println("<td><input type='checkbox' name='check'></td>");
								out.println("<td>"+(i+1)+"</td>");
								out.println("<td><a style='color:blue;' href='javascript:;'>"+dataItems.getTitle()+"</a></td>");
								out.println("<td>"+dataItems.getTime()+"</td>");
								out.println("<td><a style='color:green;' href="+dataItems.getUrl()+" target='_blank'>"+url+"</a></td>");
								out.println("<td>"+dataItems.getZhaiyao()+"</td>");
								out.println("<td>"+dataItems.getKey()+"</td>");
								out.println("<tr></tr>");
							}
							 %>
							 </tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</fieldset>
         
	<script type="text/javascript">
  layui.use('layer', function()
      {
              var $ = layui.jquery,
              form=layui.form,
              layer = layui.layer;
              var size=document.getElementById("size").value;
              var error=document.getElementById("error").value;
              if(size==0||error=="暂无数据")
              {
              	layer.msg("暂时没有数据");
              }
              else
              {
              	layer.msg("目前爬取到"+size+"条数据");
              }
        
           
              window.Xiangxi=function(obj,url)
              {
              	layer.alert(url);
              	return false;
              };
           
	 });
 </script>
</body>
</html>
