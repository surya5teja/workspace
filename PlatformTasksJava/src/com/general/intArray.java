package com.general;

import java.util.Arrays;

public class intArray {

	public static void main(String[] args) {
		int arr[] = { 1, 3, 2, 2, 5, 6, -1, 3, -1 };
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i] + arr[j] >= 5) {
					int arr1[] = new int[2];
					arr1[0] = arr[i];
					arr1[1] = arr[j];
					System.out.println(Arrays.toString(arr1));
				}
			}
		}
	}
}
