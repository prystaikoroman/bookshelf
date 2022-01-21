package com.learnjava.bookshelf.service;

import com.learnjava.bookshelf.dto.AuthorDto;
import com.learnjava.bookshelf.dto.AuthorPage;

import java.util.List;

public interface AuthorService {
    AuthorPage getAllAuthor(int page, int size);

    AuthorDto getAuthorById(int id);

    AuthorDto createAuthor(AuthorDto authorDto);

    AuthorDto updateAuthor(int id, AuthorDto authorDto);

    void deleteAuthor(int id);

}
