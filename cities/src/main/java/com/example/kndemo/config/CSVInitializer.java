package com.example.kndemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CSVInitializer {

    @Value("${kndemo:")

    @PostConstruct
    void init() {

    }
}
