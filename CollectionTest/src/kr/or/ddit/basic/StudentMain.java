package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 학번의 오름차순으로 정렬하여 출력하는 부분
 * 총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 * 총점이 같으면 학번의 내림차순으로 정렬되도록 한다.
 * 학번 정렬기준은 Student클라스 자체에서 제공하고
 * 총점 정렬기준은 외부클래스에서 제공하도록 한다. 
 */
public class StudentMain {

	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();
		students.add(new Student("20170935", "홍길동", 80, 90, 50));
		students.add(new Student("20200925", "전우치", 90, 75, 95));
		students.add(new Student("20180835", "박지원", 70, 50, 100));
		students.add(new Student("20150846", "배주석", 65, 90, 80));
		students.add(new Student("20190748", "석두호", 80, 75, 90));
		students.add(new Student("20210847", "김지만", 80, 90, 90));
		
		System.out.println("기존 학생 리스트");
		for (Student student : students) {
			System.out.println(student);
		}
		
		Collections.sort(students);
		System.out.println("학번 오름차순 정렬 후");
		for (Student student : students) {
			System.out.println(student);
		}
		
		Collections.sort(students, new StudentComparator());
		System.out.println("총점, 학번 순서대로 내림차 순 정렬 후 ");
		for (Student student : students) {
			System.out.println(student);
		}
		
		// 등수 매기기
		students.get(0).setRank(1);
		for (int i = 1; i < students.size(); i++) {
			students.get(i).setRank((students.get(i).getSum() == students.get(i - 1).getSum())
									? students.get(i - 1).getRank() 
									: i + 1);
		}
		System.out.println("등수 매긴 후 ");
		for (Student student : students) {
			System.out.println(student);
		}
	}

}

class StudentComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return (o1.getSum() == o2.getSum()) ? o2.getNumber().compareTo(o1.getNumber()) : o2.getSum() - o1.getSum(); 
	}
}

class Student implements Comparable<Student> {
	private String number;
	private String name;
	private int korean;
	private int english;
	private int math;
	private int sum;
	private int rank;
	
	public Student(String number, String name, int korean, int english, int math) {
		this.number = number;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.sum = korean + english + math;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKorean() {
		return korean;
	}
	public void setKorean(int korean) {
		this.korean = korean;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "Student [number=" + number + ", name=" + name + ", korean=" + korean + ", english=" + english
				+ ", math=" + math + ", sum=" + sum + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student o) {
		return this.getNumber().compareTo(o.getNumber());
	}
}
