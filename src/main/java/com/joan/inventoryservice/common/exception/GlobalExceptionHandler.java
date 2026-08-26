package com.joan.inventoryservice.common.exception;

import com.joan.inventoryservice.modules.category.exception.CategoryAlreadyExistsException;
import com.joan.inventoryservice.modules.category.exception.CategoryNotFoundException;
import com.joan.inventoryservice.common.commands.CreateProblemDetailCommand;
import com.joan.inventoryservice.modules.product.exception.ProductAlreadyExistsException;
import com.joan.inventoryservice.modules.product.exception.ProductNotFoundException;
import com.joan.inventoryservice.modules.product.exception.VariantAlreadyExistsException;
import com.joan.inventoryservice.modules.product.exception.VariantInsufficientException;
import com.joan.inventoryservice.modules.product.exception.VariantNotFoundException;
import com.joan.inventoryservice.modules.product.exception.VariantStockLimitExceededException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleBadRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {

        var command = new CreateProblemDetailCommand(
          HttpStatus.BAD_REQUEST,
                "Bad request",
                "Method argument not valid exception"
        );

       ProblemDetail problem = this.compactProblemDetail(request, command);

        HashMap<String, String> errorFields = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(
                        e -> errorFields.put(e.getField(), e.getDefaultMessage())
                );

        problem.setProperty("errorFields", errorFields);

        return problem;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        var command = new CreateProblemDetailCommand(
                HttpStatus.BAD_REQUEST,
                "Bad request",
                ex.getMessage()
        );

        return this.compactProblemDetail(request, command);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ProblemDetail handleCategoryNotFound(CategoryNotFoundException ex, HttpServletRequest request) {

        var command = new CreateProblemDetailCommand(
                HttpStatus.NOT_FOUND,
                "Not Found",
                ex.getMessage()
        );

        return this.compactProblemDetail(request, command);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ProblemDetail handleCategoryAlreadyExists(CategoryAlreadyExistsException ex, HttpServletRequest request) {

        var command = new CreateProblemDetailCommand(
                HttpStatus.CONFLICT,
                "Conflict",
                ex.getMessage()
        );

        return this.compactProblemDetail(request, command);

    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ProblemDetail handleProductAlreadyExists(ProductAlreadyExistsException ex, HttpServletRequest request) {

        var command = new CreateProblemDetailCommand(
                HttpStatus.CONFLICT,
                "Conflict",
                ex.getMessage()
        );

        return this.compactProblemDetail(request, command);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        var command = new CreateProblemDetailCommand(
                HttpStatus.BAD_REQUEST,
                "Bad request",
                ex.getMessage()
        );

        return this.compactProblemDetail(request, command);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFound(ProductNotFoundException ex, HttpServletRequest request) {

        var command = new CreateProblemDetailCommand(
                HttpStatus.NOT_FOUND,
                "Not Found",
                ex.getMessage()
        );

        return this.compactProblemDetail(request, command);
    }

    @ExceptionHandler(VariantAlreadyExistsException.class)
    public ProblemDetail handleVariantAlreadyExists(VariantAlreadyExistsException ex, HttpServletRequest request) {

        var command = new CreateProblemDetailCommand(
                HttpStatus.CONFLICT,
                "Conflict",
                ex.getMessage()
        );

        return this.compactProblemDetail(request, command);
    }

    @ExceptionHandler(VariantNotFoundException.class)
    public ProblemDetail handleVariantNotFound(VariantNotFoundException ex, HttpServletRequest request) {

        var command = new CreateProblemDetailCommand(
                HttpStatus.NOT_FOUND,
                "Not Found",
                ex.getMessage()
        );

        return this.compactProblemDetail(request, command);
    }

    @ExceptionHandler(VariantStockLimitExceededException.class)
    public ProblemDetail handleVariantStockLimitExceeded(VariantStockLimitExceededException ex, HttpServletRequest request) {

        var command = new CreateProblemDetailCommand(
                HttpStatus.CONFLICT,
                "Conflict",
                ex.getMessage()
        );

        return this.compactProblemDetail(request, command);
    }

    @ExceptionHandler(VariantInsufficientException.class)
    public ProblemDetail handleVariantInsufficient(VariantInsufficientException ex, HttpServletRequest request) {

        var command = new CreateProblemDetailCommand(
                HttpStatus.CONFLICT,
                "Conflict",
                ex.getMessage()
        );

        return this.compactProblemDetail(request, command);
    }

    private ProblemDetail compactProblemDetail(HttpServletRequest request, CreateProblemDetailCommand command) {
        String requestPath = request.getRequestURI();

        var problemDetail = ProblemDetail.forStatus(command.httpStatus());
        problemDetail.setInstance(URI.create(requestPath));
        problemDetail.setTitle(command.title());
        problemDetail.setDetail(command.detail());
        problemDetail.setProperty("timestamps", Instant.now());

        return problemDetail;
    }
}
