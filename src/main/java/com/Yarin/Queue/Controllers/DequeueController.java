package com.Yarin.Queue.Controllers;

import com.Yarin.Queue.Dao.Item;
import com.Yarin.Queue.Dao.QueueStats;
import com.Yarin.Queue.Services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.bind.ValidationException;

@RestController
public class DequeueController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/dequeue")
    String dequeue() throws ValidationException {
        QueueStats stats = QueueStats.getInstance();
        if (stats.getSize() > 0) {
            QueryService queryService = new QueryService(mongoTemplate);
            stats.decreaseSize();
            return queryService.dequeue().getValue();
        }
        else{
            throw new ValidationException("Queue is empty");
        }

    }
    @ExceptionHandler(ValidationException.class)
    ResponseEntity<String> exceptionHandler(ValidationException e){
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }



}