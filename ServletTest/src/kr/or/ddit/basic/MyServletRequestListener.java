package kr.or.ddit.basic;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener 
		implements ServletRequestListener, ServletRequestAttributeListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println(
				"[MyServletRequestListener] requestDestroyed() 호출됨");
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println(
				"[MyServletRequestListener] requestInitialized() 호출됨");		
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}

}
