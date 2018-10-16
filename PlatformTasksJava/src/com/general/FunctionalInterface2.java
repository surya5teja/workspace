package com.general;

@FunctionalInterface
public interface FunctionalInterface2 implements FunctionalInterface1 {

	@Override
	public void runner();

	@Override
	default void log(String str) {
		System.out.println("I1 logging::" + str);
	}
}
