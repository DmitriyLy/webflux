package com.dmly.reactive.webflux.client;

import com.dmly.reactive.webflux.model.Greeting;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GreetingClient {

    private WebClient webClient;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private WebClient.Builder builder;

    @PostConstruct
    public void init() {
        webClient = builder.baseUrl("http://localhost:" + port).build();
    }

    public Mono<String> getMessage() {
        return webClient.get()
                .uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage);
    }
}
