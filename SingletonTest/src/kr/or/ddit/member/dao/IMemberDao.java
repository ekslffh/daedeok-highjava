package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 받아와 Service에 전달하는 DAO 인터페이스
 * @author PC-20
 */
public interface IMemberDao {
	
	/**
	 * MemberVO에 담겨진 데이터를 DB에 insert하기 위한 메서드
	 * @param mv DB에 insert할 데이터를 담고있는 MemberVO객체
	 * @return DB 작업이 성공하면 1 이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int insertMember(MemberVO mv);
	
	/**
	 * MemberVO에 담겨진 데이터를 DB에 update하기 위한 메서드
	 * @param mv DB에 update할 데이터를 담고있는 MemberVO객체
	 * @return DB 작업이 성공하면 1 이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * 주어진 회원정보가 존재하는지 확인하기 위한 메서드
	 * @param memId 회원정보 존재여부 확인하기 위한 회원ID
	 * @return 해당 회원정보가 존재하면 1, 존재하지 않으면 0 리턴함.
	 */
	public boolean checkMember(String memId);
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원을 삭제하기 위한 메서드
	 * @param memId 삭제할 회원ID
	 * @return 삭제작업이 성공하면 1, 실패하면 0 리턴함.
	 */
	public int deleteMember(String memId);
	
	/**
	 * DB에 존재하는 모든 회원정보를 조회하기 위한 메서드
	 * @return 모든 회원정보를 담고 있는 리스트 객체
	 */
	public List<MemberVO> getAllMember();
	
}
