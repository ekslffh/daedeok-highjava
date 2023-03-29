package kr.or.ddit.basic;

import java.util.Random;

public class Racing {
	static int currRank = 1;
}

class Horse extends Thread implements Comparable<Horse> {
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
	
	@Override
	public void run() {
		for (int i = 0; i < 3000; i++) {
			try {
				Thread.sleep(new Random().nextInt(2000));
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
