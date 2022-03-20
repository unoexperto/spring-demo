package com.example.demo.urlshortner;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Primary
@Service
public class DbUrlShortenerService implements UrlShortenerService {

    private long previousId = 0L;

    private Map<String, Long> storage = new HashMap<>();
    private Map<Long, String> storageReverse = new HashMap<>();

    // Long must be positive
    // Same call for the same URL must return same ID
    // Method must never fail
    public String shortenUrl(String longUrl) {

        Long existingId = storage.get(longUrl);

        if (existingId == null) {

            previousId = previousId + 1;
            storage.put(longUrl, previousId);
            storageReverse.put(previousId, longUrl);
            return String.valueOf(previousId);
        } else
            return String.valueOf(existingId);
    }

    public String expandUrl(long shortId) {

        return storageReverse.get(shortId);
    }
}