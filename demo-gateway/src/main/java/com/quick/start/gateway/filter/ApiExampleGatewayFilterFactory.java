package com.quick.start.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ApiExampleGatewayFilterFactory extends AbstractGatewayFilterFactory<ApiExampleGatewayFilterFactory.Config> implements Ordered {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApiExampleGatewayFilterFactory.class);


    public ApiExampleGatewayFilterFactory(){
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    private String headerString = "";

    /**
     * 实现过滤逻辑
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest().mutate()
                    .build();

            String requestUri = request.getURI().getPath();
            String headerAttrValue = exchange.getRequest().getHeaders().getFirst(headerString);
            if(config.isEnabled()){
                LOGGER.info("请求处理 {}|{}", requestUri, headerAttrValue);
                ///TODO 自定义请求过滤逻辑
            }

            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    public static class Config {
        // 控制是否使用当前过滤器
        private boolean enabled;

        public Config() {}

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
