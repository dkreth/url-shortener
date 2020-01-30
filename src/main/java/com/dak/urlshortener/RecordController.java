package com.dak.urlshortener;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {

    HashMap<String, String> map = new HashMap<>(); //should this go in UrlShortenerApplication? What goes there?

    @GetMapping("test")
    public Record testPath(){
        return new Record("test","test");
    }

    @GetMapping("/generate")
    public Record generate(@RequestParam(name = "longUrl") String longUrl , @RequestParam(name = "shortId") String shortId) {
        return new Record(shortId,longUrl);
    }

}
