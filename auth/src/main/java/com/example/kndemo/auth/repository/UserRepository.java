package com.example.kndemo.auth.repository;

import com.example.kndemo.auth.yaml.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByLogin(String login);
}
