package com.ipinyou.other.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final int code;

    private final String message;

    public BizException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
