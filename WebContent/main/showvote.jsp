<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
<title>Insert title here</title>
<style>
</style>
</head>
<body>
	<div style="width:500px;height: 55px">
		<img style="display: block;float:left;margin-top: 10px;" src="images/title_ico.gif" style="margin-top:5px;">
		<div style="float: left;padding-left:10px;line-height:55px;">参与投票</div>
	</div>
	<div style="width:600px;border:px solid;">
		<div style="width:300px;height:35px;">
			<img style="float: left;margin-top:6px;" src="images/vote_icon.gif">
			<h3 style="float:left;margin:0px 4px"> ${voteInfor.voteName }</h3><br/>
		</div>
		<div style="padding-left:20px;font-size:12px;color:#999999;margin-bottom:10px;">共有&nbsp;${fn:length(details)}&nbsp;个选项</div>
		<table style="margin-left:15px;font-size:12px;color:#999999">
		<%int a=1; %>
			<c:forEach items="${details}" var="d" begin="">
				<tr>
					<td style="height:40px;width:50px;"><%=a %>.</td>
					<td style="width:600px;"><div style="width:400px;height:20px;background-color:#ECEEED;float:left;">
							<div style="width:${(d.optNumber*100)/totalNumber*4}px;height:20px;background-color:yellow"></div>
						</div>
						<div style="float: left;margin-left: 10px;">${d.optNumber}票&nbsp;&nbsp;&nbsp;(<fmt:formatNumber value="${(d.optNumber*100)/totalNumber}" pattern="#.##" />%)</div>
					</td>
				</tr>
				<%a++; %>
			</c:forEach>
		</table>
		<div style="margin-top:30px;height:30px;">
			<img style="float: left;" src="images/goback.gif"><a style="margin-left:10px;color:#74AFD3;font-size:13px;height:30px;line-height:30px;float: left;display: block;" href="main/returnlist.jsp">返回投票列表</a>
		</div>
	</div>
</body>
</html>