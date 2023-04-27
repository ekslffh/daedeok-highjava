package member.dao;

import java.util.List;
import member.vo.MemberVO;

public interface IMemberDao {
	public List<MemberVO> getAll();
	public MemberVO getById(String memId);
}
