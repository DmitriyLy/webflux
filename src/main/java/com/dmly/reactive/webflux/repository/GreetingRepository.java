package com.dmly.reactive.webflux.repository;

import com.dmly.reactive.webflux.model.Greeting;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends R2dbcRepository<Greeting, Long> {
}
