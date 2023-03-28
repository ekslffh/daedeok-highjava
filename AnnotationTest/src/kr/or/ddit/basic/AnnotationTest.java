package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) {
		
		// 선언된 메서드 목록 가져오기
		Method[] methodArr = Service.class.getDeclaredMethods();
		
		for (Method m : methodArr) {
			
			System.out.println("메서드명 : " + m.getName());
			
			Annotation[] annos = m.getDeclaredAnnotations();
			
			for (Annotation anno : annos) {
				
				if (anno.annotationType().getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnn = (PrintAnnotation) anno;
					for (int i = 0; i < printAnn.count(); i++) {
						System.out.print(printAnn.value());
					}
				}
			}
			System.out.println(); // 줄바꿈 처리하기...
		}
		
	}
}
