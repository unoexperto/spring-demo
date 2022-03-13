package com.example.demo.urlshortner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="url")
public class UrlShortnerController {

    private final UrlShortenerService urlShortenerService;


    @Autowired
    public UrlShortnerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping
    public UrlShortner getShort() {
        return urlShortenerService.getShort();

}
    }

