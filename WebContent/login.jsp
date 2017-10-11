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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
<title>登陆</title>
<link rel='stylesheet' type='text/css' href='css/style.css'>
<script type="text/javascript" src="js/login.js"></script>
<style>
table tr td input {
	width:130px;
}
</style>
</head>
<body>
	<div class="div_main_login">
		<div style="width: 700px;">
			<img src="images/logo.gif">
		</div>
		<table class="table1_login">
			<tr>
				<td>
					<img src="images/voteBanner.jpg">
				</td>
				<td style="width:300px"></td>
			</tr>
		</table>
		<div class="blankdiv_login">
			<form id="form_login" action="UserServlet?flag=login" method="post">
				<div class="text1_login">用户登录</div>
				<table class="table_login">
					<tr>
						<td>用户名:</td>
						<td><input type="text" id="userName" name="userName"></td>
					</tr>
					<tr>
						<td>密&nbsp;&nbsp;码:</td>
						<td><input type="password" id="userPassword" name="userPassword"></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
							<input class="submit_login" type="submit">
							<a href="main/register.jsp" class="toregister_login">新用户注册</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="float: left;margin-top: 10px;">
			<img src="images/bannerCorner.jpg">
		</div>
	</div>
</body>
</html>