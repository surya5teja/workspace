package com.general;

public class StaticTest {

	public int a;
	public static int b;

	public StaticTest() {
		a++;
		b++;
	}

	public static void main(String[] args) {
		StaticTest ts = new StaticTest();
		System.out.println("a::" + ts.a + "b::" + ts.b);
		StaticTest ts1 = new StaticTest();
		System.out.println("a::" + ts1.a + "b::" + ts1.b);
		StaticTest ts2 = new StaticTest();
		System.out.println("a::" + ts2.a + "b::" + ts2.b);
		StaticTest ts3 = new StaticTest();
		System.out.println("a::" + ts3.a + "b::" + ts3.b);
	}

}
