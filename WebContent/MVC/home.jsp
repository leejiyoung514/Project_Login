<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<link rel="stylesheet" href="./buttons.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
body {
	position: relative;
	background: #f5f6f7;
	width: 100%;
	height: 100%;
	display: block;
	font-family: 돋움, Dotum, Helvetica, sans-serif;
	text-align: center;
}

.button {
	font-size: 10px;
	width: 50%;
	padding: 10px 24px;
}

#id, #pw, #login {
	margin-top: 5px;
}

#id, #pw {
	padding: 3px 3px 3px 3px;
}

.member {
	font-family: 돋움, Dotum, Helvetica, sans-serif;
	color: blue;
}

#logout{
    display:block;
    text-align: center;
}
</style>
</head>
<body>

	<%
		String id = "";
	    id=(String)session.getAttribute("session_login_id"); 
	    
	    
	     out.print(id);
	    if(id==null|| id.equals("")){

	%>
      <!-- 인증안된 사용자 -->
 	<form action="./pageController?cmd=session_login" method="post">

		<div>
			<input type="text" name="id" id="id" placeholder="아이디" size="30">
		</div>
		<div>
			<input type="password" name="pw" id="pw" placeholder="비밀번호" size="30">
		</div>
		<div>
			<!-- <button type="button" id="button" class="btn btn-default btn-sm">로그인</button> -->
			<input type="submit" class="btn btn-default btn-sm" value="로그인"
				id="login">
		</div>
	</form>
	<b>계정이 있으신가요?</b>
	<a href="./pageController?cmd=joinVeiw" class="btn btn-link">회원가입</a>
	<br>
	
	
	<!--인증된 사용자  --> 
	<%}else{%> 
	<form action="./pageController?cmd=session_logout" method="post">
		 <div>
            <b><%=id%></b>님이 로그인 하셨습니다.
			<input type="submit" class="btn btn-default btn-sm" value="로그아웃" id="logout">
	        <br>
    	</div>    
	</form>
    <%}%>

</body>
</html>




<!-- 	<div>
		<span><a href="./pageController?cmd=find_id_pw">ID/PW찾기</a></span>
		<span><a href="./pageController?cmd=member_info">회원정보</a></span>
	</div> -->