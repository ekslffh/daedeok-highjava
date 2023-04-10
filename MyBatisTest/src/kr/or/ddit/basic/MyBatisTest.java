package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MyBatisTest {
	public static void main(String[] args) {
		// myBatis를 이용하여 DB자료를 처리하는 작업 순서
		// 1. myBatis의 환경설정파일을 읽어와 실행시킨다.
		
		SqlSessionFactory sessionFactory = null;
		
		try {
			// 1-1. 설정파일 읽어오기
			Charset charset = Charset.forName("UTF-8"); // 한글처리를 위해서...
			Resources.setCharset(charset);
			
			Reader rd = Resources
					.getResourceAsReader("config/mybatis-config.xml");
			
			// 1-2. 위에서 생성한 Reader객체를 사용하여 sqlSessionFactory객체 생성하기
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			// 1-3. 다 사용한 Read객체 반납
			rd.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} 
		
		// 2. 실행할 SQL에 맞는 적절한 메서드를 호출하여 원하는 작업을 수행한다.
		
		// 2-1. insert 연습
		System.out.println("insert 작업 시작...");
		
		// 1) 저장할 데이터를 VO에 담는다.
		MemberVO mv = new MemberVO();
		mv.setMemId("d002");
		mv.setMemName("강감찬");
		mv.setMemTel("010-3344-5566");
		mv.setMemAddr("경남");
		
		// 2) SqlSession 객체를 이용하여 해당 쿼리문을 실행한다.
		// ex) 세션객체.insert("namespace값.id값", 파라미터객체)
		//		반환값: 성공한 레코드 수 
		SqlSession session = sessionFactory.openSession(false); // 오토커밋여부 설정(default: false)
		
		try {
			
			int cnt = session.insert("memberTest.insertMember", mv);
			
			if (cnt > 0) {
				System.out.println("insert 작업 성공!");
			} else {
				System.out.println("insert 작업 실패!!!");
			}
			
		} catch (PersistenceException ex) {
			System.out.println("마이바티스에서 예외발생!!!");
			ex.printStackTrace();
			
			session.rollback();
		} finally {
			session.close(); // 세션 닫기
		}
	}
}
