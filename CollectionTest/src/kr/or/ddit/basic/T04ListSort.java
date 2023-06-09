package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04ListSort {

	public static void main(String[] args) {

		List<Member> memList = new ArrayList<Member>();

		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "이순신", "010-4444-1111"));
		memList.add(new Member(6, "강감찬", "010-5555-1111"));
		memList.add(new Member(2, "일지매", "010-6666-1111"));

		System.out.println("정렬 전");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("---------------------------------------------");

		Collections.sort(memList);
		
		System.out.println("정렬 후");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("---------------------------------------------");
		
		Collections.shuffle(memList);
		
		Collections.sort(memList, new SortNumDesc());
		System.out.println("번호의 내림차순 정렬 후");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("---------------------------------------------");
	}

}

// Member의 번호의 내림차순으로 정렬하기
class SortNumDesc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
//		if (mem1.getNum() > mem2.getNum()) {
//			return -1;
//		} else if (mem1.getNum() == mem2.getNum()) {
//			return 0;
//		} else {
//			return 1;
//		}
		
		// Wrapper클래스에서 제공하는 메서드를 이용하는 방법
		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
	}
	
	
}

// 이름을 기준으로 오름차순 정렬이 되도록 하는 클래스 만들기
class Member implements Comparable<Member> {
	private int num;
	private String name;
	private String tel;

	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public int compareTo(Member mem) {
		return getName().compareTo(mem.getName());
//		return getNum() - mem.getNum();
//		return getTel().compareTo(mem.getTel());
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
}