package com.general;

@FunctionalInterface
public interface FunctionalInterface1 {

	public void runner();

	default void log(String str) {
		System.out.println("I1 logging::" + str);
	}

	static void print(String str) {
		System.out.println("Printing " + str);
	}
}