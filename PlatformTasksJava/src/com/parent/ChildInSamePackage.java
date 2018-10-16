package com.parent;

public class ChildInSamePackage extends BaseClass {

	int add(int a) {
		return 1;
	}

	public static void main(String[] args) {
		BaseClass b = new ChildInSamePackage();
		b.add();

	}
}
