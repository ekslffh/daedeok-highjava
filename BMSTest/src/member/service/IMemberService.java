package member.service;

import java.util.List;
import member.vo.MemberVO;

public interface IMemberService {
	public List<MemberVO> findAll();
	public MemberVO findById(String memId);
}
