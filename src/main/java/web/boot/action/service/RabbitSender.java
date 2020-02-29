package web.boot.action.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.boot.action.config.RabbitConfig;
import web.boot.action.entity.Goods;
import web.boot.action.entity.Member;

/**
 * @author z
 */
@Component
public class RabbitSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDirectQueue(Goods value) {
        amqpTemplate.convertAndSend(RabbitConfig.DIRECT_QUEUE, value);
        LOGGER.debug("send direct queue: " + RabbitConfig.DIRECT_QUEUE + " class: "
                + value.getClass() + " value: " + value);
    }

    public void sendTopicQueue(Object value) {
        String routeKey = "topic.queue.goods";
        if (value instanceof Member) {
            routeKey = "topic.member.queue";
        }
        this.amqpTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, routeKey, value);
        LOGGER.debug("send topic exchange: " + RabbitConfig.TOPIC_EXCHANGE + " route key: " + routeKey +
                " class: " + value.getClass() + " value: " + value);
    }

    public void sendFanoutQueue(Goods goods) {
        this.amqpTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE, "", goods);
        LOGGER.debug("send fanout exchange: " + RabbitConfig.FANOUT_EXCHANGE + " class: "
                + goods.getClass() + "value: " + goods);
    }
}
