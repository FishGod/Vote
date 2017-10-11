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
#votetable tr td a{
text-decoration: none;
margin-left:10px;
font-size:10px;
color:#76A2D8;
}
progress{
width:100px;
height:50px;
border: 1px solid red;
}
</style>
</head>
<body>
	<div style="margin:0px auto;border: 1px solid #79A4CE;width:600px;">
			<div style="padding-left:10px;line-height:40px;background-color: #79A4CE;color:#F0FDFF">添加新投票</div>
			<form action="VoteServlet?flag=maintenancesubmit&vid=${ requestScope.infor.voteId }" method="post" onsubmit="return  submitTest();">
				<table id="votetable" style="margin-left:40px;margin-top:10px;border-spacing:10px;font-size:13px;">
					<tr>
						<td>投票内容:</td>
						<td><input id="vname" name="vname" type="text" style="width:300px" value="${ requestScope.infor.voteName }"></td>
					</tr><tr>
						<td>投票类型:</td>
						<c:if test="${ requestScope.infor.voteType=='1' }">
						<td><input type="radio" name="votetype" value="1" checked="checked">单选<input type="radio" name="votetype" value="0">多选</td>
						</c:if>
						<c:if test="${ requestScope.infor.voteType=='0' }">
						<td><input type="radio" name="votetype" value="1">单选<input type="radio" name="votetype" value="0"  checked="checked">多选</td>
						</c:if>
					</tr>
					<c:forEach items="${details}" var="d">
					<tr>
						<td>投票选项</td>
						<td><input name='voteoption' type="text" style="width:300px" value="${ d.optName }"><a onclick='deltr(this)'>删除</a></td>
					</tr>
					</c:forEach>
				</table>
				<div style="margin-left:118px;margin-bottom:20px;">
					<input id="sub" style="background-image: url('images/button_submit.gif');border:0;width:75px;height:28px;" type="submit" value=" ">
					<a id="add" style="text-decoration:none;color:#76A2D8;font-size:12px;margin-left:10px;">增加选项</a>
					<a href="main/maintenance.jsp" style="text-decoration:none;color:#76A2D8;font-size:12px;margin-left:10px;">取消操作</a>
				</div>
			</form>
	</div>
</body>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$(window).load(function(){
	$("#add").click(function(){
		var newtr="<tr><td></td><td><input name='voteoption' type='text' style='width:300px'><a onclick='deltr(this)'>删除</a></td></tr>";
		$("#votetable").append(newtr);
	});
});
function deltr(tr){
	$(tr).parent().parent().remove();
}
function submitTest(){
	var a=0;
	var str1=document.getElementById("vname").value;
	var values=document.getElementsByName("voteoption");
	if(str1.length==0){
		a++;
	}
	for(var j=0;j<values.length;j++){
		if((values[j].value).length==0){
			a++;
		}
	}
	if(a>0){
		alert("输入不能为空!");
		return false;
	}
}
</script>
</html>