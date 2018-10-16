package com.child;

import com.parent.BaseClass;

public class ChildClass extends BaseClass {

	/*
	 * public ChildClass(int a) { super(a); }
	 */

	int add() {
		return 1;
	}

	public static void main(String[] args) {
		BaseClass b = new ChildClass();
		b.add();
	}

}
