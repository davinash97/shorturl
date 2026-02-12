package com.shorturl.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.shorturl.core.UrlCore;

@Service
public class UrlService {

	private static Random random = new Random(6);

	private static Map<String, String> map = new HashMap<>();

	public static String longToShort(String url) {
		long id = random.nextLong(10_000_000, 1_00_000_000);
		System.out.println("Generated random number is " + id);
		String key = UrlCore.encode(id);
		if(!map.containsKey(key)) {
			map.put(key, url);
		}
		return key;
	}

	public static String shortToLong(String key) {
		return map.get(key);
	}

}