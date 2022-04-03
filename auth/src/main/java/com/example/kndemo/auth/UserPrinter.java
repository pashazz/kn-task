package com.example.kndemo.auth;

import com.example.kndemo.auth.yaml.config.AuthConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserPrinter {

    @Autowired
    private AuthConfigurationProperties properties;

    @PostConstruct
    void init() {
        for (var user: properties.getUsers()) {
            System.out.println("user: " + user.getLogin() +
                    " password: " + user.getPassword());
        }
    }
}
