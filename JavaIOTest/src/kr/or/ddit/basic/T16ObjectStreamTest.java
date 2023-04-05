package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 	객체 입출력을 위한 보조 스트림 예제
 */
public class T16ObjectStreamTest {
	public static void main(String[] args) {
		// Member 객체 생성하기
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("이순신", 30, "대구");
		Member mem3 = new Member("일지매", 40, "부산");
		Member mem4 = new Member("성춘향", 50, "광주");
		
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
					new FileOutputStream("D:/D_Other/memObj.bin")));
			oos.writeObject(mem1); // 직렬화
			oos.writeObject(mem2); // 직렬화
			oos.writeObject(mem3); // 직렬화
			oos.writeObject(mem4); // 직렬화
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/////////////////////////////////////////
		
		ObjectInputStream ois = null; 
		
		try {
			// 객체를 읽어오기 위한 보조스트림 생성하기
			ois = new ObjectInputStream(
					new BufferedInputStream(
					new FileInputStream("D:/D_Other/memObj.bin")));
			
			Object obj = null;
			
			while ((obj = ois.readObject()) != null) { // 역직렬화
				
				// 읽어온 데이터를 원래의 객체 타입으로 변환 후 사용한다.
				Member mem = (Member) obj;
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("---------------------------");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			
		}
	}
}

class Member implements Serializable {
	// 자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있음
	
	
	private transient String name;
	
	// transient => 직렬화가 되지 않을 멤버변수에 지정한다.
	//				(static필드도 직렬화 대상이 아니다.)
	//				직렬화 대상이 아닌 멤버변수는 기본값으로 저장된다.
	//				(참조형변수 : null, 숫자형변수: 0)
	
	private transient int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}