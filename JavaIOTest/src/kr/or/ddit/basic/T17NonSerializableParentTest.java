package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T17NonSerializableParentTest {
/*
	부모 클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
	부모객체의 필드값 처리 방법에 대하여...
	
	1. 부모클래스가 Serializable 인터페이스를 구현하도록 해야한다.
	2. 자식 클래스에 writeObject() 및 readObject()메서드를 이용하여 부모객체의 필드값
		처리를 할 수 있도록 직접 구현한다.
 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("D:/D_Other/nonSerializableTest.bin"));
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		
		oos.writeObject(child); // 직렬화
		
		oos.flush(); // 버퍼 비우기(강제 내보내기) 생략가능
		oos.close();
		
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("D:/D_Other/nonSerializableTest.bin"));
		
		Object obj = ois.readObject(); // 역직렬화
		
		Child child2 = (Child) obj;
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());
		
		ois.close();
	}
}

// Serializable을 구현하지 않은 부모클래스
class Parent {
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}

// Serializable을 구현한 자식 클래스
class Child extends Parent implements Serializable {
	
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	/**
	 * 직렬화 될 때 자동으로 호출됨.
	 * (접근 제한자가 private이 아니면 자동 호출되지 않음)
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		// 부모객체 필드값 처리하기
		out.writeUTF(getParentName()); // 부모객체 필드값 처리하기
		
		out.defaultWriteObject(); // 기본 저장기능 호출
	}
	
	/**
	 * 역직렬화 될 때 자동으로 호출됨.
	 * (접근제한자가 private이 아니면 자동 호출되지 않음)
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		
		setParentName(in.readUTF()); // 부모객체 필드값 처리하기
		
		in.defaultReadObject(); // 기본 읽기 기능 호출하기
	}
	
}