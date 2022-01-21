package com.learnjava.bookshelf.service;

import com.learnjava.bookshelf.dao.AuthorDao;
import com.learnjava.bookshelf.dto.AuthorDto;
import com.learnjava.bookshelf.dto.AuthorMapper;
import com.learnjava.bookshelf.dto.AuthorPage;
import com.learnjava.bookshelf.dto.BookPage;
import com.learnjava.bookshelf.entity.Author;
import com.learnjava.bookshelf.entity.Book;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import  com.learnjava.bookshelf.dto.AuthorMapper;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorDao authorDao;
//    @Autowired
//    private BookServiceImpl bookServiceImpl;
//    @Autowired
//    private BookService bookService;

    @Override
    public AuthorPage getAllAuthor(int page, int size) {
        final AuthorPage authorPage = new AuthorPage();
        final Page<Author> allAuthor = authorDao.myFindAll(PageRequest.of(page, size));
        List<AuthorDto> authorDtos = new ArrayList<>();

        for (Author author : allAuthor) {
            authorDtos.add(parseAuthor(author));
        }
        authorPage.setAuthors(authorDtos);
        authorPage.setCurrentPage(allAuthor.getNumber());
        authorPage.setTotalElements(allAuthor.getTotalElements());
        authorPage.setIslast(allAuthor.isLast());

        return authorPage;
    }

    public AuthorDto parseAuthor(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setSurname(author.getSurname());
        Set<Integer> bookId = new HashSet<>();
        for (Book book : author.getBooks()) {
            bookId.add(book.getId());
        }
        authorDto.setBookId(bookId);
        return authorDto;
    }

    @Override
    public AuthorDto getAuthorById(int id) {
        final Optional<Author> authorDaoById = authorDao.findById(id);
        return parseAuthor(authorDaoById.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No author found with id " + id)));
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {

        final Author author = parseAuthorDto(authorDto);
        authorDao.saveAndFlush(author);
        authorDto.setId(author.getId());
        return authorDto;
    }

    public Author parseAuthorDto(AuthorDto authorDto) {
        validateAuthorDto(authorDto);

        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
//        if (intCycleAuthor < 2) {
//            intCycleAuthor++;
//            List<Book> bookSet = new ArrayList<>();
//
//            for (Integer integer : authorDto.getBookId()) {
//                bookSet.add(bookServiceImpl.parseBookDto(bookService.getBookById(integer), intCycleAuthor, intCycleBook));
//
//            }
//            author.setBooks(bookSet);
//        }
        return author;
    }

    public void validateAuthorDto(AuthorDto authorDto) {
        if (StringUtils.isBlank(authorDto.getName())) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Name should not be empty");
        } else if (!CharUtils.isAsciiAlphaUpper(authorDto.getName().charAt(0))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name should start with capital letter");
        }
    }

    @Override
    public AuthorDto updateAuthor(int id, AuthorDto authorDto) {
        if (!authorDao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No author found with id " + id);
        }
        authorDto.setId(id);
//        final Author author1 = parseAuthorDto(authorDto);
        final Author author1 = AuthorMapper.AUTHOR_MAPPER.authorDtoToAuthor(authorDto);

//        final Author author2 =AuthorMapper.AUTHOR_MAPPER.updateAuthorFromDto(authorDto, author1);
        final Author author = authorDao.saveAndFlush(author1);
        return authorDto;
    }

    @Override
    public void deleteAuthor(int id) {
        if (!authorDao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No author found with id " + id);
        }
        authorDao.deleteById(id);
    }
}
