package com.example.demo.urlshortner;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

interface UrlShortenerService {
    Long shortenUrl(String longUrl);
    String expandUrl(long shortId);
}

@Service
public class MemoryUrlShortenerService implements UrlShortenerService {

    private long previousId = 0L;

    private Map<String, Long> storage = new HashMap<>();
    private Map<Long, String> storageReverse = new HashMap<>();

    // Long must be positive
    // Same call for the same URL must return same ID
    // Method must never fail
    public Long shortenUrl(String longUrl) {

        Long existingId = storage.get(longUrl);

        if (existingId == null) {

            previousId = previousId + 1;
            storage.put(longUrl, previousId);
            storageReverse.put(previousId, longUrl);
            return previousId;
        } else
            return existingId;
    }

    public String expandUrl(long shortId) {

        return storageReverse.get(shortId);
    }
}