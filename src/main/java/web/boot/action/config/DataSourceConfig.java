package web.boot.action.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @author z
 */
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(Environment environment) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        hikariDataSource.setUsername(environment.getProperty("spring.datasource.username"));
        hikariDataSource.setPassword(environment.getProperty("spring.datasource.password"));
        hikariDataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        return hikariDataSource;
    }
}
