package com.example.kndemo.auth.yaml.repository;

import com.example.kndemo.auth.repository.UserRepository;
import com.example.kndemo.auth.yaml.config.AuthConfigurationProperties;
import com.example.kndemo.auth.yaml.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class YamlRepository implements UserRepository {

    private Map<String, User> users;


    public YamlRepository(AuthConfigurationProperties properties) {
        users = new HashMap<>();
        for (var user: properties.getUsers()) {
            users.put(user.getLogin(), user);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(users.get(login));
    }
}
