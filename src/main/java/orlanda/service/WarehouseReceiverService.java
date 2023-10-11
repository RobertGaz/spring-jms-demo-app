package orlanda.service;

import com.mdrsolutions.SpringJmsExample.model.BookOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class WarehouseReceiverService {
    public static final String BOOK_QUEUE = "book.order.queue";
    private static final Logger LOGGER = LoggerFactory.getLogger("Warehouse");

    @Autowired
    private WarehouseProcessingService warehouseProcessingService;

    @JmsListener(destination = BOOK_QUEUE)
    public void receive(@Payload BookOrder bookOrder,
                        @Header(name = "storeId") String storeId,
                        @Header(name = "bookOrderId") String bookOrderId,
                        MessageHeaders messageHeaders) {
        LOGGER.info("Received order {} from store {}, message headers: {}", bookOrderId, storeId, messageHeaders);
        if (bookOrder.getBook().getTitle().startsWith("L")) {
            throw new RuntimeException("no books left starting with 'L");
        }
        LOGGER.info("Book order " + bookOrder.getBookOrderId() + " is on warehouse now.");
        warehouseProcessingService.processOrder(bookOrder);
    }
}
