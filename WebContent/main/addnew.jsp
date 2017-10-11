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
	<div class="div_main_addnew">
			<div class="text1_addnew">添加新投票</div>
			<form action="VoteServlet?flag=addnew" method="post" onsubmit="return submitTest()">
				<table id="votetable" class="table_addnew">
					<tr>
						<td>投票内容:</td>
						<td><input id="vname" name="vname" type="text" style="width:300px"></td>
					</tr><tr>
						<td>投票类型:</td>
						<td><input type="radio" name="votetype" value="1"  checked="checked">单选<input type="radio" name="votetype" value="0">多选</td>
					</tr><tr>
						<td>投票选项</td>
						<td><input name='voteoption' type="text" style="width:300px"></td>
					</tr>
					<tr>
						<td></td>
						<td><input name='voteoption' type="text" style="width:300px"></td>
					</tr>
				</table>
				<div style="margin-left:118px;margin-bottom:20px;">
					<input class="submit_addnew" type="submit" value=" ">
					<a id="add" class="link_addnew">增加选项</a>
					<a href="main/returnlist.jsp" class="link_addnew">取消操作</a>
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
		alert("投票名不能为空!");
		return false;
	}else{
		for(var j=0;j<values.length;j++){
			if((values[j].value).length==0){
				alert("选项输入不能为空!");
				return false;
			}
		}
	}
}
</script>
</html>