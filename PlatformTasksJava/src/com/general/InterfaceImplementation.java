package com.general;

public class InterfaceImplementation implements BaseInterface {

	static void staticMethod() {
		System.out.println("staticimpl");
	}

	static boolean isNull(String str) {
		System.out.println("Impl Null Check");

		return str == null ? true : false;
	}

	public static void main(String[] args) {
		InterfaceImplementation impl = new InterfaceImplementation();
		// impl.test();
		impl.print("");
		impl.isNull("su");
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		BaseInterface.super.test();
	}

}
