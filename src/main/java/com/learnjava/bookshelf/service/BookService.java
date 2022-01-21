package com.learnjava.bookshelf.service;

import com.learnjava.bookshelf.dto.BookDto;
import com.learnjava.bookshelf.dto.BookPage;
import org.mapstruct.Named;

import java.util.List;
public interface BookService {
    BookPage getAllBooks(int page, int size);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(int id, BookDto bookDto);

    void deleteBook(int id);

    BookDto   getBookById(int id);

    BookDto getBookByTitle(String title);
}
