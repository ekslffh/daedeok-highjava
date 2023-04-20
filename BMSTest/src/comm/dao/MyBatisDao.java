package comm.dao;

import java.util.Collections;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import comm.util.MyBatisUtil;

public class MyBatisDao<T> {
	
	public List<T> selectList(String statement) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		List<T> list = Collections.EMPTY_LIST;
		
		try {
			list = session.selectList(statement);
		} catch (PersistenceException ex) {
			throw new RuntimeException("마이바티스 전체목록 조회중 예외발생!");
		} finally {
			session.close();
		}
		
		return list;
	}
	
	public T selectOne(String statement) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		T obj = null;
		
		try {
			obj = session.selectOne(statement);
		} catch (PersistenceException ex) {
			throw new RuntimeException("마이바티스 목록 조회중 예외발생!");
		} finally {
			session.close();
		}
		
		return obj;
	}
	
}
