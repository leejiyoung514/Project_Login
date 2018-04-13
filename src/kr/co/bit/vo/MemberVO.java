package kr.co.bit.vo;

public class MemberVO {

	private String id;
	private String name;
	private String nickname;
	private String pw;
	
	public MemberVO() {
		
	}
	
	public MemberVO(String id, String name, String nickname, String pw) {
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.pw = pw;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

}
