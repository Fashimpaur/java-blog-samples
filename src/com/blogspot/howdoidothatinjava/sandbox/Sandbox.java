package com.blogspot.howdoidothatinjava.sandbox;

import java.util.List;
import java.util.function.Predicate;

public class Sandbox {
	
	public static void main(String[] args) {
		List<TestObject> objList = TestObject.generateTestObjectList();
		Predicate<TestObject> objFilter = tO -> tO.getA() > 20;
		objList.stream().filter(objFilter).forEach(tO -> tO.displayObject());
	}

}
