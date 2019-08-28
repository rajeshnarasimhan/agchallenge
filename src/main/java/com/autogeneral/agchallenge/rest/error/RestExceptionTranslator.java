package com.autogeneral.agchallenge.rest.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import com.autogeneral.agchallenge.bean.validation.ToDoItemNotFoundError;
import com.autogeneral.agchallenge.bean.validation.ToDoItemValidationError;

/**
 * Exception translator class to handle the expected exceptions/error in the specified format
 * @author rajesh
 */
@ControllerAdvice
public class RestExceptionTranslator implements ProblemHandling {
	
	@Override
    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nonnull NativeWebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<ToDoItemValidationError> fieldErrors = result.getFieldErrors().stream()
            .map(f -> 
            	new ToDoItemValidationError()
				.setLocation("params")
				.setParam(f.getField())
				.setMsg(f.getDefaultMessage())
				.setValue(f.getRejectedValue().toString()))
            .collect(Collectors.toList());

        Problem problem = Problem.builder()
            .withStatus(Status.BAD_REQUEST)
            .with("details", fieldErrors)
            .with("name", "ValidationError")
            .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleItemNotFoundException(ItemNotFoundException ex, NativeWebRequest request) {
    	List<ToDoItemNotFoundError> toDoItemNotFoundErrors = new ArrayList<ToDoItemNotFoundError>();
        toDoItemNotFoundErrors.add(new ToDoItemNotFoundError().setIdInMessage(ex.getId()));
        
    	Problem problem = Problem.builder()
            .withStatus(Status.NOT_FOUND)
            .with("details", toDoItemNotFoundErrors)
            .with("name", "NotFoundError")
            .build();
        return create(ex, problem, request);
    }
    
	@Override
    public ResponseEntity<Problem> handleConstraintViolation(ConstraintViolationException ex, NativeWebRequest request) {
    	Set<ConstraintViolation<?>> result = ex.getConstraintViolations();
    	List<ToDoItemValidationError> fieldErrors = result.stream()
    			.map( f ->  new ToDoItemValidationError()
    					.setLocation("params")
    					.setMsg(f.getMessage())
    					.setParam(f.getPropertyPath().toString())
    					.setValue(f.getInvalidValue().toString()))
    			.collect(Collectors.toList());
        Problem problem = Problem.builder()
            .withStatus(Status.BAD_REQUEST)
            .with("details", fieldErrors)
            .with("name", "ConstraintViolationError")
            .build();
        return create(ex, problem, request);
    }
    
    @Override
	public ResponseEntity<Problem> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, NativeWebRequest request) {
     String error = ex.getParameterName() + " parameter is missing.";
     List<ToDoItemNotFoundError> paramMissingErrors = new ArrayList<ToDoItemNotFoundError>();
     paramMissingErrors.add(new ToDoItemNotFoundError().setMessage(error));
     Problem problem = Problem.builder()
             .withStatus(Status.NOT_FOUND)
             .with("details", paramMissingErrors)
             .with("name", "ParameterMissingError")
             .build();
         return create(ex, problem, request);
    }
}