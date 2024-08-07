package com.reactor.controller;

import com.reactor.model.UserAccount;
import com.reactor.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping
    public Mono<ResponseEntity<UserAccount>> createCustomer(@Valid @RequestBody UserAccount userAccount) {
        return userAccountService.saveUserAccount(userAccount)
                .map(savedUser -> ResponseEntity.status(HttpStatus.CREATED).body(savedUser))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @GetMapping
    public Flux<UserAccount> getAllUsers() {
        return userAccountService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserAccount>> getUserById(@PathVariable String id) {
        return userAccountService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserAccount>> updateUser(@PathVariable String id, @Valid @RequestBody UserAccount userAccount) {
        return userAccountService.updateUser(id, userAccount)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteUser(@PathVariable String id) {
        return userAccountService.deleteUser(id)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
    }

}
