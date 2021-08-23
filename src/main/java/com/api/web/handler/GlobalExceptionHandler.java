package com.api.web.handler;


import com.api.web.exceptions.AppBaseException;
import com.api.web.exceptions.CheckException;
import com.api.web.response.AppErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import java.util.Locale;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

      private MessageSource messageSource;


    @ExceptionHandler({AppBaseException.class})
    public ResponseEntity<AppErrorResponse> handleAppBaseException(
            AppBaseException ex, Locale locale, ServerWebExchange exchange) {

        if (ex.getCause() != null) {
            log.error(ex.getLocalizedMessage(), ex);
        }

        HttpStatus status = ex.getStatus() != null
                ? ex.getStatus()
                : HttpStatus.BAD_REQUEST;

        String errorMessage = ex.getErrorCode() != null
                ? messageSource.getMessage(ex.getErrorCode()
                    .getValue(), ex.getArgs(), locale)
                        : null;

        return new ResponseEntity<>(
                AppErrorResponse.builder()
                        .timestamp(System.currentTimeMillis())
                            .path(exchange.getRequest().getPath().value())
                                .status(status.value())
                                    .error(status.getReasonPhrase())
                                        .message(errorMessage != null ? errorMessage : ex.getLocalizedMessage())
                                            .requestId(exchange.getRequest().getId())
                                                .build(), status
        );
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handlerBindException(WebExchangeBindException ex) {
        return new ResponseEntity<>(toString(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckException.class)
    public ResponseEntity<String> handlerCheckException(CheckException ex) {
        return new ResponseEntity<>(toString(ex), HttpStatus.BAD_REQUEST);
    }

    private String toString(CheckException ex) {
        return ex.getFieldName() + "：" + ex.getFieldValue();
    }

    private String toString(WebExchangeBindException ex) {
        return ex.getFieldErrors().stream()
                .map(e -> e.getField() + ":" + e.getDefaultMessage()).reduce("", (s1, s2) -> s1 + "\n" + s2);
    }
}