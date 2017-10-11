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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
<title>Insert title here</title>
<style>
a{
text-decoration:none;
color:#76B9D9;
font-weight:bold;
}
</style>
</head>
<body>
	<div style="width:500px;height: 55px">
		<img style="display: block;float:left;margin-top: 10px;" src="images/title_ico.gif" style="margin-top:5px;">
		<div style="float: left;padding-left:10px;line-height:55px;">投票列表</div>
	</div>
	<table id="table_vote" style="border-spacing:2px;">
	<c:forEach items="${infors}" var="v">
		<tr>
			<td style="width:800px;padding-bottom:8px;border-bottom: 1px solid #E9E9E9;">
					<img src="images/vote_icon.gif">
					<a href="VoteServlet?flag=checkvote&vid=${v.voteId}&voteName=${v.voteName}">${v.voteName}</a>
					<div style="font-size:10px;margin-top:5px;padding-left:20px;">别看了，快投票啊！
						<a style="float: right;font-weight: normal;" href="VoteServlet?flag=join&vid=${v.voteId}&voteName=${v.voteName}">我要参与</a>
						<img style="float: right;margin-right:5px;" src="images/join.gif">
					</div>
			</td>
		</tr>
	</c:forEach>
	</table>
</body>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$(window).load(function(){
	$("tr:even").css("background-color","#FFFFDB");
});
</script>
</html>