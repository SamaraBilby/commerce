package com.commerce.orderflow.services;

import com.commerce.orderflow.dtos.RegisterUserRequest;
import com.commerce.orderflow.entities.User;
import com.commerce.orderflow.enums.UserRole;
import com.commerce.orderflow.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UUID createUser(RegisterUserRequest request) {

        if(userRepository.existsByEmail(request.email())) {
            log.warn("User registration attempt with existing email {}", request.email());
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        String passwordHash = passwordEncoder.encode(request.password());

        User user = new User(
                request.name(),
                request.email(),
                passwordHash,
                UserRole.CUSTOMER
        );

        User savedUser = userRepository.save(user);

        log.info("User created successfully with id {}", savedUser.getId());

        return savedUser.getId();
    }
}
