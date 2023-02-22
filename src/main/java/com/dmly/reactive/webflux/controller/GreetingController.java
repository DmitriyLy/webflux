package com.dmly.reactive.webflux.controller;

import com.dmly.reactive.webflux.model.Greeting;
import com.dmly.reactive.webflux.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "greeting")
public class GreetingController {

    private final GreetingService service;

    @Autowired
    public GreetingController(GreetingService service) {
        this.service = service;
    }

    @GetMapping(path = "find-all")
    public Flux<Greeting> findAll(@RequestParam(defaultValue = "0") Long start,
                                  @RequestParam(defaultValue = "10") Long count) {
        return service.findAll();
    }

    @PostMapping(path = "save")
    public Mono<Greeting> save(@RequestBody Greeting greeting) {
        return service.save(greeting);
    }
}
