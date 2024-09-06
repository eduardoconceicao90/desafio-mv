package io.github.eduardoconceicao90.desafio_mv.infra.exception;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.Arrays;
import java.util.List;

@Hidden
public class ApiErrors {

    List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String message) {
        this.errors = Arrays.asList(message);
    }

    public List<String> getErrors() {
        return errors;
    }

}
