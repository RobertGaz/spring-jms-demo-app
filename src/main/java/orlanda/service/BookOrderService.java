package orlanda.service;

import com.mdrsolutions.SpringJmsExample.model.BookOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class BookOrderService {

    public static final String BOOK_QUEUE = "book.order.queue";

    @Autowired
    JmsTemplate jmsTemplate;

    @Transactional
    public void send(BookOrder bookOrder, String storeId) {

        jmsTemplate.convertAndSend(BOOK_QUEUE, bookOrder, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setStringProperty("bookOrderId", bookOrder.getBookOrderId());
                message.setStringProperty("storeId", storeId);

                return message;
            }
        });
    }

}
