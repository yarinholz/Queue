package com.Yarin.Queue;

import com.Yarin.Queue.Dao.Item;
import com.Yarin.Queue.Dao.QueueState;
import com.Yarin.Queue.Services.QueryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueRunner implements CommandLineRunner {
    private MongoTemplate mongoTemplate;
    private QueryService queryService;

    public QueueRunner(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
        this.queryService = new QueryService(mongoTemplate);
    }

    @Override
    public void run(String... args)  {
        QueueState stats = QueueState.getInstance();
        long size=queryService.getSize();
        stats.setSize(size);
        if(size==0){
            stats.setNextId(1);
        }
        else {
            int nextId=queryService.getNextId();
            stats.setNextId(nextId);
        }
    }

    }