<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/register.js"></script>
<link rel='stylesheet' type='text/css' href='css/style.css'>
<title>注册</title>
<style type="text/css">
table tr td{
padding-left: 15px;	

}
</style>
</head>
<body>
	<div class="div_main_register">
		<div style="width: 700px;">
			<img src="images/logo.gif">
		</div>
		<div class="blankdiv1_register"></div>
		<div class="text1_register">新用户注册</div>
		
		<form id="form_register" action="UserServlet?flag=register" method="post">
			<table class="table_register">
				<tr>
					<td> </td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: right;">用户ID:</td>
					<td><input id="userId" type="text" name="userId"></td>
				</tr>
				<tr>
					<td style="text-align: right;">用户名:</td>
					<td><input id="userName" type="text" name="userName"></td>
				</tr>
				<tr>
					<td style="text-align: right;">密码:</td>
					<td><input id="userPassword" type="password" name="userPassword"></td>
				</tr>
				<tr>
					<td style="text-align: right;">确认密码:</td>
					<td><input id="userPassword2" type="password"></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="submit_register" type="submit" value=" ">
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>