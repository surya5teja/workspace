package com.general;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {

	public static void main(String[] args) {
		Set<String> s = new HashSet<>();
		System.out.println(s.add(null) + "" + s.hashCode());
		System.out.println(s.add(null) + "" + s.hashCode());

	}
}
