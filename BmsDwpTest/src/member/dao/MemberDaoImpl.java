package member.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import member.vo.MemberVO;
import util.MyBatisUtil;

public class MemberDaoImpl implements IMemberDao {

	private static IMemberDao memberDao;
	
	private MemberDaoImpl(){}
	
	public static IMemberDao getInstance() {
		if (memberDao == null) {
			memberDao = new MemberDaoImpl();
		}
		return memberDao;
	}
	
	@Override
	public List<MemberVO> getAll() {
		SqlSession session = MyBatisUtil.getInstance();
		
		List<MemberVO> memList = new ArrayList();
		try {
			memList = session.selectList("member.getAll");
		} catch (PersistenceException ex) {
			throw new RuntimeException("멤버 전체조회중 예외발생 : " + ex.getMessage());
		} finally {
			session.close();
		}
		return memList;
	}

	@Override
	public MemberVO getById(String memId) {
		SqlSession session = MyBatisUtil.getInstance();
		
		MemberVO member = null;
		try {
			member = session.selectOne("member.getById", memId);
		} catch (PersistenceException ex) {
			throw new RuntimeException("특정멤버 조회중 예외발생 : " + ex.getMessage());
		} finally {
			session.close();
		}
		return member;
	}

}
