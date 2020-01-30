package com.dak.urlshortener;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;

@RestController
public class RecordController {

    HashMap<String, String> map = new HashMap<>(); //should this go in UrlShortenerApplication? What goes there?

    @GetMapping("test")
    public Record testPath(){
        return new Record("test","test");
    }

    @GetMapping("/generate")
    public Record generate(@RequestParam(name = "longUrl") String longUrl , @RequestParam(name = "shortId") String shortId) {
        map.put(shortId,longUrl);
        return new Record(shortId,longUrl);
    }

    @GetMapping("/{shortId}")
    public RedirectView redirect(RedirectAttributes attributes, @PathVariable String shortId) {
        return new RedirectView("http://"+map.get(shortId));
    }
}
