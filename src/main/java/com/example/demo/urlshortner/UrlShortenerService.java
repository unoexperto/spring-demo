package com.example.demo.urlshortner;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Service
public class UrlShortenerService {

    @GetMapping
    public UrlShortner getShort() {
        return
                new UrlShortner("https://www.imdb.com/title/tt8690918/episodes",
        "http://localhost/u/924");
    }
}