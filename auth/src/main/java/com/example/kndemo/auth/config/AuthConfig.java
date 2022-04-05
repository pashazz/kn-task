package com.example.kndemo.auth.config;

import com.example.kndemo.auth.yaml.entity.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConfigurationProperties("kndemo.auth")
@Configuration
@Data
public class AuthConfig {
    List<User> users;
    long tokenExpirationTimeMs = 3600000;
}
