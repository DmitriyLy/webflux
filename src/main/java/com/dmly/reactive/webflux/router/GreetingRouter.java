package com.dmly.reactive.webflux.router;

import com.dmly.reactive.webflux.handler.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        return RouterFunctions.route()
                .GET("/hello", accept(MediaType.APPLICATION_JSON), greetingHandler::hello)
                .GET("/hellos", accept(MediaType.APPLICATION_JSON), greetingHandler::hellos)
                .build();
    }

}
