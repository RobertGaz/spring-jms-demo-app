package com.mdrsolutions.SpringJmsExample.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

// этот класс реализует JMS интерфейс
@Component
public class BookOrderProcessingMessageListener implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookOrderProcessingMessageListener.class);

    public BookOrderProcessingMessageListener() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            String text = ((TextMessage) message).getText();
            System.out.println("Received Book Order Processed Message: " + text);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
