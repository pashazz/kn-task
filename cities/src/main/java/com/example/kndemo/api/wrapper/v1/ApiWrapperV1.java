package com.example.kndemo.api.wrapper.v1;

import com.example.kndemo.api.error.ApiError;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiWrapperV1<T> {
    T result;
    @Builder.Default
    String time = LocalDateTime.now().toString();

    boolean success;
    String errorCode;
    String errorDescription;
    List<ApiError> subErrors;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApiWrapper{");
        sb.append("result=").append(result);
        sb.append(", time='").append(time).append('\'');
        sb.append(", success=").append(success);
        if (errorCode != null) {
            sb.append(", errorCode='").append(errorCode).append('\'');
        }
        if (errorDescription != null) {
            sb.append(", errorDescription='").append(errorDescription).append('\'');
        }
        if (subErrors != null) {
            sb.append(", subErrors=").append(subErrors);
        }
        sb.append('}');
        return sb.toString();
    }

    public static <T> ApiWrapperV1<T> ofError(String errorCode, String errorDescription) {
        return ApiWrapperV1.<T>builder()
                .success(false)
                .errorCode(errorCode)
                .errorDescription(errorDescription)
                .build();
    }

    public static <T> ApiWrapperV1<T> of(T result) {
        return ApiWrapperV1.<T>builder()
                .success(true)
                .result(result)
                .build();
    }
}

