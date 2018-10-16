package com.test;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("test");
		String s = "suryateja,d,d,";
		s = s.substring(0, s.lastIndexOf(','));
		System.out.println(s);

	}
}
