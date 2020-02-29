package web.boot.action.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author z
 */
@Configuration
public class RabbitConfig {
    public static final String DIRECT_QUEUE = "direct.queue";
    public static final String TOPIC_QUEUE_MEMBER = "member.queue";
    public static final String TOPIC_QUEUE_GOODS = "goods.queue";
    public static final String TOPIC_EXCHANGE = "topic.exchange";
    public static final String FANOUT_QUEUE = "fanout.queue";
    public static final String FANOUT_QUEUE_1 = "fanout.queue1";
    public static final String FANOUT_EXCHANGE = "fanout.exchange";

    @Bean
    public Queue directQueue() {
        return new Queue("direct.queue", true);
    }

    @Bean
    public Queue topicMemberQueue() {
        return new Queue(TOPIC_QUEUE_MEMBER);
    }

    @Bean
    public Queue topicGoodsQueue() {
        return new Queue(TOPIC_QUEUE_GOODS);
    }

    @Bean
    public Queue fanoutQueue() { return new Queue(FANOUT_QUEUE); }

    @Bean Queue fanoutQueue1() { return new Queue(FANOUT_QUEUE_1); }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchange() { return new FanoutExchange(FANOUT_EXCHANGE); }

    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(topicMemberQueue()).to(topicExchange()).with("*.member.*");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicGoodsQueue()).to(topicExchange()).with("*.*.goods");
    }

    @Bean
    public Binding fanoutBindingQueue() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBindingQueue1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }
}
