package web.boot.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.boot.action.config.MysqlConfigBean;

/**
 * @author z
 */
@RestController
@EnableConfigurationProperties({MysqlConfigBean.class})
public class ConfigController {
    @Value("${author}")
    private String author;
    @Value("${app.id}")
    private String uuid;
    @Value("${app.value}")
    private String value;
    @Value("${app.secret}")
    private String secret;
    @Value("${app.upload.path}")
    private String upload;
    @GetMapping("/config/app")
    public Object config() {
        return new Object() {
            public String author = ConfigController.this.author;
            public String uuid = ConfigController.this.uuid;
            public String value = ConfigController.this.value;
            public String secret = ConfigController.this.secret;
            public String upload = ConfigController.this.upload;
        };
    }
    @GetMapping("/config/mysql")
    public MysqlConfigBean mysqlConfig(@Autowired MysqlConfigBean mysqlConfigBean) {
        return mysqlConfigBean;
    }
}
