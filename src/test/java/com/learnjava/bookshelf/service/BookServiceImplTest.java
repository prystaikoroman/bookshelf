package com.learnjava.bookshelf.service;

import com.learnjava.bookshelf.dao.BookDao;
import com.learnjava.bookshelf.dto.BookDto;
import com.learnjava.bookshelf.dto.BookPage;
import com.learnjava.bookshelf.entity.Author;
import com.learnjava.bookshelf.entity.Book;
import com.learnjava.bookshelf.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

import static org.hibernate.validator.internal.util.CollectionHelper.asSet;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

@Mock
private BookDao bookDao;
    @Mock
    private AuthorService authorService;
    @Mock
    private AuthorServiceImpl authorServiceImpl;

    @Mock
    private CategoryService categoryService;
    @Mock
    private CategoryServiceImpl categoryServiceImpl;

    @InjectMocks
    private  BookServiceImpl bookService;

    @Test
    void givenPageAndSizeWhenGettingAllBookThenReturnAllBooks () {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book1", 100, 
                generateCategory(1), 
                generateAuthor(1)));
        books.add(new Book(2, "Book2", 200,
                generateCategory(2),
                generateAuthor(2)));
        final PageImpl<Book> bookPage = new PageImpl<>(books);
        Mockito.when(bookDao.myFindAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn( bookPage);

        List<BookDto> bookDtos = new ArrayList<>();
        final BookDto bookDto1 = new BookDto(1, "Book1", 100, asSet(1), asSet(1));
        final BookDto bookDto2 = new BookDto(2, "Book2", 200, asSet(2), asSet(2));
        bookDtos.add(bookDto1);
        bookDtos.add(bookDto2);

        BookPage expectedResult = new BookPage();
        expectedResult.setBooks(bookDtos);
        expectedResult.setTotalElements(2);
        final BookPage actualResult = bookService.getAllBooks(0, 2);

        Assertions.assertEquals(expectedResult.getTotalElements(), actualResult.getTotalElements());
        final List<BookDto> books1 = expectedResult.getBooks();
        final BookDto expected = books1.get(0);
        final BookDto actual = actualResult.getBooks().get(0);
        Assertions.assertEquals(expected.getId(),actual.getId());
        Assertions.assertEquals(expected.getTitle(),actual.getTitle());
        Assertions.assertEquals(expected.getPrice(),actual.getPrice());
     }

    private Set<Author> generateAuthor(int id) {
        Set<Author> authors= new HashSet<>();
        List<Book> books = new ArrayList<>();

        authors.add(new Author(id, "Name", "Surname", null, books));
                return authors;
    }

    private Set<Category> generateCategory(int id) {
       Set<Category> categories = new HashSet<>();
       List<Book> books = new ArrayList<>();

       categories.add(new Category(id, "biography", books));
        return  categories;
    }

    @Test
    void createBook() {

    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void getBookById() {
    }

    @Test
    void testGetAllBooks() {
    }

    @Test
    void testCreateBook() {
    }

    @Test
    void testUpdateBook() {
    }

    @Test
    void testDeleteBook() {
    }

    @Test
    void testGetBookById() {
    }

    @Test
    void getBookByTitle() {
    }

    @Test
    void parseBook() {
    }

    @Test
    void parseBookDto() {
    }
}