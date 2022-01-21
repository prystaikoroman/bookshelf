package com.learnjava.bookshelf.service;

import com.learnjava.bookshelf.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategory();

    CategoryDto getCategoryById(int id);

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, int id);

    void deleteCategory(int id);
}
