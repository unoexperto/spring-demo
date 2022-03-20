package com.example.demo.urlshortner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UrlShortnerController {

//    private final String baseUrl = System.getenv().get("BASE_URL"); // "http://localhost:8080";
    private final String baseUrl = "http://localhost:8080";
    private final UrlShortenerService urlShortenerService;

    @Autowired
    public UrlShortnerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping(path = "url/shorten")
    public UrlInfo shortenUrl(@RequestParam("longUrl") String longUrl) {

        String shortId = urlShortenerService.shortenUrl(longUrl);


        // https://ru.wikipedia.org/wiki/%D0%95%D0%B2%D1%80%D0%B0%D0%B7%D0%B8%D0%B9%D1%81%D1%82%D0%B2%D0%BE#:~:text=%D0%95%D0%B2%D1%80%D0%B0%D0%B7%D0%B8%CC%81%D0%B9%D1%81%D1%82%D0%B2%D0%BE%20%E2%80%94%20%D0%BF%D0%B5%D1%80%D0%B2%D0%BE%D0%BD%D0%B0%D1%87%D0%B0%D0%BB%D1%8C%D0%BD%D0%BE%20%D0%B8%D0%B4%D0%B5%D0%B9%D0%BD%D0%BE%2D%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%BE%D0%B7%D0%B7%D1%80%D0%B5%D0%BD%D1%87%D0%B5%D1%81%D0%BA%D0%BE%D0%B5%2C,%D0%BE%D0%B1%D1%8A%D0%B5%D0%B4%D0%B8%D0%BD%D0%B8%D0%B2%D1%88%D0%B5%D0%B9%20%D1%8D%D0%BB%D0%B5%D0%BC%D0%B5%D0%BD%D1%82%D1%8B%20%D0%92%D0%BE%D1%81%D1%82%D0%BE%D0%BA%D0%B0%20%D0%B8%20%D0%97%D0%B0%D0%BF%D0%B0%D0%B4%D0%B0%2C
        // http://localhost:8080/u/1
        return new UrlInfo(longUrl, baseUrl + "/u/" + shortId);
    }

    @GetMapping(path = "url/expand")
    public UrlInfo expandUrl(@RequestParam("shortId") long shortId) {

        String longUrl = urlShortenerService.expandUrl(shortId);
        if (longUrl != null)
            return new UrlInfo(longUrl, baseUrl + "/u/" + shortId);
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

