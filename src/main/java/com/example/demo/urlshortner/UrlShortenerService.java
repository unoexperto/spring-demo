package com.example.demo.urlshortner;

public interface UrlShortenerService {
    String shortenUrl(String longUrl);
    String expandUrl(long shortId);
}
