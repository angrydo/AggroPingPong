package com.jjcomp.aggropingpong.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {
    private static final String MSG_OK = HttpStatus.OK.toString();
    private static final String MSG_BAD = HttpStatus.BAD_REQUEST.toString();
    private static final String MSG_NOT_FOUND = HttpStatus.NOT_FOUND.toString();
    private static final String MSG_INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.toString();

    private int status;
    private String message;
    private T data;

    public static <T> ResponseData<T> success() {
        return new ResponseData<>(200, MSG_OK, null);
    }

    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<>(200, MSG_OK, data);
    }

    public static <T> ResponseData<T> badRequest() {
        return new ResponseData<>(400, MSG_BAD, null);
    }

    public static <T> ResponseData<T> notFound() {
        return new ResponseData<>(404, MSG_NOT_FOUND, null);
    }

    public static <T> ResponseData<T> error() {
        return new ResponseData<>(500, MSG_INTERNAL_SERVER_ERROR, null);
    }

}
