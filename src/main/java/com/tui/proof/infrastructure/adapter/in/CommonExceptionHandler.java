package com.tui.proof.infrastructure.adapter.in;

import com.tui.proof.application.exception.NotFoundException;
import com.tui.proof.application.exception.OrderUpdateException;
import com.tui.proof.infrastructure.adapter.in.dto.error.ErrorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
@Slf4j
public class CommonExceptionHandler {

    public static final String MESSAGE_UNHANDLED_ERROR = "Unhandled error";

    public ErrorDetail handlerException(Exception ex, String instance) {
        HttpStatus status;
        String detail;

        switch (ex) {
            case MethodArgumentNotValidException mEx -> {
                status = HttpStatus.BAD_REQUEST;
                detail = mEx.getMessage();
            }
            case OrderUpdateException oEx -> {
                status = HttpStatus.GONE;
                detail = oEx.getMessage();
            }
            case NotFoundException nEx -> {
                status = HttpStatus.BAD_REQUEST;
                detail = nEx.getMessage();
            }
            default -> {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                detail = MESSAGE_UNHANDLED_ERROR;
            }
        }

        return ErrorDetail.builder().status(status).detail(detail).instance(instance).build();
    }
}
