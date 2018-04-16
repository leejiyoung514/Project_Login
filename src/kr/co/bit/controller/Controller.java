package kr.co.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.dao.MemberDAO;
import kr.co.bit.vo.MemberVO;

public class Controller extends HttpServlet {
     private ArrayList<MemberVO>list;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		 list=new ArrayList<MemberVO>();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=euc-kr");
		String url="./MVC/home.jsp";
		String cmd=request.getParameter("cmd");
		cmd=cmd==null?"":cmd;
		
		  //로그인페이지
		if(cmd.equals("session_login")) {  
			url="./MVC/home.jsp";
			MemberVO vo=null;
			MemberDAO dao=new MemberDAO();
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			int result=dao.userCheck(id, pw);
			HttpSession session =request.getSession();
			System.out.println(result);
            if(result==1) {
	         session.setAttribute("session_login_id", id);
            }else if(result==0) {
             request.setAttribute("check", "아이디가 없습니다.");
            }else if(result==-1) {
             request.setAttribute("check", "비밀번호가 맞지 않습니다.");
            }else {
             request.setAttribute("check", "데이터베이스 오류입니다.");	
            }
         //로그아웃페이지					
		}else if(cmd.equals("session_logout")){
			url="./MVC/logout.jsp";
		//회원가입페이지
	    }else if(cmd.equals("joinVeiw")) {
			url="./MVC/join.jsp";
		//회원등록페이지에서 출력해봄	
		}else if(cmd.equals("regist")) {
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String nickName=request.getParameter("nickName");
			String pw=request.getParameter("pw");
			MemberVO vo=new MemberVO();
			vo.setId(id);
			vo.setName(name);
            vo.setNickname(nickName);
            vo.setPw(pw);
            /*  list.add(vo);
            req.setAttribute("message", list);*/
            MemberDAO dao=new MemberDAO();
            boolean flag=dao.insert(vo);
            url="./MVC/list.jsp";
            request.setAttribute("result",flag?"success":"fail");
		//아이디비밀번호 찾는 페이지
	    }else if(cmd.equals("find_id_pw")) {
			url="./MVC/find_id_pw.jsp";
		//회원정보 수정 페이지 	
		}else if(cmd.equals("member_info")) {
			url="./MVC/member_info.jsp";
		}
		RequestDispatcher rd=request.getRequestDispatcher(url);
		rd.forward(request, response);	
	}
}
