package bookshop.service.mq;

import bookshop.service.OrderRequestContext;
import bookshop.service.OrderService;
import bookshop.util.Constants;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = Constants.TOPIC_DESTINATION),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "clientID", propertyValue = "consumer")
})
public class OrderMessageListener implements MessageListener {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderMessageListener.class);

    @EJB
    private OrderService orderService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(Message inMessage) {
        TextMessage msg;
        OrderRequestContext context = null;
        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                String content = msg.getText();
                //
                log.info("开始处理订单消息 {}", content);
                try {
                    context = this.objectMapper.readValue(content, OrderRequestContext.class);
                } catch (IOException e) {
                    log.warn("订单消息反序列化错误", e);
                }
                try {
                    orderService.doCreate(context);
                } catch (Exception e) {
                    log.warn("订单消息处理错误", e);
                }
            } else {
                log.warn("MDB 类型错误 {}", inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}
