package com.general;

import java.util.ArrayList;
import java.util.List;

public class StringSTest {

	public static List<String> generateStringArray(Integer n) {
		String testString = "Test ";
		List<String> stringFinalList = new ArrayList<String>();
		for (Integer i = 0; i <= n; i++) {
			stringFinalList.add(testString + i);
		}
		return stringFinalList;
	}

	public static void main(String[] args) {
		System.out.println(StringSTest.generateStringArray(10));
	}
}
