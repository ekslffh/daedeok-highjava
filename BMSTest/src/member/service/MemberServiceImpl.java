package member.service;

import java.util.List;
import member.dao.IMemberDao;
import member.dao.MemberDaoImpl;
import member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao memberDao;
	
	private static IMemberService memberService;
	
	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		if (memberService == null) {
			memberService = new MemberServiceImpl();
		}
		return memberService;
	}
	
	@Override
	public List<MemberVO> findAll() {
		return memberDao.getAll();
	}

	@Override
	public MemberVO findById(String memId) {
		return memberDao.getById(memId);
	}

}
