package br.com.paulopinheiro.onlinestore.web.controllers.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorControllerAdvisor {
    @ExceptionHandler(NoHandlerFoundException.class)
    public String hannnddle(Exception ex) {
        return "notfound";
    }
}
