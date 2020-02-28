package web.boot.action.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author z
 */
@Configuration
public class RabbitDirectConfig {
    public static final String DIRECT_QUEUE = "direct.queue";
    @Bean
    public Queue directQueue() {
        return new Queue("direct.queue", true);
    }
}
