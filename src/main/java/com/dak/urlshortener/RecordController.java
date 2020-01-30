package com.dak.urlshortener;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {
    @GetMapping("/generate")
    public Record generate(@RequestParam(name = "longUrl") String longUrl , @RequestParam(name = "shortId") String shortId) {
        return new Record(shortId,longUrl);
    }

}
