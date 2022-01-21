package com.learnjava.bookshelf.controller;

import com.learnjava.bookshelf.dto.AuthorDto;
import com.learnjava.bookshelf.dto.AuthorPage;
import com.learnjava.bookshelf.entity.Author;
import com.learnjava.bookshelf.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/author")
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping

    public AuthorPage getAuthor (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        return authorService.getAllAuthor(page, size);
    }

    @GetMapping ("/{id}")
    public AuthorDto getAuthorById (@PathVariable int id){
        return authorService.getAuthorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto insertAuthor (@RequestBody AuthorDto authorDto){
        return authorService.createAuthor(authorDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthorDto updateAuthor (@PathVariable int id, @RequestBody AuthorDto authorDto){
        return authorService.updateAuthor(id, authorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id){
        authorService.deleteAuthor(id);
    }



}
