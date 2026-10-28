package com.ahuanay.mvccommon.infrastructure.entrypoints.rest.exception;

import com.ahuanay.mvccommon.infrastructure.entrypoints.rest.dto.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        RestResponse<Object> response = RestResponse.error(
            HttpStatus.BAD_REQUEST.value(),
            "La validación de los campos ha fallado.",
            fieldErrors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestResponse<Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        RestResponse<Object> response = RestResponse.error(
            HttpStatus.BAD_REQUEST.value(),
            "El formato del JSON es inválido o contiene valores no permitidos."
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ParametroInvalidoException.class)
    public ResponseEntity<RestResponse<Object>> handleParametroInvalidoException(ParametroInvalidoException ex) {
        RestResponse<Object> response = RestResponse.error(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RestResponse<Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.warn("Violación de integridad de datos: {}", ex.getMostSpecificCause().getMessage());

        RestResponse<Object> response = RestResponse.error(
            HttpStatus.CONFLICT.value(),
            "La operación entra en conflicto con una restricción de integridad de datos."
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse<Object>> handleGlobalException(Exception ex) {
        log.error("Error interno no controlado: ", ex);

        RestResponse<Object> response = RestResponse.error(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Ocurrió un error inesperado en el servidor."
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}