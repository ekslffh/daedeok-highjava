package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T06WildCardTest {
	
	/**
	 * 장바구니 항목조회를 위한 메서드(음식 항목 가능)
	 * @param cart
	 */
	public static void displayItemInfo(Cart<?> cart) {
		System.out.println("= 음식류 장바구니 항목 리스트 =");
		for (Object obj : cart.getList()) {
			System.out.println(obj.toString());
		}
		System.out.println("---------------------------------");
	}
	
	/**
	 * 장바구니 항목조회를 위한 메서드(음료나 그 하위 항목 가능)
	 * @param cart
	 */
	public static void displayItemInfo2(Cart<? extends  Drink> cart) {
		System.out.println("= 음료나 그 하위 항목 장바구니 항목 리스트 =");
		for (Object obj : cart.getList()) {
			System.out.println(obj.toString());
		}
		System.out.println("---------------------------------");
	}
	
	/**
	 * 장바구니 항목조회를 위한 메서드(고기류나 그 상위 항목 가능)
	 * @param cart
	 */
	public static void displayItemInfo3(Cart<? super Meat> cart) {
		System.out.println("= 고기류나 그 상위 항목 장바구니 항목 리스트 =");
		for (Object obj : cart.getList()) {
			System.out.println(obj.toString());
		}
		System.out.println("---------------------------------");
	}
	
	public static void main(String[] args) {
		
		Cart<Food> foodCart = new Cart<Food>();
		foodCart.addItem(new Meat("돼지고기", 5000));
		foodCart.addItem(new Meat("소고기", 25000));
		foodCart.addItem(new Juice("오렌지쥬스", 1500));
		foodCart.addItem(new Coffee("아메리카노", 2000));
		
		Cart<Meat> meatCart = new Cart<Meat>();
		meatCart.addItem(new Meat("돼지고기", 5000));
		meatCart.addItem(new Meat("소고기", 25000));
//		meatCart.addItem(new Juice("오렌지쥬스", 1500));
//		meatCart.addItem(new Coffee("아메리카노", 2000));
		
		Cart<Drink> drinkCart = new Cart<Drink>();
//		drinkCart.addItem(new Meat("돼지고기", 5000));
//		drinkCart.addItem(new Meat("소고기", 25000));
		drinkCart.addItem(new Juice("오렌지쥬스", 1500));
		drinkCart.addItem(new Coffee("아메리카노", 2000));
		
		displayItemInfo(foodCart);
		displayItemInfo(meatCart);
		displayItemInfo(drinkCart);
		//////////////////////////////
		
//		displayItemInfo2(foodCart);
//		displayItemInfo2(meatCart);
		displayItemInfo2(drinkCart);
		//////////////////////////////
		
		displayItemInfo3(foodCart);
		displayItemInfo3(meatCart);
//		displayItemInfo3(drinkCart);
		//////////////////////////////
	}
}

class Food {
	private String name; // 음식이름
	private int price; 	 // 가격
	
	public Food(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return this.name + "(" + this.price + "원)";
	}
}

class Meat extends Food {

	public Meat(String name, int price) {
		super(name, price);
	}
}

class Drink extends Food {

	public Drink(String name, int price) {
		super(name, price);
	}
}

class Juice extends Drink {

	public Juice(String name, int price) {
		super(name, price);
	}
}

class Coffee extends Drink {

	public Coffee(String name, int price) {
		super(name, price);
	}
}

/**
 * 장바구니
 * @param <T>
 */
class Cart<T> {
	
	private List<T> list;
	
	public Cart() {
		list = new ArrayList<T>();
	}
	
	public void addItem(T item) {
		list.add(item);
	}

	public List<T> getList() {
		return list;
	}
}