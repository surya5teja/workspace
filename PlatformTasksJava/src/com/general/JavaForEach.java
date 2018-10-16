package com.general;

import java.util.ArrayList;
import java.util.List;

public class JavaForEach {

	public static void main(String[] args) {
		Employee emp1 = new Employee();
		emp1.setId(1);
		Employee emp2 = new Employee();
		emp2.setId(2);
		List<Employee> empLis = new ArrayList<>();
		empLis.add(emp1);
		empLis.add(emp2);

		MyConsumer con = new MyConsumer();
		empLis.forEach(con);

	}
}
