package com.learnjava.bookshelf.controller;

import com.learnjava.bookshelf.dto.BookDto;
import com.learnjava.bookshelf.dto.CategoryDto;
import com.learnjava.bookshelf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<CategoryDto> getCategory() {

        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable int id) {

        return categoryService.getCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto insertCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable int id) {
        return categoryService.updateCategory(categoryDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }
}
