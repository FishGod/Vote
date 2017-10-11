<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>投票</title>
<style type="text/css">
.div_main_login{
border-bottom:1.5px solid #96B7D8;
width: 800px;
height: 620px;
margin: 0px auto;
}
ul{
	margin: 0;
}
ul li{
list-style:none;
float: left;
width:120px;
text-align: center;
}
ul li a{
padding-left:5px;
color:#8194B8;
font-size:12px;
text-decoration:none;
}
</style>
</head>
<%Cookie cookie[]=request.getCookies();
String un="";
  if(cookie!=null){
	  for(Cookie ck:cookie){
		  if(ck.getName().equals("username")){
		  		un=ck.getValue();
		  }
	  }
  }%>
<body>
	<div class="div_main_login">
		<div style="width: 700px;">
			<img src="images/logo.gif">
		</div>
		<div style="width: 800px;height: 5px;background-color:#7CA2C1"></div>
		<div style="width: 800px;height:40px;background-color:#D9E9F1;line-height:40px;">
			<div style="float:left;height:40px;width:120px;line-height:40px;font-size:12px;padding-left: 10px;">你好,${ sessionScope.username }</div>
		  	<ul>
		  		<c:choose>
		  		<c:when test="${ sessionScope.power=='0' }">
					<li><img src="images/vote_icon.gif"><a href="main/returnlist.jsp" target="myframe">返回列表</a></li>
					<li><img src="images/addnew.gif"><a href="main/addnew.jsp" target="myframe">添加新投票</a></li>
					<li><img src="images/edit.gif"><a href="main/maintenance.jsp" target="myframe">维护</a></li>
				</c:when>
				<c:otherwise>
					<li><img src="images/vote_icon.gif"><a href="main/returnlist.jsp" target="myframe">返回列表</a></li>
				</c:otherwise>
				</c:choose>
			</ul>
			<div style="float:right;margin-right:10px;">
				<form action="VoteServlet?flag=votesearch" method="post" target="myframe">
					<input type="text" name="search">
					<input type="submit" value="搜索">
				</form>
			</div>
		</div>
		<div style="width:800px;height:600px;">
			<iframe name="myframe" src="main/returnlist.jsp" style="width:100%;height:100%;border:0;margin-left:0px;"></iframe>
		</div>
	</div>
</body>
</html>