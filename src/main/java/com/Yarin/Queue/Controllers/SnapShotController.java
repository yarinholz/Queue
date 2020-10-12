package com.Yarin.Queue.Controllers;

import com.Yarin.Queue.Dao.Item;
import com.Yarin.Queue.Services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SnapShotController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/snapshot")
    List<String> snapShot(){
        QueryService queryService = new QueryService(mongoTemplate);
        return queryService.getAllItems().stream().map(Item::getValue).collect(Collectors.toList());
    }

}
