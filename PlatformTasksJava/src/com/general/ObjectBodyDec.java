package com.general;

public class ObjectBodyDec {

	public static void main(String[] args) {
		Test test = new Test() {

			@Override
			public void run() {
				System.out.println("object body");
			}
		};
		test.run();
	}

}
