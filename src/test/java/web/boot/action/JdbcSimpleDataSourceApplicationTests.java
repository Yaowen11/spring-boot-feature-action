package web.boot.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcSimpleDataSourceApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Test
    public void sprintDataSourceTest() {
        System.out.println(dataSource.getClass());
        try {
            Connection mysql = dataSource.getConnection();
            System.out.println(mysql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
