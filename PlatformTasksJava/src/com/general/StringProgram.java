package com.general;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StringProgram {

	public static void main(String[] args) {
		String s = "ascendascent";
		int distinct = 0;
		for (int i = 0; i < s.length();) {

			for (int j = 0; j < s.length(); j++) {

				if (s.charAt(i) == s.charAt(j)) {
					distinct++;

				}
			}
			if (distinct > 1) {
				System.out.println(s.charAt(i) + "--" + distinct);
			}
			String d = String.valueOf(s.charAt(i)).trim();
			s = s.replaceAll(d, "");
			distinct = 0;

		}
		String ss = "tezstatestaixtdra";
		Map<Character, Integer> mp = new ConcurrentHashMap<>();
		for (int i = 0; i < ss.length(); i++) {
			char c = ss.charAt(i);
			if (mp.containsKey(c)) {
				int cnt = mp.get(c);
				mp.put(c, ++cnt);
			} else {
				mp.put(c, 1);
			}
		}

		/*
		 * int count = 0; char arr[] = s.toCharArray(); for (int i = 0; i <
		 * arr.length; i++) { char c = arr[i]; char arr1[] =
		 * Arrays.copyOfRange(arr, i + 1, arr.length); //
		 * System.out.println(arr1);
		 *
		 * for (int j = 0; j < arr1.length; j++) { if (arr1[j] == c) { mp.put(c,
		 * count++); // System.out.println(arr1[j]); } }
		 *
		 * }
		 */

		for (Character key : mp.keySet()) {
			if (mp.get(key) <= 1) {
				mp.remove(key);
			}
		}
		System.out.println(mp);

	}
}
