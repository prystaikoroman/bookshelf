package com.learnjava.bookshelf.controller;

import com.learnjava.bookshelf.dao.BookDao;
import com.learnjava.bookshelf.dto.BookDto;
import com.learnjava.bookshelf.dto.BookPage;
import com.learnjava.bookshelf.entity.Book;
import com.learnjava.bookshelf.service.BookService;
import com.learnjava.bookshelf.validator.BookValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/book")
@RestController
public class  BookController {
//    private List<BookDto> books = new ArrayList<>();
//
//    {
//        books.add(new BookDto(1, "sss", 11));
//    }
    private static Logger LOG =  LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookValidator bookValidator ;

    @Autowired
private BookService bookService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        dataBinder.addValidators(new BookValidator());
    }

    //    @RequestMapping(value = "/book", method = RequestMethod.GET)
    @GetMapping()
    public BookPage   getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {

        return bookService.getAllBooks(page, size);
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable int id){

        return bookService.getBookById(id);
    }

    @GetMapping("/title/{title}")
    public BookDto getBookByTitle(@PathVariable String title){

        return bookService.getBookByTitle(title);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto insertBook(@RequestBody BookDto bookDto) {
        LOG.info("Handling Post request for object {}", bookDto);
        return bookService.createBook(bookDto );
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookDto updateBook(@PathVariable int id, @RequestBody   BookDto bookDto) {
        LOG.info("Handling PUT request for object {}, id= {}", bookDto, id);

        return bookService.updateBook(id,bookDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id){

        bookService.deleteBook(id);
    }
}
