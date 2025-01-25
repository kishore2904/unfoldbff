package com.unfold.unfoldbff.exception;

import com.unfold.unfoldbff.utils.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {

    private final String errorCode;
    private final String errorMsg;
    private final HttpStatus statusCode;

    public NotFoundException(String errorType) {
        this.errorCode = ErrorMessage.ErrorCodeConstants.valueOf(errorType).getErrorCode();
        this.errorMsg = ErrorMessage.ErrorCodeConstants.valueOf(errorType).getErrorMessage();
        this.statusCode = ErrorMessage.ErrorCodeConstants.valueOf(errorType).getHttpStatus();
    }
}
