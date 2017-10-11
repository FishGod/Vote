window.onload=function(){
	document.getElementById("form_register").onsubmit=function(){
		var uid=document.getElementById("userId").value;
		var un=document.getElementById("userName").value;
		var pw=document.getElementById("userPassword").value;
		var pw2=document.getElementById("userPassword2").value;
		if(uid!=null && uid!=""){
			if(un!=null && un!=""){
				if(pw==null||pw==""||pw2==null||pw2==""){
					alert("密码不能为空!");
					return false;
				}else{
					if(pw!=pw2){
						alert("密码输入不正确！");
						return false;
					}else{
						return true;
					}
				}
			}else{
				alert("用户名不能为空!");
				return false;
			}
		}else{
			alert("用户ID不能为空");
			return false;
		}
	}
}