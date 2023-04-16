package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T08HashMapTest {

   public static void main(String[] args) {
      
      Map<String, String> map = new HashMap<String, String>();
      
      // 자료추가 => put(key값, value값)
      map.put("name","홍길동");
      map.put("addr","대전");
      map.put("tel","010-1234-5678");
      
      System.out.println("map => " + map);
      
      // 자료 수정 => 데이터를 저장할 때 key값이 같으면 나중에 입력한 값이 저장된다.
      //put(수정할 key값 ,새로운 value값)
      
      map.put("addr","서울");
      System.out.println("map => "+ map);
      
      // 자료삭제 => remove(삭제할 key값)
      map.remove("name");
      System.out.println("map =>" + map);
      
      // 자료읽기 => get(key값)
      System.out.println("addr =" + map.get("addr"));
      System.out.println("=============================");
      
      //key값들을 읽어와 데이터를 출력하는 방법
      
      //방법1 => keySet()메서드 이용하기
      //        Map의 key값들만 읽어와 Set타입 객체로 반환한다.
      Set<String> keySet = map.keySet();
      System.out.println("Iterator를 이용한 방법");
      Iterator<String> it = keySet.iterator();
      while(it.hasNext()) {
         String key = it.next();
         System.out.println(key + " : " + map.get(key));
      }
      System.out.println("--------------------------------------");
      
      //방법2 => Set타입의 데이터를 '향상된 for문(for-each)'으로 처리하면 Iterator를 사용하지 않아도 된다
      System.out.println("향상된 for문을 이용한 방법");
            for(String key : keySet) {
               System.out.println(key + " : "+ map.get(key));
            }
            System.out.println("--------------------------------");
            
      //방법3 => value값들만 읽어와 출력하기
            System.out.println("values()메서드 이용한 방법");
            for(String value : map.values()){
               System.out.println(value);
            }
      System.out.println("--------------------------------------------");
      
      //방법4 => Map관련 클래스는 Map.Entry 타입 내부  class가 만들어져 있다
      // 이 내부 클래스에는 getKey()와 getValue()를 구현하고 있다.
      //Map에서 이 Map.Entry 타입의 객체들을 Set 타입으로 가져올 수 있다.
      
      Set<Map.Entry<String, String>> entrySet = map.entrySet();
            
      //Iterator 이용하여 순차적으로 접근하기
      Iterator<Map.Entry<String,String>> entryIt = entrySet.iterator();

      while(entryIt.hasNext()) {
         Map.Entry<String, String> entry = entryIt.next();
         System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
         System.out.println(entry);
      }
   }

}