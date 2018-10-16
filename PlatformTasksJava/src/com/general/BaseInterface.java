package com.general;

public interface BaseInterface {

	default void test() {
		System.out.println("test");
	}

	static void staticMethod() {
		System.out.println("static1");
	}

	default void print(String str) {
		if (!isNull(str))
			System.out.println("MyData Print::" + str);
	}

	static boolean isNull(String str) {
		System.out.println("Interface Null Check");

		return str == null ? true : "".equals(str) ? true : false;
	}
}
