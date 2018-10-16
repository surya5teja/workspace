package com.general;

public class PassByValueAndReference {

	public static void main(String[] args) {
		int a = 1;
		int b = 2;
		Employee e1 = new Employee();
		e1.setId(1);
		e1.setName("surya");
		Employee e2 = new Employee();
		e2.setId(2);
		e2.setName("teja");
		PassByValueAndReference ps = new PassByValueAndReference();
		ps.swap(a, b);
		ps.swap(e1, e2);
		System.out.println("a:" + a + "b::" + b);
		System.out.println("emp1" + e1.getId() + "emp2" + e2.getName());
	}

	void swap(int a, int b) {
		int temp = b;
		b = a;
		a = temp;
	}

	void swap(Employee e1, Employee e2) {
		e1.setId(e2.getId());
		e2.setName(e1.getName());
	}
}
