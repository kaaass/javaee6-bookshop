package net.kaaass.bookshop.service.mq;

import lombok.extern.slf4j.Slf4j;
import net.kaaass.bookshop.util.Constants;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
@Slf4j
public class OrderMessageProducer {

    @Resource(mappedName = "java:/ConnectionFactory")
    ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/" + Constants.TOPIC_DESTINATION)
    Destination destination;

    public void sendMessage(String key, String content) {
        log.info("订单消息发送 {} = {}", key, content);

        Connection connection;
        try {
            connection = connectionFactory.createConnection();
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
