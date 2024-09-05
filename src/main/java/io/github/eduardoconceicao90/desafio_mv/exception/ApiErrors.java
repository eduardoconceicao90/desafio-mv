package io.github.eduardoconceicao90.desafio_mv.exception;

import java.util.Arrays;
import java.util.List;

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
