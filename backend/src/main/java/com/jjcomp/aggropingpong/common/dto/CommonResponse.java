package com.jjcomp.aggropingpong.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private static final String MSG_OK = HttpStatus.OK.toString();
    private static final String MSG_BAD = HttpStatus.BAD_REQUEST.toString();
    private static final String MSG_NOT_FOUND = HttpStatus.NOT_FOUND.toString();
    private static final String MSG_INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.toString();

    private int status;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(200, MSG_OK, null);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(200, MSG_OK, data);
    }

    public static <T> CommonResponse<T> badRequest() {
        return new CommonResponse<>(400, MSG_BAD, null);
    }

    public static <T> CommonResponse<T> notFound() {
        return new CommonResponse<>(404, MSG_NOT_FOUND, null);
    }

    public static <T> CommonResponse<T> error() {
        return new CommonResponse<>(500, MSG_INTERNAL_SERVER_ERROR, null);
    }

}
