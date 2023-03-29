package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T11DisplayCharacterTest {
	
	static int currRank = 1; // 현재 순위 정보

/*
 	3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데, 출력을 끝낸 순서대로
 	결과를 나타내는 프로그램 작성하기
 */
	
	public static void main(String[] args) {
		
		List<DisplayCharacter> disCharList = new ArrayList<DisplayCharacter>();
		
		disCharList.add(new DisplayCharacter("김은지"));
		disCharList.add(new DisplayCharacter("나성민"));
		disCharList.add(new DisplayCharacter("배소현"));
		disCharList.add(new DisplayCharacter("현성윤"));
		
		for (Thread th : disCharList) {
			th.start();
		}
		
		for (Thread th : disCharList) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 순위에 맞춰 오름차순으로 정렬하기
		Collections.sort(disCharList);
		
		System.out.println("경기 끝...");
		System.out.println("--------------------");
		System.out.println("경기 결과");
		System.out.println("--------------------");
		System.out.println("순위\t:\t이름");
		System.out.println("====================");
		
		for (DisplayCharacter dc : disCharList) {
			System.out.println(dc.getRank() + "\t:\t" + dc.getName());
		}
	}
}

// 알파벳 대문자를 출력하기 위한 스레드
class DisplayCharacter extends Thread implements Comparable<DisplayCharacter> {
		
	private String name;
	
	private int rank;
	
	public DisplayCharacter(String name) {
		super(name);
		this.name = name;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public void run() {
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + "의 출력 문자 : " + ch);
			
			// sleep()메서드의 값을 200 ~ 500사이의 난수로 한다.
			try {
				Thread.sleep((int)(Math.random() * 301 + 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + " 출력 끝...");
		
		// 순위정보 설정하기
		setRank(T11DisplayCharacterTest.currRank++);
		
	}

	// 순위의 오름차순 정렬
	@Override
	public int compareTo(DisplayCharacter dc) {
		return new Integer(getRank()).compareTo(dc.getRank());
	}
}