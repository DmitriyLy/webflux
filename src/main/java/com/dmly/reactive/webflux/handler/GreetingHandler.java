package com.dmly.reactive.webflux.handler;

import com.dmly.reactive.webflux.model.Greeting;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        var inserter = BodyInserters.fromValue(new Greeting("Hello, Spring!"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(inserter);
    }

    public Mono<ServerResponse> hellos(ServerRequest request) {

        String start = request.queryParam("start").orElse("0");
        String count = request.queryParam("count").orElse("10");

        Flux<Greeting> greetingFlux = Flux
                .just(
                        new Greeting("One"),
                        new Greeting("Two"),
                        new Greeting("Three"),
                        new Greeting("Four"),
                        new Greeting("Five"),
                        new Greeting("Six")

                )
                .skip(Long.parseLong(start))
                .take(Long.parseLong(count));

        var inserter = BodyInserters.fromProducer(
                greetingFlux,
                Greeting.class
        );

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(inserter);

    }
}
