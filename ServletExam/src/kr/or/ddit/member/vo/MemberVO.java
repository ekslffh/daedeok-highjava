package kr.or.ddit.member.vo;

/**
 * DB 테이블에 있는 컬럼명을 기준으로 데이터를 객체화 하기 위한 클래스
 * 
 * <p>
 * 	DB테이블의 컬럼명을 참조하여 클래스의 멤버변수 정의한다.
 * </p>
 * @author PC-20
 *
 */
public class MemberVO {
	
	private String memId;
	private String memName;
	private String memTel;
	private String memAddr;
	private long atchFileId = -1;
	
	public MemberVO() {}
	
	public MemberVO(String memId, String memName, String memTel, String memAddr) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.memTel = memTel;
		this.memAddr = memAddr;
	}
	
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
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}

	public long getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}

	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + ", memTel=" + memTel + ", memAddr=" + memAddr
				+ ", atchFileId=" + atchFileId + "]";
	}
}
