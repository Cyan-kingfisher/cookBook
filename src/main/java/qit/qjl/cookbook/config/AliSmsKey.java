package qit.qjl.cookbook.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author: Cyan-K
 * @date: 2021/5/27
 */
@Component
@ConfigurationProperties(value = "ali")
@PropertySource(value = {"classpath:/application-dev.properties"})
public class AliSmsKey {

    private String access;
    private String secret;

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public AliSmsKey() {
    }
}
