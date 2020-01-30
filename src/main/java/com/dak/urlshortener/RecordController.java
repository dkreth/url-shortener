package com.dak.urlshortener;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;

@RestController
public class RecordController {

//    HashMap<String, String> map = new HashMap<>(); //should this go in UrlShortenerApplication? What goes there?

    MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://MyMongoDBUser:"+Password.pw+"@mycluster-shdpl.mongodb.net/test?retryWrites=true&w=majority");
    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("myDatabase");
    MongoCollection<Document> collection = database.getCollection("first set of urls");

    @GetMapping("test")
    public Record testPath(){
        return new Record("test","test");
    }

    @GetMapping("/generate")
    public Record generate(@RequestParam(name = "longUrl") String longUrl , @RequestParam(name = "shortId") String shortId) {
//        map.put(shortId,longUrl);
        //TODO don't allow duplicates
        Document record = new Document("shortId",shortId).append("longUrl",longUrl);
        collection.insertOne(record);
        return new Record(shortId,longUrl);
    }

    @GetMapping("/{shortId}")
    public RedirectView redirect(RedirectAttributes attributes, @PathVariable String shortId) { // TODO maybe make this a ModelAndView
        try {
//            if (map.containsKey(shortId)) {
//                return new RedirectView("http://" + map.get(shortId));
//            } else {
                String longUrl = collection.find(new Document("shortId", shortId)).first().getString("longUrl");
                return new RedirectView("http://" + longUrl);
//            }
        } catch (Exception e) {
                return new RedirectView("test"); //TODO redirect this to some error page?
        }
    }

}
