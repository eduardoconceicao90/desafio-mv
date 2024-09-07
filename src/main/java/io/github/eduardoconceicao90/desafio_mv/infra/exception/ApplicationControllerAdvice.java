package io.github.eduardoconceicao90.desafio_mv.infra.exception;

import io.github.eduardoconceicao90.desafio_mv.service.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream().map(erro -> erro.getDefaultMessage()).collect(Collectors.toList());
        return new ApiErrors(errors);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleObjectNotFound(ObjectNotFoundException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrors handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String mensagemErro = ex.getMessage();
        List<String> errors = Arrays.asList(mensagemErro);
        return new ApiErrors(errors);
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleClassCastException(ClassCastException ex) {
        String mensagemErro = "ID informado não pertence a este tipo cliente.";
        List<String> errors = Arrays.asList(mensagemErro);
        return new ApiErrors(errors);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleExceptionJava(ApiException ex) {
        String mensagemErro = ex.getMessage();
        List<String> errors = Arrays.asList(mensagemErro);
        return new ApiErrors(errors);
    }


}
