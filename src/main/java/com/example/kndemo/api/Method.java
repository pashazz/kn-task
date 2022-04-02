package com.example.kndemo.api;

public interface Method<IN, OUT> {
    OUT execute(IN request);
}
