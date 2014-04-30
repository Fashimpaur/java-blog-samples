package com.blogspot.howdoidothatinjava.sandbox;

import static com.blogspot.howdoidothatinjava.utilities.PrintUtilties.*;

import java.util.ArrayList;
import java.util.List;

public class TestObject {
	int a, b;
	String name;
	
	public TestObject(){
		a = 0;
		b = 0;
		name = "unnamed";
	}
	
	public TestObject(int a, int b){
		this.a = a;
		this.b = b;
		name = "unnamed";
	}
	
	public TestObject(int a, int b, String name){
	    this.a = a;
	    this.b = b;
	    this.name = name;
	}
	
	public static List<TestObject> generateTestObjectList(){
		List<TestObject> out = new ArrayList<TestObject>();
		int[] aArray = new int[]{0, 1, 2, 3, 5, 8, 13, 21, 34, 45, 79, 124};
		int[] bArray = new int[]{1, 3, 6, 10, 15, 21, 28, 37, 47, 58, 70, 82};
		
		String[] names = new String[]{"Lefty", "Windsurfer", "Shortstop", "Viper", "Stingray", "Snoopy", "Archangel", "Rainmaker", "Boss Hog", "Terror", "Pitbull", "Axe"};
		for (int i = 0; i < aArray.length; i++){
			out.add(new TestObject(aArray[i], bArray[i], names[i]));
		}
		return out;
	}
	
	public int getA(){
		return a;
	}
	
	public int getB(){
		return b;
	}
	
	public String getName(){
	    return name;
	}
	
	public void displayObject(){
	    String tabs = getName().length() > 9 ? "\t" : "\t\t";
		println("TestObject (" + getName() + "):" + tabs + "a: " + getA() + "\tb: " + getB());
	}
	
}
