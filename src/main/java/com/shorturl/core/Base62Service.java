package com.shorturl.core;

import java.util.Arrays;

import com.shorturl.exception.InvalidTokenException;

public class Base62Service {

	private static final String ALLOWED_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int BASE = ALLOWED_CHARACTERS.length(); // 62

	private static final int[] CHARACTER_INDEX_LOOKUP = new int['z' + 1]; // total of 122 + 1

	static {
		Arrays.fill(CHARACTER_INDEX_LOOKUP, -1); // populate with -1 to catch unsafe inputs
		
		for(int i = 0; i < BASE; i++) {
			CHARACTER_INDEX_LOOKUP[ALLOWED_CHARACTERS.charAt(i)] = i; // populate validate lookup coordinates
		}
	}

	public static String encode(Long id) {
		if(id == null
			|| id < 0) {
			throw new IllegalArgumentException("Invalid or negative number given");
		}

		if(id == 0) {
			return String.valueOf(ALLOWED_CHARACTERS.charAt(0));
		}

		StringBuilder sb = new StringBuilder();
		long currentId = id;

		while(currentId > 0) {
			int remainder = (int) (currentId % BASE);
			sb.append(ALLOWED_CHARACTERS.charAt(remainder));
			currentId /= BASE;
		}

		return sb.reverse().toString();
	}

	public static Long decode(String token) {

		if(token == null
			|| token.isBlank()) {
				throw new InvalidTokenException("Provided URL token cannot be null or empty");
		}

		long id = 0;
		for(int i = 0; i < token.length(); i++) {
			char c = token.charAt(i);

			if(c >= CHARACTER_INDEX_LOOKUP.length
				|| CHARACTER_INDEX_LOOKUP[c] == -1) {
					throw new InvalidTokenException("invalid character discovered " + c);
			}

			id = (id * BASE) + CHARACTER_INDEX_LOOKUP[c];
		}

		return id;
	}
}
