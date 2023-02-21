package com.dmly.reactive.webflux.service;

import com.dmly.reactive.webflux.model.Greeting;
import com.dmly.reactive.webflux.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GreetingServiceImpl implements GreetingService {

    private final GreetingRepository repository;

    @Autowired
    public GreetingServiceImpl(GreetingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Greeting> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Greeting> save(Greeting greeting) {
        return repository.save(greeting);
    }
}
