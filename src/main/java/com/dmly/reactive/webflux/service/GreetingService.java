package com.dmly.reactive.webflux.service;

import com.dmly.reactive.webflux.model.Greeting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GreetingService {
    Flux<Greeting> findAll();

    Mono<Greeting> save(Greeting greeting);
}
