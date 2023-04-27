package member.vo;

public class MemberVO {
	String memId;	// 고유번호
	String memName; // 이름
	String memMail; // 이메일주소
	String memHp;	// 전화번호
	String memAdd1;	// 기본주소
	String memAdd2;	// 상세주소
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public String getMemHp() {
		return memHp;
	}
	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}
	public String getMemAdd1() {
		return memAdd1;
	}
	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}
	public String getMemAdd2() {
		return memAdd2;
	}
	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}
	
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + ", memMail=" + memMail + ", memHp=" + memHp
				+ ", memAdd1=" + memAdd1 + ", memAdd2=" + memAdd2 + "]";
	}
}
