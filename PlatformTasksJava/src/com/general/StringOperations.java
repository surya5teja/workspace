package com.general;

public class StringOperations {

	public void test(String s) {
		System.out.println("String");
	}

	public int test(StringBuffer sb) {
		System.out.println("StringBuffer");
		return 1;
	}

	public static void main(String[] args) {
		new StringOperations().test("su");

		String s1 = "abc";
		String s2 = new String("abc");
		System.out.println(s1 == s2);
	}
}
