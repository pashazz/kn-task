package com.example.kndemo.api.method;

public interface Method<IN, OUT> {
    OUT execute(IN request);
}
