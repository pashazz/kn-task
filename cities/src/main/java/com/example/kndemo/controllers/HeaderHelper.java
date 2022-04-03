package com.example.kndemo.controllers;

import com.example.kndemo.constants.CommonConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class HeaderHelper {

    public static final HeaderHelper INSTANCE = new HeaderHelper();

    public static Optional<String> getHeader(HttpHeaders headers, String name) {
        return Optional.ofNullable(headers.getFirst(name));
    }

    public Optional<String> getToken(HttpHeaders headers) {
        return getHeader(headers, CommonConstants.AUTH_HEADER_NAME);
    }

    public String getRequestId(HttpServletRequest request) {
        return request.getHeader(CommonConstants.REQUEST_ID_HEADER_NAME);
    }

    public String getRequestId(HttpHeaders headers) {
        return headers.getFirst(CommonConstants.REQUEST_ID_HEADER_NAME);
    }
}
