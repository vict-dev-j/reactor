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
@RequestMapping("/user-accounts")
@AllArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/create")
    public Mono<ResponseEntity<UserAccount>> createUserAccount(@Valid @RequestBody UserAccount userAccount) {
        return userAccountService.saveUserAccount(userAccount)
                .map(savedUser -> ResponseEntity.status(HttpStatus.CREATED).body(savedUser))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @GetMapping("/all")
    public Flux<UserAccount> getAllUserAccounts() {
        return userAccountService.getAllUserAccount();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserAccount>> getUserById(@PathVariable String id) {
        return userAccountService.getUserAccountById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserAccount>> updateUser(@PathVariable String id, @Valid @RequestBody UserAccount userAccount) {
        return userAccountService.updateUserAccount(id, userAccount)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteUser(@PathVariable String id) {
        return userAccountService.deleteUserAccount(id)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
    }

}
