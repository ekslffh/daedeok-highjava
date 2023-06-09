package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T03ListSort {
/*
  	정렬과 관련된 Interface는 Comparable과 Comparator 두개가 존재한다.
  	
  	- 보통 객체 자체에 정렬기능을 넣기 위해서는 Comparable을 구현하고,
  	  정렬기준을 별도로 구현하고자 할 경우에는 Comparator를 구현하여
  	  사용한다.
  	  
  	- Comparable은 compareTo() 메서드를 구현해야 하고,
  	  Comparator에서는 compare() 메서드를 구현해야 한다.
*/
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전: " + list);
		
		/*
		    정렬은 Collections.sort() 메서드를 이요하여 정렬한다.
		    정렬은 기본적으로 '오름차순 정렬'을 수행한다.
		*/
		Collections.sort(list);
		System.out.println("정렬 후: " + list);
		
		Collections.shuffle(list);
		System.out.println("섞기 후: " + list);
		
		// 정렬방식을 결정하는 객체(외부정렬자)
		Collections.sort(list, new Desc());
		System.out.println("외부정렬자 이용하여 정렬 후: " + list);
	}

}

/*
 * 정렬방식을 결정하는 class는 Comparator 인터페이스를 구현해야 한다.
 * 이 Comparator 인터페이스의 compare() 메서드를 재정의 해주면 된다.
 */
class Desc implements Comparator<String> {
	/*
	 * compare()메서드의 반환값을 결정하는 방법
	 * 
	 * - 오름차순일 경우
	 * => 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 한다.
	 */
	@Override
	public int compare(String str1, String str2) {
		return str1.compareTo(str2) * -1;
	}
	
}