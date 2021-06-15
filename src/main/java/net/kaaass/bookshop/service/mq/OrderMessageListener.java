package net.kaaass.bookshop.service.mq;

import lombok.extern.slf4j.Slf4j;
import net.kaaass.bookshop.service.OrderRequestContext;
import net.kaaass.bookshop.service.OrderService;
import net.kaaass.bookshop.util.Constants;
import org.codehaus.jackson.map.ObjectMapper;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
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
@Slf4j
public class OrderMessageListener implements MessageListener {

    @Inject
    private OrderService orderService;

    @Inject
    private ObjectMapper objectMapper;

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
