<%@page import="kr.co.bit.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
    request.setCharacterEncoding("UTF-8");
    MemberDAO dao=new MemberDAO();
    String nickName=request.getParameter("nickName");
    
    int result=dao.confirmNickName(nickName);
    if(result == 1){ //같은 닉네임 있음(사용불가능)
    	out.print("false");
    }else{//같은 닉네임 없음(사용가능)
    	out.print("true");
    }
%>