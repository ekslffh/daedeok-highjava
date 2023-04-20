package member.dao;

import java.util.List;
import member.vo.MemberVO;

public interface IMemberDao {
	public List<MemberVO> selectAll();
	public MemberVO selectOne();
}
