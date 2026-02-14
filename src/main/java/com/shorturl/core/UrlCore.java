package com.shorturl.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlCore {

	private static final Logger logger = LoggerFactory.getLogger(UrlCore.class);

	private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int BASE = 62;

	public static String encode(long value) {
		if (value < 0) {
			logger.error("Attempted to encode negative value: {}", value);
			throw new IllegalArgumentException("value should be greater than 0");
		}

		if (value == 0) {
			logger.debug("Encoding 0 -> {}", ALPHABET.charAt(0));
			return String.valueOf(ALPHABET.charAt(0));
		}

		StringBuilder str = new StringBuilder();
		long temp = value;

		while (temp > 0) {
			int remainder = (int) (temp % BASE);
			temp /= BASE;
			str.append(ALPHABET.charAt(remainder));
		}

		String encoded = str.reverse().toString();
		logger.debug("Encoded {} -> {}", value, encoded);
		return encoded;
	}

	public static long decode(String key) {
		if (key.isBlank() || key.isEmpty()) {
			logger.error("Attempted to decode empty or blank string");
			throw new IllegalArgumentException("encoded string cannot be null or empty");
		}

		long result = 0;
		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			int index = ALPHABET.indexOf(c);
			if (index == -1) {
				logger.error("Invalid character in encoded string: {}", c);
				throw new IllegalArgumentException("invalid character in encoded string: " + c);
			}
			result = result * BASE + index;
		}

		logger.debug("Decoded {} -> {}", key, result);
		return result;
	}
}
