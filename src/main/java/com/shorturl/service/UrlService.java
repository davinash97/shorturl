package com.shorturl.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

import com.shorturl.core.UrlCore;

@Service
public class UrlService {

	private static AtomicLong longAtom = new AtomicLong(1);

	private static Map<String, String> map = new HashMap<>();

	public static String longToShort(String url) {
		long id = longAtom.getAndIncrement();
		System.out.println("Generated longAtom is " + longAtom);
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