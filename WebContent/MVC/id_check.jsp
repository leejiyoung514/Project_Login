<%@page import="kr.co.bit.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	request.setCharacterEncoding("UTF-8");
	MemberDAO dao = new MemberDAO();
	//String message = "";
	String id = request.getParameter("id");
	int result = dao.confirmId(id);
	if (result == 1) { //이미 같은 아이디 있음(사용불가능)
		out.print("false");
	} else {//같은 아이디 없음(사용가능)
		out.print("true");
	}
%>