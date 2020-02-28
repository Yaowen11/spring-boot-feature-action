package web.boot.action.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.boot.action.config.RabbitDirectConfig;
import web.boot.action.entity.Goods;

/**
 * @author z
 */
@Component
public class DirectSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDirectQueue(Goods value) {
        amqpTemplate.convertAndSend(RabbitDirectConfig.DIRECT_QUEUE, value);
    }

}
