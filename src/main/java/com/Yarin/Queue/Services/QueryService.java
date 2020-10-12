package com.Yarin.Queue.Services;

import com.Yarin.Queue.Dao.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Service
public class QueryService {

    private MongoTemplate mongoTemplate;

    public QueryService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public long getSize() {
        return this.mongoTemplate.getCollection("Items").countDocuments();
    }

    public int getNextId() {
        Query query = new Query();
        query.limit(1);
        query.with(Sort.by(Sort.Direction.DESC, "id"));
        Item item = mongoTemplate.findOne(query, Item.class);
        return item.getId() + 1;
    }

    public List<Item> getAllItems() {
        return this.mongoTemplate.findAll(Item.class);
    }

    public void insertItem(Item item) {
        mongoTemplate.insert(item);
    }

    public Item dequeue() {
        Query query = new Query();
        query.limit(1);
        query.with(Sort.by(Sort.Direction.ASC, "id"));
        List<Item> list = mongoTemplate.findAllAndRemove(query, Item.class);
        return list.get(0);

    }

}
