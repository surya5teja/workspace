package com.general;

public class ClockAngle {

	int degreeBwTwoHands = 6;

	public static int convertTimeToDegrees(String time) {
		int degrees = 0;
		String arr[] = time.split(":");
		int lhs = Integer.parseInt(arr[0]);
		int rhs = Integer.parseInt(arr[1]);
		lhs = lhs * 5;
		if (lhs > rhs)
			degrees = (lhs - rhs) * 6;
		else {
			degrees = (rhs - lhs) * 6;
		}
		return degrees;
	}

	public static void main(String[] args) {
		System.out.println(convertTimeToDegrees("11:50"));
	}
}
