package com.github.leopc17.inventorymanager.domain.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.github.leopc17.inventorymanager.domain.enums.UserRole;

public class User {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private final String name;
    private final String email;
    private final String passwordHash;
    private final UserRole role;

    public User(String name, String email, String rawPassword, UserRole role) {
        validate(name, email, rawPassword, role);
        this.name = name.trim();
        this.email = email.toLowerCase();
        this.passwordHash = ENCODER.encode(rawPassword);
        this.role = role;
    }

    public User(String name, String email, String passwordHash, UserRole role, boolean alreadyHashed) {
        validate(name, email, passwordHash, role);
        this.name = name.trim();
        this.email = email.toLowerCase();
        this.passwordHash = passwordHash;
        this.role = role;
    }

    private void validate(String name, String email, String password, UserRole role) {
        if (name == null || name.isBlank() || name.length() < 3) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email inválido.");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Senha inválida.");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role não pode ser nulo.");
        }
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public UserRole getRole() { return role; }

    public boolean checkPassword(String rawPassword) {
        return ENCODER.matches(rawPassword, this.passwordHash);
    }
}
