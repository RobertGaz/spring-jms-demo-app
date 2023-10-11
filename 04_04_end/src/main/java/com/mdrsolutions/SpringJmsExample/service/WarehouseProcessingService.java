package com.mdrsolutions.SpringJmsExample.service;

import com.mdrsolutions.SpringJmsExample.model.BookOrder;
import com.mdrsolutions.SpringJmsExample.model.ProcessedBookOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WarehouseProcessingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseProcessingService.class);


    public ProcessedBookOrder processOrder(BookOrder bookOrder) {
        ProcessedBookOrder order = new ProcessedBookOrder(bookOrder, new Date(), new Date());
        return order;
    }

}
