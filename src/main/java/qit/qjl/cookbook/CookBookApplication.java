package qit.qjl.cookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties
public class CookBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookBookApplication.class, args);
    }

}
