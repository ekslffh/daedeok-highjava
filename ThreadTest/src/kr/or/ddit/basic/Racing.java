package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Racing {
	static int currRank = 1;
	
	public static void clearScreen() {
	    for (int i = 0; i < 80; i++)
	      System.out.println();
	  }
	
	public static void main(String[] args) {
		List<Horse> horseList = new ArrayList<Horse>();
		// 10마리 말객체 생성
		for (int i = 1; i <= 10; i++) {
			horseList.add(new Horse(i + "번말"));
		}
		
		// 10마리 말들 각각 출발
		for (Horse horse : horseList) {
			horse.start();
		}
		
		StringBuffer sb = new StringBuffer();
		// 10위까지 순위매길때까지 반복
		while (currRank <= horseList.size()) {
			// 화면지우기
			clearScreen();
			
			// 경기화면 만들기
			sb.append("==========================================================\n");
			for (Horse horse : horseList) {
				sb.append(horse.getCurPostion());
				if (horse.getRank() != 0) {
					sb.append(horse.getRank());
				}
				sb.append("\n");
			}
			sb.append("==========================================================\n");
			// 경기화면 출력
			System.out.println(sb);
			// StringBuffer 초기화
			sb.setLength(0);
			// 1초 기다리고 다시 반복
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("경기종료!\n결과 집계중...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clearScreen();
		// 순위기준으로 오름차순 정렬
		Collections.sort(horseList);
		System.out.println("--------------------");
		System.out.println("경기 결과");
		System.out.println("--------------------");
		System.out.println("이름\t:\t순위");
		System.out.println("====================");
		for (Horse horse : horseList) {
			System.out.println(horse.getName() + "\t:\t" + horse.getRank());
		}
		System.out.println("====================");
	}
}

class Horse extends Thread implements Comparable<Horse> {
	private int section = 1;
	private int rank;
	
	public Horse(String name) {
		super(name);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public String getCurPostion() {
		String curPosition = getName() + "\t";
		for (int i = 1; i <= 50; i++) {
			
			if (section == i) {
				curPosition += ">";
				continue;
			}
			curPosition += "-";
		}
		return curPosition;
	}
	
	@Override
	public void run() {
		for (; section < 50; section++) {
			try {
				Thread.sleep(new Random().nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setRank(Racing.currRank++);
	}

	@Override
	public int compareTo(Horse horse) {
		return this.getRank() - horse.getRank(); 
	}
}
