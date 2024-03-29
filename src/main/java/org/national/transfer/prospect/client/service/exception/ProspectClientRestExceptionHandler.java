package org.national.transfer.prospect.client.service.exception;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@CommonsLog
public class ProspectClientRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProspectClientNotFoundException.class})
    protected ResponseEntity<Object> handleProspectClientNotFoundException(RuntimeException ex, WebRequest request) {
        log.error(ex, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProspectClientApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }
}