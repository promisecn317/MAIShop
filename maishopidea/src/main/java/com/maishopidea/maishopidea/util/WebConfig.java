package com.maishopidea.maishopidea.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    //    这是要解决跨域问题(全局配置类)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://locathost")
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(false)
                .maxAge(3600);
    }
}
