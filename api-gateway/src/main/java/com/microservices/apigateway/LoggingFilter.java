package com.microservices.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// GlobalFilter - intercepts all requests through gateway
// Runs for every request automatically - used for logging, auth, rate limiting
@Component
public class LoggingFilter implements GlobalFilter, Ordered{

    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        // PRE filter - runs before request reaches microservices
        logger.info("PRE FILTER executed");
        logger.info("Request Path: " + exchange.getRequest().getPath());

        // chain.filter() passes request to the microservices
        // passes request forward - without this, request never reaches the service
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // POST filter - runs after response comes back
            logger.info("Post FILTER executed");
            logger.info("response status: " + exchange.getResponse().getStatusCode());
        }));
    }

    @Override
    public int getOrder(){
        // runs first before any other filter
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
