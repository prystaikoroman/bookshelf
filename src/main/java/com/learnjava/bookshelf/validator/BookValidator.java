package com.learnjava.bookshelf.validator;


import com.learnjava.bookshelf.dto.AuthorDto;
import com.learnjava.bookshelf.dto.BookDto;
import com.learnjava.bookshelf.entity.Book;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {

        return BookDto.class.isAssignableFrom(clazz) || AuthorDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookDto bookDto = (BookDto) target;
        if (StringUtils.isBlank(bookDto.getTitle())) {
            errors.rejectValue("title", "book.title.not-blank", "Book title shouldn't be empty!");

        } else if (!CharUtils.isAsciiAlphaUpper(bookDto.getTitle().charAt(0))) {
            errors.rejectValue("title", "book.title.capital-letter", "Book title should start with capital letter!");
        }
    }


}
