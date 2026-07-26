/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.shorturl.url.repository.impl;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UrlRepositoryMemoryTest {

	public UrlRepositoryMemoryTest() {}

	public static final AtomicLong idSequence = new AtomicLong(1);

	private final UrlRepositoryMemory instance = new UrlRepositoryMemory();

	private static final String[] links = {
			"https://google.com",
			"https://chat.openai.com",
			"https://github.com/davinash97",
			"https://example.com/path/to/resource",
			"https://example.com/path?query=param&foo=bar",
			"https://example.com/path#section",
			"https://localhost:8080/test",
			"http://example.org/",
			"https://sub.domain.example.com",
			"https://example.com:8443/path?query=1#frag",
			"https://verylongdomainname.example.com/some/very/long/path/to/resource?param1=value1&param2=value2#end",
			null,
			"https://短域名.com/路径?查询=值#片段",
	};

	private static final String[] ids = {
			"1",
			"2",
			"3",
			"abc",
			"xyz123",
			"A1B2C3",
			"0001",
			"z9Y8X7",
			"shortId",
			"test123",
			"Base62Id",
			null,
			"ZZZZZZ",
	};

	@Test
	public void testRepository() {
		System.out.println("Testing Repository");
		for (int i = 0; i < links.length; i++) {
			String id = ids[i];
			String link = links[i];

			String insertResult = instance.insertOne(id, link);
			String findResult = instance.findOne(id);
			if (link == null || id == null) {
				assertNull(insertResult);
				assertNull(findResult);
			} else {
				assertEquals(id, insertResult, "Failed insert at id: " + id);
				assertEquals(link, findResult, "Failed find at id: " + id);
			}
		}
	}
}
