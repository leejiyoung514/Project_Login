package kr.co.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.vo.MemberVO;

public class MemberDAO {

	// 회원정보 등록
	public boolean insert(MemberVO vo) {
		boolean flag = false;
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
				flag = true;
				System.out.println("JOIN_LIST 테이블에 새로운 레코드를 추가했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mgr.connectionClose(con, pstmt, null);
		}
		return flag;
	}

	// 로그인 체크 (인증여부 판단)
	public int userCheck(String id, String pw) {
		ConnectionManager mgr = new ConnectionManager();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		String sql = "SELECT pw FROM join_list where id=?";
		try {
			con = mgr.getConnection();
			String u_pw = pw;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) { // 해당 아이디가 있으면 수행
				String dbPW = rs.getString("pw");
				if (u_pw.equals(dbPW)) {
					x = 1;// 인증성공
				} else {
					x = -1;// 비밀번호 틀림
				}
			} else {
				x = 0; // 아이디 없음
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectionClose(con, pstmt, null);
		}
		return x;
	}

	// 등록페이지에서 idCheck 체크 기능
	public int confirmId(String id) { // 폼의 파라미터값 사용자가 원하는 아이디
		int x = 0;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from join_list where id=?"; // id=? String id를 던져줄께
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);// where id=? ?가 1개 그자리가 1번이다
			rs = pstmt.executeQuery(); // id 값을 가지고 해당 레코드를 검색함
			if (rs.next())// 아이디존재하면
				x = 1; // 이미 같은 아이디 있음(사용불가)
			else // 아이디가 없다면
				x = 0; // 같은 아이디 없음(사용가능)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectionClose(con, pstmt, rs);
		}

		return x;
	}

	public int confirmNickName(String nickName) {
		int x = 0;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from join_list Where nickName=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			if (rs.next())
				x = 1; // 같은 닉네임 있음 (사용불가)
			else
				x = 0; // 닉네임 없음 (사용가능)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectionClose(con, pstmt, rs);
		}
		return x;
	}

}
