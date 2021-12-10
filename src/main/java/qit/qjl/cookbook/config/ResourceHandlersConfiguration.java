package qit.qjl.cookbook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import qit.qjl.cookbook.common.Content;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Configuration
public class ResourceHandlersConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + Content.IMG_SAVE_ADDRESS);
    }



}
