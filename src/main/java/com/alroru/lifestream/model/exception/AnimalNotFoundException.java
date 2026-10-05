package com.alroru.lifestream.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnimalNotFoundException extends RuntimeException {

    public AnimalNotFoundException(Long id) {
        super("No animal found with id: " + id);
    }
}