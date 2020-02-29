package web.boot.action.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.boot.action.config.Algorithm;
import web.boot.action.config.RabbitConfig;
import web.boot.action.entity.Goods;
import web.boot.action.entity.Member;
import web.boot.action.repository.GoodsRepository;
import web.boot.action.repository.MemberRepository;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author z
 */
@Component
public class RabbitReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitReceiver.class);

    private static Random random = new Random();

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    private MemberRepository memberRepository;

    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE)
    public void receiverDirectQueue(Goods value) {
        goodsRepository.save(value);
        LOGGER.debug("receiver direct queue: " + RabbitConfig.DIRECT_QUEUE + " value class: "
                + value.getClass() + "value info: " + value);
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE_MEMBER)
    public void receiveTopicMember(Member member) {
        HashString hashString = new HashString();
        try {
            member.setPassword(hashString.StringHashHex(member.getPassword(), Algorithm.SHA256));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.info(e.getMessage());
        }
        memberRepository.save(member);
        LOGGER.debug("receiver topic queue: " + RabbitConfig.TOPIC_QUEUE_MEMBER + " value class: "
            + member.getClass() + " value info: " + member);
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE_GOODS)
    public void receiveTopicGoods(Goods goods) {
        goods.setPrice(random.nextDouble());
        goodsRepository.save(goods);
        LOGGER.debug("receiver topic queue: " + RabbitConfig.TOPIC_QUEUE_GOODS + " value class: "
            + goods.getClass() + " value info: " + goods);
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE)
    public void receiveFanoutGoods(Goods goods) {
        goods.setPrice(random.nextDouble() * 100);
        goodsRepository.save(goods);
        LOGGER.debug("receiver fanout queue: " + RabbitConfig.FANOUT_QUEUE + " value class: " + goods.getClass()
            + " value info: " + goods);
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_1)
    public void receiveFanoutQueue(Goods goods) {
        goods.setPrice(random.nextDouble() * 10);
        goodsRepository.save(goods);
        LOGGER.debug("receiver fanout queue: " + RabbitConfig.FANOUT_QUEUE_1 + " value class: " + goods.getClass()
                + " value info: " + goods);
    }
}
