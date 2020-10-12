package com.Yarin.Queue.Dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Items")
public class Item {
    @Id
    @Indexed
    final private int id;
    final private String value;


    public Item(int id,String value) {
        this.id = id;
        this.value=value;
    }


    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
