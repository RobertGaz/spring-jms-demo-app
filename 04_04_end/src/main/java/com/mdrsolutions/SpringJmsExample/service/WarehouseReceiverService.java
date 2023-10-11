package com.mdrsolutions.SpringJmsExample.service;

import com.mdrsolutions.SpringJmsExample.model.BookOrder;
import com.mdrsolutions.SpringJmsExample.model.ProcessedBookOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class WarehouseReceiverService {
    public static final String BOOK_QUEUE = "book.order.queue";
    private static final Logger LOGGER = LoggerFactory.getLogger("Warehouse");
    public static final String PROCESSED_ORDER_QUEUE = "processed.order.queue";

    @Autowired
    private WarehouseProcessingService warehouseProcessingService;

    @JmsListener(destination = BOOK_QUEUE)
    public JmsResponse<Message<ProcessedBookOrder>> receiveAndSend(@Payload BookOrder bookOrder,
                                                                  @Header(name = "storeId") String storeId,
                                                                  @Header(name = "bookOrderId") String bookOrderId,
                                                                  MessageHeaders messageHeaders) {
        LOGGER.info("Received order {} from store {}, message headers: {}", bookOrderId, storeId, messageHeaders);
        ProcessedBookOrder processedOrder = warehouseProcessingService.processOrder(bookOrder);
        LOGGER.info("Processed the order, sending further");

        Message<ProcessedBookOrder> message = MessageBuilder
                .withPayload(processedOrder)
                .setHeader("storeId", storeId)
                .setHeader("orderId", bookOrderId)
                .build();

        if ("jp-2345".equals(bookOrder.getCustomer().getCustomerId())) {
            return JmsResponse.forQueue(message, "cancelled.queue");
        }


        return JmsResponse.forQueue(message, PROCESSED_ORDER_QUEUE);
    }


    @JmsListener(destination = BOOK_QUEUE)
    public void receive2(@Payload BookOrder bookOrder,
                                                                  @Header(name = "storeId") String storeId,
                                                                  @Header(name = "bookOrderId") String bookOrderId,
                                                                  MessageHeaders messageHeaders) {
        LOGGER.info("222 KEEEK 222 Received order {} from store {}, message headers: {}", bookOrderId, storeId, messageHeaders);

    }

    @JmsListener(destination = BOOK_QUEUE)
    public void receive3(@Payload BookOrder bookOrder,
                         @Header(name = "storeId") String storeId,
                         @Header(name = "bookOrderId") String bookOrderId,
                         MessageHeaders messageHeaders) {
        LOGGER.info("333 KEEEK 333 Received order {} from store {}, message headers: {}", bookOrderId, storeId, messageHeaders);

    }

    @JmsListener(destination = BOOK_QUEUE)
    public void receive4(@Payload BookOrder bookOrder,
                         @Header(name = "storeId") String storeId,
                         @Header(name = "bookOrderId") String bookOrderId,
                         MessageHeaders messageHeaders) {
        LOGGER.info("444 KEEEK 444 Received order {} from store {}, message headers: {}", bookOrderId, storeId, messageHeaders);

    }

//    @JmsListener(destination = BOOK_QUEUE)
//    @SendTo("SISKI")
//    public Message<ProcessedBookOrder> receiveAndSend(@Payload BookOrder bookOrder,
//                                                     @Header(name = "storeId") String storeId,
//                                                     @Header(name = "bookOrderId") String bookOrderId,
//                                                     MessageHeaders messageHeaders) {
//        LOGGER.info("Received order {} from store {}, message headers: {}", bookOrderId, storeId, messageHeaders);
//        ProcessedBookOrder processedOrder = warehouseProcessingService.processOrder(bookOrder);
//        LOGGER.info("Processed the order, sending further");
//
//        return MessageBuilder
//                .withPayload(processedOrder)
//                .setHeader("storeId", storeId)
//                .setHeader("orderId", bookOrderId)
//                .build();
//    }

//    @JmsListener(destination = BOOK_QUEUE)
//    @SendTo("SISKI")
//    public ProcessedBookOrder receiveAndSend(@Payload BookOrder bookOrder,
//                        @Header(name = "storeId") String storeId,
//                        @Header(name = "bookOrderId") String bookOrderId,
//                        MessageHeaders messageHeaders) {
//        LOGGER.info("Received order {} from store {}, message headers: {}", bookOrderId, storeId, messageHeaders);
//        ProcessedBookOrder processedOrder = warehouseProcessingService.processOrder(bookOrder);
//        LOGGER.info("Processed the order, sending further");
//        return processedOrder;
//    }

//    @JmsListener(destination = BOOK_QUEUE)
//    public void receive(@Payload BookOrder bookOrder,
//                        @Header(name = "storeId") String storeId,
//                        @Header(name = "bookOrderId") String bookOrderId,
//                        MessageHeaders messageHeaders) {
//        LOGGER.info("Received order {} from store {}, message headers: {}", bookOrderId, storeId, messageHeaders);
//        if (bookOrder.getBook().getTitle().startsWith("L")) {
//            throw new RuntimeException("no books left starting with 'L");
//        }
//        LOGGER.info("Book order " + bookOrder.getBookOrderId() + " is on warehouse now.");
//        warehouseProcessingService.processOrder(bookOrder);
//    }
}
