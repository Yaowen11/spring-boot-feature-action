package web.boot.action.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.boot.action.config.RabbitDirectConfig;
import web.boot.action.entity.Goods;
import web.boot.action.repository.GoodsRepository;

/**
 * @author z
 */
@Component
public class DirectReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectReceiver.class);

    @Autowired
    GoodsRepository goodsRepository;

    @RabbitListener(queues = RabbitDirectConfig.DIRECT_QUEUE)
    public void receiverDirectQueue(Goods value) {
        goodsRepository.save(value);
        LOGGER.info("value class: " + value.getClass() + "value info: " + value);
    }
}
