package com.blogspot.howdoidothatinjava.sandbox;

import java.util.ArrayList;
import java.util.List;

public class TestObject {
	int a, b;
	
	public TestObject(){
		a = 0;
		b = 0;
	}
	
	public TestObject(int a, int b){
		this.a = a;
		this.b = b;
	}
	
	public List<TestObject> generateTestObjectList(){
		List<TestObject> out = new ArrayList<TestObject>();
		int[] aArray = new int[]{0, 1, 2, 3, 5, 8, 13, 21, 34, 45, 79, 124};
		int[] bArray = new int[]{1, 3, 6, 10, 15, 21, 28, 37, 47, 58, 70, 82};
		for (int i = 0; i < aArray.length; i++){
			out.add(new TestObject(aArray[i], bArray[i]));
		}
		return out;
	}
	
	public int getA(){
		return a;
	}
	
	public int getB(){
		return b;
	}
	
	public void displayObject(){
		System.out.println("TestObject: a: " + getA() + " b: " + getB());
	}
	
}
