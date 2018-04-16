<%@page import="kr.co.bit.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>joinVeiw</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<style>
div {
	text-align: center;
}

span {
	position: relative;
	font-family: 돋움, Dotum, Helvetica, sans-serif;
	font-size: 15px;
	color: darkgray;
	position: static;
}

#id, #name, #nickName, #pw {
	margin-top: 5px;
	position: relative;
	padding: 3px 3px 3px 3px;
}

#id_check, #name_check, #nickName_check, #pw_check {
	display: block;
	text-align: center;
}

#join {
	margin-top: 5px;
	position: relative;
}
</style>
<script type="text/javascript">
	function confirmId_Ajax() {
		var id = document.getElementById("id").value;
		//alert(id);
		var server_page = "./MVC/id_check.jsp";
		//alert(server_page);
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var result = this.responseText;
				//alert(result);
				if (result == "false") {
					document.getElementById("id_check").innerHTML = "사용할 수 없는 아이디 입니다";
				} else if(result == "true"){
					idCheck(id);
				}
			   }else if (this.readyState == 4 && this.status != 200) {
				alert("error");
			     }	
		      }
		data = "id=" + id;
		xhr.open("POST", server_page, true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send(data);
    }

	function idCheck(val) {
		if (val == "") {
			document.getElementById("id_check").innerHTML = "아이디를 입력해주세요!";
		} else {
			document.getElementById("id_check").innerHTML = "";
		}
	}

	var nameCheck = function() {
		var name = document.getElementById("name").value;
		if (name == "") {
			document.getElementById("name_check").innerHTML = "이름을 입력해주세요!";
		} else {
			document.getElementById("name_check").innerHTML = "";
		}
	}
	
	function confirmNickName_Ajax(){
		var nickName = document.getElementById("nickName").value;
		var server_page="./MVC/nickname_check.jsp";
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(this.readyState == 4 && this.status == 200){
				var result = this.responseText;
				if(result == "false"){
					document.getElementById("nickName_check").innerHTML="사용할 수 없는 닉네임입니다.";
				}else if(result == "true"){
					nicknameCheck(nickName);
				}
			 }else if(this.readyState == 4 && this.status != 200){
				alert("error");
			 }
	    }
		data="nickName="+nickName;
		xhr.open("POST", server_page, true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send(data);	
	}	
	
	function nicknameCheck(nickName) {
		if (nickName == "") {
			document.getElementById("nickName_check").innerHTML = "닉네임을 입력해주세요!";
		} else {
			document.getElementById("nickName_check").innerHTML = "";
		}
	}

	function pwCheck() {
		var pw = document.getElementById("pw").value;
		if (pw == "") {
			document.getElementById("pw_check").innerHTML = "비밀번호를 입력해주세요!";
		} else {
			document.getElementById("pw_check").innerHTML = "";
		}
	}
	
</script>
</head>
<body>
	<%
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		list = (ArrayList<MemberVO>) request.getAttribute("message");
		String message = "";
		if (list != null) {
			message = list.get(0).getId();
		}
		/* if(list!=null){
			out.println(list.get(0).getId());
		}		 */
	%>
	<form action="./pageController?cmd=regist" method="post">
		<div>
			<input type="text" name="id" id="id" onblur="confirmId_Ajax()"
				placeholder="휴대번호 또는 이메일 주소" size="30"> <span id="id_check"></span>
		</div>
		<div>
			<input type="text" name="name" id="name" onblur="nameCheck()"
				placeholder="성명" size="30"> <span id="name_check"></span>
		</div>
		<div>
			<input type="text" name="nickName" id="nickName"
				onblur="confirmNickName_Ajax()" placeholder="닉네임" size="30"> <span
				id="nickName_check"></span>
		</div>
		<div>
			<input type="text" name="pw" id="pw" onblur="pwCheck()"
				placeholder="비밀번호" size="30"> <span id="pw_check"></span>
		</div>
		<div>
			<br> <input type="submit" value="가입" id="join"
				class="btn btn-default btn-sm" size="30">
		</div>
	</form>
</body>
</html>