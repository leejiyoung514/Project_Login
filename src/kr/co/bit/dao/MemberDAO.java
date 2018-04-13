package kr.co.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.vo.MemberVO;

public class MemberDAO {
	
	
	
	//회원정보 등록
	public boolean insert(MemberVO vo) {
		boolean flag=false;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		String sql = "insert into join_list values(?, ?, ?, ?)";
		System.out.println("0k");
		PreparedStatement pstmt = null;
		try {
			System.out.println("in");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getNickname());
			pstmt.setString(4, vo.getPw());

			int affectedCount = pstmt.executeUpdate();
			if (affectedCount > 0) {
				flag=true;
				System.out.println("JOIN_LIST 테이블에 새로운 레코드를 추가했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mgr.connectionClose(con, pstmt, null);
		}
		return flag;
	}
	//로그인 체크 (인증여부 판단)
	public int userCheck(String id, String pw) {
		ConnectionManager mgr=new ConnectionManager();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;	
		int x=-1;
		String sql="SELECT pw FROM join_list where id=?";
		try {
			con=mgr.getConnection();
			String u_pw= pw;
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) { //해당 아이디가 있으면 수행
				String dbPW=rs.getString("pw");
				if(u_pw.equals(dbPW)) {
					x=1;//인증성공
				}else { 
					x=-1;//비밀번호 틀림
				}		
			} else {
				x = 0; // 아이디 없음
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			mgr.connectionClose(con, pstmt, null);
		}
		return x;
	}
}
