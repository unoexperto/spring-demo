package com.example.demo.urlshortner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;

import javax.xml.stream.Location;
import java.util.Map;

@RestController
@RequestMapping
public class UrlShortnerController {

    private final String baseUrl = "http://localhost:8080";
    private final UrlShortenerService urlShortenerService;

    @Autowired
    public UrlShortnerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping(path = "url/shorten")
    public UrlShortner shortenUrl(@RequestParam("longUrl") String longUrl) {

        long shortId = urlShortenerService.shortenUrl(longUrl);
        return new UrlShortner(longUrl, baseUrl + "/u/" + shortId);
    }

    @GetMapping(path = "url/expand")
    public UrlShortner expandUrl(@RequestParam("shortId") long shortId) {

        String longUrl = urlShortenerService.expandUrl(shortId);
        if (longUrl != null)
            return new UrlShortner(longUrl, baseUrl + "/u/" + shortId);
        else
            throw new RuntimeException("Cannot find URL with ID " + shortId);
    }

    @GetMapping(path = "u/{id}")
    public ResponseEntity<?> redirecting(@PathVariable("id") long shortId) {

        String longUrl = urlShortenerService.expandUrl(shortId);
        if (longUrl != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", longUrl);
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        } else
            throw new RuntimeException("Cannot find URL with ID " + shortId);
    }
}

