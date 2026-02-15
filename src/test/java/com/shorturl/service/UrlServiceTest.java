package com.shorturl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlServiceTest {

    @Autowired
    private UrlService urlService;

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
            "https://短域名.com/路径?查询=值#片段"
    };

    @Test
    void testEncodeDecodeRoundTrip() {
        for (String link : links) {
            String shortUrl = urlService.longToShort(link);
            String longUrl = urlService.shortToLong(shortUrl);
            assertEquals(link, longUrl, "Failed at value: " + link);
        }
    }
}
