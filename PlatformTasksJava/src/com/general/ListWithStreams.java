package com.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListWithStreams {

	public static void main(String[] args) {
		List<String> ls = new ArrayList<>();
		ls.add("surya");
		ls.add("teja");
		ls.stream().filter(name -> name.equals("teja")).forEach(name -> System.out.println(name));

		List<Employee> lsEmp = new ArrayList<>();
		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setName("surya");
		Employee emp2 = new Employee();
		emp2.setId(2);
		emp2.setName("tej");
		lsEmp.add(emp1);
		lsEmp.add(emp2);
		lsEmp.stream().filter(Objects::nonNull).filter(name -> name.getId() == 1 && name.getName().equals("surya"))
				.forEach(name -> System.out.println(name.getName()));

		Map<Integer, String> map = lsEmp.stream().collect(Collectors.toMap(Employee::getId, Employee::getName));
		System.out.println(map);

	}
}
