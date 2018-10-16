package com.general;

import java.util.function.Consumer;

public class MyConsumer implements Consumer<Employee> {

	@Override
	public void accept(Employee t) {
		if (t.getId() == 1)
			System.out.println("1st employee");
	}

}
