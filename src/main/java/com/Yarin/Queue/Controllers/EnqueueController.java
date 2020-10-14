package com.Yarin.Queue.Controllers;

import com.Yarin.Queue.Dao.Item;
import com.Yarin.Queue.Dao.QueueState;
import com.Yarin.Queue.Services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public  class EnqueueController {
    @Autowired
    private MongoTemplate mongoTemplate;
    @PostMapping(value = "/enqueue", consumes = "application/json", produces = "application/json")
    Item enqueue(@RequestBody String s){
        QueryService queryService = new QueryService(mongoTemplate);
        QueueState stats = QueueState.getInstance();
        int id = stats.getNextId();
        Item item = new Item(id,s);
        queryService.insertItem(item);
        stats.increaseId();
        stats.increaseSize();
        return item;
    }
}
