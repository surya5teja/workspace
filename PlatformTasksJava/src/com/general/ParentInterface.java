package com.general;

public interface ParentInterface {

	public void transaction();

	default void test() {
		System.out.println("test");
	}

	static void staticMethod() {
		System.out.println("static1");
	}
}
