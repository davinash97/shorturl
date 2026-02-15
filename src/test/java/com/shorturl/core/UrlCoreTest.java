package com.shorturl.core;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class UrlCoreTest {

    @Test
    void testEncodeDecodeRoundTrip() {
        for (long i = 0; i < 1_000_000; i++) {
            long decoded = UrlCore.decode(UrlCore.encode(i));
            assertEquals(i, decoded, "Failed at value: " + i);
        }
    }

    @Test
    void testEdgeValues() {
        long[] values = {
                0,
                1,
                61,
                62,
                63,
                1000,
                Long.MAX_VALUE
        };

        for (long value : values) {
            assertEquals(value,
                    UrlCore.decode(UrlCore.encode(value)),
                    "Failed at value: " + value);
        }
    }

    @Test
    void testRandomValues() {
        for (int i = 0; i < 100_000; i++) {
            long value = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
            assertEquals(value,
                    UrlCore.decode(UrlCore.encode(value)));
        }
    }

}
