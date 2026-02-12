package com.shorturl.core;

public class UrlCore {
	
	private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ";
	private static final int BASE = 62;

	public static String encode(long value) {
		if(value < 0) throw new IllegalArgumentException("value should be greater than 0");

		if(value == 0) ALPHABET.charAt(0);
		StringBuilder str = new StringBuilder();

		long temp = value;
		while(temp > 0) {
			int remainder = (int) temp % BASE;
			temp /= BASE;
			str.append(ALPHABET.charAt(remainder));
		}
		return str.reverse().toString();
	}
}