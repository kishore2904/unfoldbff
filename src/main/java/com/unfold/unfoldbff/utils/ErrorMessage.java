package com.unfold.unfoldbff.utils;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    public static final String DATA_NOT_FOUND = "DATA_NOT_FOUND";
    public static final String INVALID_INPUT = "INVALID_INPUT";
    public static final String USER_NAME_ALREADY_EXIST = "USER_NAME_ALREADY_EXIST";

    public enum ErrorCodeConstants {
        DATA_NOT_FOUND("A001", "UserName Not Found", HttpStatus.NOT_FOUND),
        USER_NAME_ALREADY_EXIST("A002","User Name Already Exist", HttpStatus.BAD_REQUEST),
        INVALID_INPUT("A003", "Invalid Input", HttpStatus.BAD_REQUEST);

        private final String errorCode;
        private final String errorMessage;
        private final HttpStatus httpStatus;


        ErrorCodeConstants(String errorCode, String errorMessage, HttpStatus httpStatus) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
            this.httpStatus = httpStatus;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }
    }
}
