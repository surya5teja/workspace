package com.general;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapOperations {

	public static void main(String[] args) {
		Map mp = new HashMap();
		mp.put("su", "sur");
		mp.put(1, "tej");
		Map<String, String> hashtable = new HashMap<>();
		hashtable.put(null, "sur");
		hashtable.put(null, "tej");
		for (Map.Entry<String, String> entry : hashtable.entrySet()) {
			System.out.println("key" + entry.getKey() + ":" + entry.getValue());
		}

		mp.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));

		Iterator<Map.Entry<String, String>> itr = mp.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry entry = itr.next();
			String key = String.valueOf(entry.getKey());
			System.out.println("Key = " + key + ", Value = " + entry.getValue());
		}
	}
}
