package com.learnjava.bookshelf.service;

import com.learnjava.bookshelf.dao.BookDao;
import com.learnjava.bookshelf.dto.BookDto;
import com.learnjava.bookshelf.dto.BookPage;
import com.learnjava.bookshelf.entity.Author;
import com.learnjava.bookshelf.entity.Book;
import com.learnjava.bookshelf.entity.Category;
import com.learnjava.bookshelf.exceptions.ItemNotFoundException;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorServiceImpl authorServiceImpl;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;


    @Override
    public BookPage getAllBooks(int page, int size) {
        final Page<Book> books = bookDao.myFindAll(PageRequest.of(page, size));
//        List<BookDto> bookDtos = new ArrayList<>();
//        for (Book book : books.getContent()) {
//            bookDtos.add(parseBook(book));
//        }
        final BookPage bookPage = new BookPage();
        final List<Book> bookList = books.getContent();
        List<BookDto>bookDtos = new ArrayList<>();
        for (Book book : bookList) {
            bookDtos.add(parseBook(book));
        }

        bookPage.setBooks(bookDtos);
        bookPage.setCurrentPage(books.getNumber());
        bookPage.setTotalElements(books.getTotalElements());
        bookPage.setIslast(books.isLast());
        return bookPage;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        if (StringUtils.isBlank(bookDto.getTitle())) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Title should not be empty");
        } else if (!CharUtils.isAsciiAlphaUpper(bookDto.getTitle().charAt(0))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title should start with capital letter");
        }
        Book book = parseBookDto(bookDto);
        bookDao.saveAndFlush(book);
        bookDto.setId(book.getId());
        return bookDto;
    }

    @Override
    public BookDto updateBook(int id, BookDto bookDto) {
        bookDto.setId(id);
        if (!bookDao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No book found");
        }

        bookDto.setId(id);
        bookDao.saveAndFlush(parseBookDto(bookDto));
        return
                bookDto;
    }

    @Override
    public void deleteBook(int id) {
        if (!bookDao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No book found");
        }
        bookDao.deleteById(id);
    }

    @Override
    public BookDto getBookById(int id) {
        final Optional<Book> byId = bookDao.findById(id);
        final Book book = byId.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No book found with such id " + id));
        return parseBook(book);
    }

    @Override
    public BookDto getBookByTitle(String title) {
        final Optional<Book> book = bookDao.findByTitle(title);

        return parseBook(book.orElseThrow(() -> new ItemNotFoundException("Book", "title", title)));
    }

    public BookDto parseBook(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPrice(book.getPrice());
        Set <Integer> authorId = new HashSet<>();
        for (Author author : book.getAuthors()) {
            authorId.add(author.getId());
        }
        bookDto.setAuthorId(authorId);
        final Set<Integer> categoryId = book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
        bookDto.setCategoryId(categoryId);
        return bookDto;
    }

    public Book parseBookDto(BookDto bookDto){
        Book book = new Book();
        book.setId(bookDto.getId());
        Set<Category> categoryList = new HashSet<>();
        for (Integer integer : bookDto.getCategoryId()) {
            categoryList.add(categoryServiceImpl.parseCategoryDto(categoryService.getCategoryById(integer)));
        }
        book.setCategories(categoryList);
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());

        Set<Author> authorList = new HashSet<>();
        for (Integer integer : bookDto.getAuthorId()) {
            authorList.add(authorServiceImpl.parseAuthorDto(authorService.getAuthorById(integer)));
        }
        book.setAuthors(authorList);

        return book;
    }
}
