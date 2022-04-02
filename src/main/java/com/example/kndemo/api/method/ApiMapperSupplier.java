package com.example.kndemo.api.method;

public interface ApiMapperSupplier<REQ, RESP> {

    REQ getRequestMapper();
    RESP getResponseMapper();
}
