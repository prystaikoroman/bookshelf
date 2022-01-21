package com.learnjava.bookshelf.validator;

import com.learnjava.bookshelf.dto.AuthorDto;
import com.learnjava.bookshelf.dto.BookDto;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class BookValidatorTest {

    @Test
    void testValidate() {
        BookValidator bookValidator = new BookValidator();
        BookDto bookDto = new BookDto();
        BindException bindException = new BindException(bookDto, "com.learnjava.bookshelf.dto.AuthorDto");

        bookValidator.validate(bookDto, bindException);
        assertEquals(
                "org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult:"
                        + " 1 errors\n"
                        + "Field error in object 'com.learnjava.bookshelf.dto.AuthorDto' on field 'title': rejected value [null];"
                        + " codes [book.title.not-blank.com.learnjava.bookshelf.dto.AuthorDto.title,book.title.not-blank.title"
                        + ",book.title.not-blank.java.lang.String,book.title.not-blank]; arguments []; default message [Book title"
                        + " shouldn't be empty!]",
                bindException.toString());
        assertTrue(bindException.getPropertyEditorRegistry() instanceof org.springframework.beans.BeanWrapperImpl);
        assertEquals(1, bindException.getBindingResult().getErrorCount());
    }

    @Test
    void testValidate2() {
        BookValidator bookValidator = new BookValidator();
        HashSet<Integer> categoryId = new HashSet<>();
        BookDto bookDto = new BookDto(1, "Dr", 10.0, categoryId, new HashSet<>());

        bookValidator.validate(bookDto, new BindException(bookDto, "com.learnjava.bookshelf.dto.AuthorDto"));
        assertTrue(bookDto.getAuthorId().isEmpty());
        assertEquals("Dr", bookDto.getTitle());
        assertEquals(10.0, bookDto.getPrice());
        assertEquals(1, bookDto.getId());
        assertTrue(bookDto.getCategoryId().isEmpty());
    }

    @Test
    void testValidate3() {
        BookValidator bookValidator = new BookValidator();
        BookDto bookDto = new BookDto();
        BindException bindException = new BindException(bookDto, "Object Name");

        bookValidator.validate(bookDto, bindException);
        assertEquals(
                "org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult:"
                        + " 1 errors\n"
                        + "Field error in object 'Object Name' on field 'title': rejected value [null]; codes [book.title.not"
                        + "-blank.Object Name.title,book.title.not-blank.title,book.title.not-blank.java.lang.String,book.title"
                        + ".not-blank]; arguments []; default message [Book title shouldn't be empty!]",
                bindException.toString());
        assertTrue(bindException.getPropertyEditorRegistry() instanceof org.springframework.beans.BeanWrapperImpl);
        assertEquals(1, bindException.getBindingResult().getErrorCount());
    }

    @Test
    void testValidate4() {
        BookValidator bookValidator = new BookValidator();
        BookDto bookDto = new BookDto();
        BindException bindException = new BindException(bookDto, "com.learnjava.bookshelf.dto.AuthorDto");

        bookValidator.validate(bookDto, bindException);
        assertEquals(
                "org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult:"
                        + " 1 errors\n"
                        + "Field error in object 'com.learnjava.bookshelf.dto.AuthorDto' on field 'title': rejected value [null];"
                        + " codes [book.title.not-blank.com.learnjava.bookshelf.dto.AuthorDto.title,book.title.not-blank.title"
                        + ",book.title.not-blank.java.lang.String,book.title.not-blank]; arguments []; default message [Book title"
                        + " shouldn't be empty!]",
                bindException.toString());
        assertTrue(bindException.getPropertyEditorRegistry() instanceof org.springframework.beans.BeanWrapperImpl);
        assertEquals(1, bindException.getBindingResult().getErrorCount());
    }

    @Test
    void testValidate5() {
        BookValidator bookValidator = new BookValidator();
        HashSet<Integer> categoryId = new HashSet<>();
        BookDto bookDto = new BookDto(1, "Dr", 10.0, categoryId, new HashSet<>());

        bookValidator.validate(bookDto, new BindException(bookDto, "com.learnjava.bookshelf.dto.AuthorDto"));
        assertTrue(bookDto.getAuthorId().isEmpty());
        assertEquals("Dr", bookDto.getTitle());
        assertEquals(10.0, bookDto.getPrice());
        assertEquals(1, bookDto.getId());
        assertTrue(bookDto.getCategoryId().isEmpty());
    }

    @Test
    void testValidate6() {
        BookValidator bookValidator = new BookValidator();
        BookDto bookDto = new BookDto();
        BindException bindException = new BindException(bookDto, "Object Name");

        bookValidator.validate(bookDto, bindException);
        assertEquals(
                "org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult:"
                        + " 1 errors\n"
                        + "Field error in object 'Object Name' on field 'title': rejected value [null]; codes [book.title.not"
                        + "-blank.Object Name.title,book.title.not-blank.title,book.title.not-blank.java.lang.String,book.title"
                        + ".not-blank]; arguments []; default message [Book title shouldn't be empty!]",
                bindException.toString());
        assertTrue(bindException.getPropertyEditorRegistry() instanceof org.springframework.beans.BeanWrapperImpl);
        assertEquals(1, bindException.getBindingResult().getErrorCount());
    }


}