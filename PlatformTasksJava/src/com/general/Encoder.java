/**
 *
 */
package com.general;

import java.util.Base64;

/**
 * @author surteja
 *
 */
public class Encoder {

	public static void main(String[] args) {
		String originalInput = "test inpusasasas";
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		System.out.println(encodedString);
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		System.out.println(decodedString);
	}
}
