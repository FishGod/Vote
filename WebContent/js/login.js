window.onload=function(){
	document.getElementById("form_login").onsubmit=function(){
		var un=document.getElementById("userName").value;
		var pw=document.getElementById("userPassword").value;
		if(un!=null && un!=""){
			if(pw==null||pw==""){
				alert("密码不能为空!");
				return false;
			}else{
				return true;
			}
		}else{
			alert("用户名不能为空!");
			return false;
		}
	}
}