package com.example.kndemo.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        /**
         * No password encoding, but we can add it here
         * TODO: add password encoding
         */
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                log.info("matching password: raw: {}; encoded: {}", rawPassword, encodedPassword);
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }
}
