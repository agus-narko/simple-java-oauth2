package com.agus.java.resource.exception.util;

import com.agus.java.model.exception.ErrorDetails;
import com.agus.java.resource.exception.ApprovalException;
import com.agus.java.resource.exception.FileUploadException;
import com.agus.java.resource.exception.HitServerOAuth2Exception;
import com.agus.java.resource.exception.ImageUploadException;
import com.agus.java.resource.exception.InvalidParameterException;
import com.agus.java.resource.exception.ParsingFailedException;
import com.agus.java.resource.exception.ResourceNotFoundException;
import com.agus.java.resource.exception.UnrecognizedBoParamException;
import com.agus.java.resource.exception.UnsupportedParameterException;
import java.util.Date;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public GlobalExceptionHandler() {
        super();
    }


    //========================400=============================
    @ExceptionHandler({ ConstraintViolationException.class,
        DataIntegrityViolationException.class,
        FileUploadException.class,
        ImageUploadException.class,
        ApprovalException.class,
        InvalidParameterException.class,
        UnsupportedParameterException.class,
        UnrecognizedBoParamException.class})
    public ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    //==========================404=====================
    @ExceptionHandler({
        EntityNotFoundException.class,
        ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFound(final RuntimeException ex, final WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    //===========================409=====================
    @ExceptionHandler({
        InvalidDataAccessApiUsageException.class,
        DataAccessException.class })
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    //===========================412=====================

    //===========================500=====================
    @ExceptionHandler({
        NullPointerException.class,
        IllegalArgumentException.class,
        IllegalStateException.class,
        ParsingFailedException.class,
        HitServerOAuth2Exception.class
    })
    public ResponseEntity<Object> handleInternalServerError(final RuntimeException ex,final WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //with DefaultErrorAttributes
//    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
//    public void globleExcpetionHandler(HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
//    }
}