package com.learnjava.bookshelf.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ItemNotFoundException extends  RuntimeException {
    public ItemNotFoundException(String entity, String field, String value) {
        super(String.format("No item found for %s with field %s value = %s", entity, field, value)) ;
    }
}
