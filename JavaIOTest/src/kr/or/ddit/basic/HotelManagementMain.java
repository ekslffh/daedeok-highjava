package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelManagementMain {
	public static void main(String[] args) {
		new HotelManagement().startManagement();
	}
}

class HotelManagement {
	private Scanner sc;
	private Map<Integer, String> roomInfo;
	public HotelManagement() {
		sc = new Scanner(System.in);
		roomInfo = new HashMap<Integer, String>();
	}
	
	public void startManagement() {
		// 파일 정보 읽어오기
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader("D:/D_Other/호텔예약정보/reservation.txt");
			br = new BufferedReader(fr);
			
			String temp = "";
			
			while((temp = br.readLine()) != null) {
				String[] infos = temp.split(":");
				roomInfo.put(Integer.parseInt(infos[0]), infos[1]);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("***************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("***************");
		while (true) {
			switch(printMenu()) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				checkRoomInfo();
				break;
			case 4:
				System.out.println("\n***************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("***************");
				exit();
				System.exit(0);
			default:
				System.out.println("잘못된 입력입니다.");
			}
		}
	}
	
	public int printMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n****************************\n");
		sb.append("어떤 업무를 하시겠습니까?\n");
		sb.append("1.체크인 2.체크아웃 3.객실상태 4.업무종료\n");
		sb.append("*****************************\n");
		sb.append("메뉴선택 => ");
		System.out.print(sb);
		return Integer.parseInt(sc.nextLine());
	}
	
	public void checkIn() {
		System.out.println("\n어느 방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNumber = Integer.parseInt(sc.nextLine());
		System.out.println("\n누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = sc.nextLine();
		if (roomInfo.containsKey(roomNumber)) {
			System.out.println(roomNumber + "방에는 이미 사람이 있습니다.");
			return;
		}
		roomInfo.put(roomNumber, name);
		System.out.println("체크인 되었습니다.");
	}
	
	public void checkOut() {
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNumber = Integer.parseInt(sc.nextLine());
		if (!roomInfo.containsKey(roomNumber)) {
			System.out.println(roomNumber + "방에는 체크인한 사람이 없습니다.");
			return;
		}
		roomInfo.remove(roomNumber);
		System.out.println("체크아웃 되었습니다.");
	}
	
	public void checkRoomInfo() {
		Set<Integer> keySet = roomInfo.keySet();
		if (keySet.size() == 0) {
			System.out.println("현재 투숙하고 있는 사람이 없습니다.");
			return;
		}
		for (Integer key : keySet) {
			System.out.println("방번호: " + key + ", 투숙객: " + roomInfo.get(key));
		}
	}
	
	private void exit() {
		FileOutputStream fos = null;
		PrintWriter out = null;
		try {
			fos = new FileOutputStream("D:/D_Other/호텔예약정보/reservation.txt");
			out = new PrintWriter(fos);
			Set<Integer> keySet = roomInfo.keySet();
			List<Integer> keyList = new ArrayList<Integer>(keySet);
			Collections.sort(keyList);
			for (Integer key : keyList) {
				out.println(key + ":" + roomInfo.get(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
