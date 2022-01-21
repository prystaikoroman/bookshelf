package com.learnjava.bookshelf.service;

import com.learnjava.bookshelf.dao.CategoryDao;
import com.learnjava.bookshelf.dto.CategoryDto;
import com.learnjava.bookshelf.dto.CategoryMapper;
import com.learnjava.bookshelf.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<CategoryDto> getAllCategory() {
        final List<Category> categories = categoryDao.findAll();

        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : categories) {
            categoryDtos.add(CategoryMapper.CATEGORY_MAPPER.categoryToCategoryDto(category));
        }
        return categoryDtos;
    }

    public CategoryDto parseCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        final Optional<Category> CategoryFoundById = categoryDao.findById(id);
        return parseCategory(CategoryFoundById.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "No category found with such id " + id)));
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        final Category category = CategoryMapper.CATEGORY_MAPPER.categoryDtoToCategory(categoryDto);
        categoryDao.saveAndFlush(category);
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    public Category parseCategoryDto(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
        if (categoryDao.existsById(id)) {
            categoryDto.setId(id);
            final Category category = CategoryMapper.CATEGORY_MAPPER.categoryDtoToCategory(categoryDto);
            categoryDao.saveAndFlush(category);
            return categoryDto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no such category with Id " + id);
        }
    }

    @Override
    public void deleteCategory(int id) {
        if (categoryDao.existsById(id)) {
            categoryDao.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No category found");
        }
    }
}
