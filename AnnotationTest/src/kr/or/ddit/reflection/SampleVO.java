package kr.or.ddit.reflection;

import java.io.Serializable;

public class SampleVO implements Serializable {
	
	public String id;
	protected String name;
	private int age;
	
	public SampleVO() {
	}
	public SampleVO(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "SampleVO [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
