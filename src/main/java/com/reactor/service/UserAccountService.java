package com.reactor.service;

import com.reactor.model.UserAccount;
import com.reactor.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public Mono<UserAccount> saveUserAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public Flux<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }

    public Mono<UserAccount> getUserById(String id) {
        return userAccountRepository.findById(id);
    }

    public Mono<UserAccount> updateUser(String id, UserAccount customer) {
        return userAccountRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setName(customer.getName());
                    existingUser.setEmail(customer.getEmail());
                    return userAccountRepository.save(existingUser);
                });
    }

    public Mono<Void> deleteUser(String id) {
        return userAccountRepository.deleteById(id)
                .onErrorResume(e -> Mono.empty());
    }

}
