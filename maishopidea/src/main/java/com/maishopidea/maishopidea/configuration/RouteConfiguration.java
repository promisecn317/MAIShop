package com.maishopidea.maishopidea.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
public class RouteConfiguration {
    private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client,Origin,No-Cache,X-Requested-With,If-Modified-Since,Pragma,Last-Modified,Cache-Control,Expires,Access-Control-Allow-Credentials,Biligame-Request-Id,request-game-id,request-game-base-id,request-account-domain";
    private static final String ALLOWED_METHODS = "*";
    private static final String ALLOWED_METHODS_TEST = "GET,POST,OPTIONS";

    private static final String ALLOWED_ORIGIN = "*";
    private static final String ALLOWED_EXPOSE = "*";
    private static final String MAX_AGE = "3600";

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {

                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();

                headers.set("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
                headers.add("Access-Control-Allow-Credentials", "true");

                headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
                //   "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client,
                //   Origin,No-Cache,X-Requested-With,If-Modified-Since,Pragma,Last-Modified,Cache-Control,Expires";
                headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
                //   配置为
                headers.add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
                //   配置为 3600
                //第一次是浏览器使用OPTIONS方法发起一个预检请求  每1800秒才会有一次
                headers.add("Access-Control-Max-Age", MAX_AGE);
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    //  配置为*
                    headers.set("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
                    headers.add("Access-Control-Allow-Credentials", "true");
                    //   配置为*
                    headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
                    headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
                    headers.add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
                    headers.add("Access-Control-Max-Age", MAX_AGE);
                    response.setStatusCode(HttpStatus.PARTIAL_CONTENT);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }

}
