package com.quick.start.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ApiGlobalFilter implements GlobalFilter,Ordered {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApiGlobalFilter.class);

    /**
     * 请求头属性，如token
     */
    private String headerString = "";

    /**
     * 实现自定义拦截逻辑
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUri = exchange.getRequest().getURI().getPath();

        LOGGER.info("校验uri {}|{}", requestUri);
        if(isStartWith(requestUri) || isContains(requestUri)){
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst(headerString);
        ///TODO 请求认证逻辑，如token认证，登录状态校验，权限认证等

        return chain.filter(exchange);
    }

    /**
     * 拦截器执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 是否包含某种特征
     * @param requestUri
     * @return
     */
    private boolean isContains(String requestUri) {
        ///TODO 过滤url逻辑

        return true;
    }

    /**
     * URI是否以什么打头
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        ///TODO 过滤url逻辑

        return true;
    }
}
