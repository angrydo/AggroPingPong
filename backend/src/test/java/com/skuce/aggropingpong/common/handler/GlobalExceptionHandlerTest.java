package com.skuce.aggropingpong.common.handler;

import com.skuce.aggropingpong.common.dto.CommonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("예외처리 단위 테스트: IllegalArgumentException")
    void handleIllegalArgumentException() {
        // Given
        IllegalArgumentException exception = new IllegalArgumentException("Invalid Argument");

        // When
        ResponseEntity<CommonResponse<?>> response = globalExceptionHandler.handleIllegalArgumentException(exception);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(400);
        assertThat(response.getBody().getData()).isNull();
        assertThat(response.getBody().getMessage())
                .isEqualTo("Bad Request: The request is invalid.");
    }

    @Test
    @DisplayName("예외처리 단위 테스트: NullPointerException")
    void handleNullPointerException() {
        // Given
        NullPointerException exception = new NullPointerException("Null pointer");

        // When
        ResponseEntity<CommonResponse<?>> response = globalExceptionHandler.handleNullPointerException(exception);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(500);
        assertThat(response.getBody().getData()).isNull();
        assertThat(response.getBody().getMessage())
                .isEqualTo("Internal Server Error: An unexpected error occurred.");
    }

    @Test
    @DisplayName("예외처리 단위 테스트: SQLException")
    void handleSQLException() {
        // Given
        SQLException exception = new SQLException("SQL error");

        // When
        ResponseEntity<CommonResponse<?>> response = globalExceptionHandler.handleSQLException(exception);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(500);
        assertThat(response.getBody().getData()).isNull();
        assertThat(response.getBody().getMessage())
                .isEqualTo("Internal Server Error: An unexpected error occurred.");
    }

    @Test
    @DisplayName("예외처리 단위 테스트: RuntimeException")
    void handleRuntimeException() {
        // Given
        RuntimeException exception = new RuntimeException("Runtime error");

        // When
        ResponseEntity<CommonResponse<?>> response = globalExceptionHandler.handleRuntimeException(exception);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(500);
        assertThat(response.getBody().getData()).isNull();
        assertThat(response.getBody().getMessage())
                .isEqualTo("Internal Server Error: An unexpected error occurred.");
    }

    @Test
    @DisplayName("예외처리 단위 테스트: Exception")
    void handleGeneralException() {
        // Given
        Exception exception = new Exception("General error");

        // When
        ResponseEntity<CommonResponse<?>> response = globalExceptionHandler.handleGeneralException(exception);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(500);
        assertThat(response.getBody().getData()).isNull();
        assertThat(response.getBody().getMessage())
                .isEqualTo("Internal Server Error: An unexpected error occurred.");
    }
}