package net.kaaass.bookshop.service.mq;

import net.kaaass.bookshop.util.Constants;
import org.slf4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class OrderMessageProducer {

    private static final String DEFAULT_USERNAME = "testJNDI";
    private static final String DEFAULT_PASSWORD = "123456";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderMessageProducer.class);

    @Resource(mappedName = "java:/ConnectionFactory")
    ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/" + Constants.TOPIC_DESTINATION)
    Destination destination;

    public void sendMessage(String key, String content) {
        log.info("订单消息发送 {} = {}", key, content);

        Connection connection;
        try {
            connection = connectionFactory.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD);
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            connection.start();
            Message message = session.createTextMessage(content);
            producer.send(message);
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
