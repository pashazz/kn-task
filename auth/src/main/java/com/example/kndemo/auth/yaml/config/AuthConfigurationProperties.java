package com.example.kndemo.auth.yaml.config;

import com.example.kndemo.auth.yaml.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConfigurationProperties("kndemo.auth")
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthConfigurationProperties {
    List<User> users;
}
