<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel='stylesheet' type='text/css' href='css/style.css'>
<title>Insert title here</title>
</head>
<body>
	<div class="div1_joinvote">
		<img class="img1_joinvote" src="images/title_ico.gif" style="margin-top:5px;">
		<div class="text1_joinvote">参与投票</div>
	</div>
	<div class="div2_joinvote">
		<div class="div3_joinvote">
			<img style="float: left;margin-top:6px;" src="images/vote_icon.gif">
			<h3 style="float:left;margin:0px 4px"> ${ voteInfor.voteName }</h3><br/>
		</div>
		<div class="text2_joinvote">共有&nbsp;${fn:length(details)}&nbsp;个选项</div>
		<form id="frm" method="post">
			<table class="table_joinvote">
			<%int a=1; %>
			<c:choose>
				<c:when test="${ voteInfor.voteType=='0' }">
					<c:forEach items="${details}" var="d" begin="">
						<tr>
							<td style="height:40px;"><%=a %>.</td>
							<td><input type="checkbox" name="voteoption" value="${d.optName}">${d.optName}</td>
						</tr>
						<%a++; %>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach items="${details}" var="d" begin="">
						<tr>
							<td style="height:40px;"><%=a %>.</td>
							<td><input type="radio" name="voteoption" value="${d.optName}">${d.optName}</td>
						</tr>
						<%a++; %>
					</c:forEach>
				</c:otherwise>
			</c:choose>
				<tr>
					<td style="height:40px;width:60px;"><input type="submit" class="submit1_joinvote" value=" " onclick="sub1()"></td>
					<td><input type="submit" class="submit2_joinvote" value=" " onclick="sub2()"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
function sub1(){
	var obj=document.getElementsByName("voteoption");
	var j=0;
	for(var i=0;i<obj.length;i++){
		if(obj[i].checked){
			j++;
		}
	}
	if(j==0){
		alert("投票为空！");
		return false;
	}else{
		alert("投票成功！");
		frm.action="VoteServlet?flag=vote";
	}
}
function sub2(){
	frm.action="VoteServlet?flag=checkvote&vid=${voteId}&vname=${param.voteName }";
}
</script>
</html>