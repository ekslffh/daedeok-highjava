package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

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
		
		/* SqlSession session = sessionFactory.openSession(false); // 오토커밋여부 설정(default: false)
		
		try {
			
			int cnt = session.insert("memberTest.insertMember", mv);
			
			if (cnt > 0) {
				System.out.println("insert 작업 성공!");
				session.commit();
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
		System.out.println("-----------------------------");
		*/
		
		// 2-2. update작업 연습
		System.out.println("update작업 시작...");
		
		MemberVO mv2 = new MemberVO();
		mv2.setMemId("d002");
		mv2.setMemName("김은지");
		mv2.setMemTel("555-333");
		mv2.setMemAddr("대구시");
		
		SqlSession session = sessionFactory.openSession(); // default auto commit: false
		
		try {
			// update()메서드의 반환값도 성공한 레코드 수이다.
			int cnt = session.update("memberTest.updateMember", mv2);
			if (cnt > 0) {
				System.out.println("update 작업 성공!");
				session.commit();
			} else {
				System.out.println("update 작업 실패!!!");
			}
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		
		System.out.println("-----------------------------");

		
		// 2-3. delete 연습
		System.out.println("delete 작업 시작...");
		
		session = sessionFactory.openSession();
		
		try {
			// delete()메서드의 반환값: 성공한 레코드 수
			int cnt = session.delete("memberTest.deleteMember", "d002");
			
			if (cnt > 0) {
				System.out.println("delete 작업 성공!");
				session.commit();
			} else {
				System.out.println("delete 작업 실패!!!");
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		System.out.println("-----------------------------");
		
		// 2-4. select 연습
		// 1) 응답의 결과가 여러개일 경우...
		System.out.println("select연습 시작(결과가 여러개일 경우)...");
		
		// 응답의 결과가 여러개일 경우에는 selectList() 메서드를 사용한다.
		// 이 메서드는 여러개의 레코드를 VO에 담아서 List에 추가해 주는 작업을 
		// 자동으로 수행한다.
		
		session = sessionFactory.openSession(true);
		
		try {
			
			List<MemberVO> memList = session.selectList("memberTest.getAllMember");
			
			for (MemberVO mv3 : memList) {
				System.out.println("ID : " + mv3.getMemId());
				System.out.println("이름 : " + mv3.getMemName());
				System.out.println("전화 : " + mv3.getMemTel());
				System.out.println("주소 : " + mv3.getMemAddr());
				
				System.out.println("--------------");
			}
			
		} finally {
			session.close();
		}
		System.out.println("-----------------------------");		
		
		// 2) 응답결과가 1개인 경우...
		System.out.println("select 연습시작(결과가 1개일 경우)...");
		
		session = sessionFactory.openSession(true);
		
		try {
			MemberVO mv4 = (MemberVO) session.selectOne("memberTest.getMember", "c001");
			System.out.println("ID : " + mv4.getMemId());
			System.out.println("이름 : " + mv4.getMemName());
			System.out.println("전화 : " + mv4.getMemTel());
			System.out.println("주소 : " + mv4.getMemAddr());
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
	}
}
