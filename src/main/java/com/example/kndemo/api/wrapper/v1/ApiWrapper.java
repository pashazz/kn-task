package com.example.kndemo.api.wrapper.v1;

import com.example.kndemo.api.error.ApiError;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class ApiWrapper<T> {
    T result;
    boolean success;
    String errorCode;
    String errorDescription;
    List<ApiError> subErrors;

    public static <T> ApiWrapper<T> ofError(String errorCode, String errorDescription) {
         return ApiWrapper.<T>builder()
                .success(false)
                .errorCode(errorCode)
                .errorDescription(errorDescription)
                .build();
    }

    public static <T> ApiWrapper<T> of(T result) {
        return ApiWrapper.<T>builder()
                .success(true)
                .result(result)
                .build();
    }

    public static <T> ApiWrapper<T> emptySuccess() {
        return ApiWrapper.<T>builder()
                .success(true)
                .build();
    }

}
