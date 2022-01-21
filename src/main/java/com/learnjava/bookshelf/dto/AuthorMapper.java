package com.learnjava.bookshelf.dto;

import com.learnjava.bookshelf.entity.Author;
import com.learnjava.bookshelf.entity.Book;
import com.learnjava.bookshelf.service.BookService;
import com.learnjava.bookshelf.service.BookServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Mapper()
public interface AuthorMapper {


    AuthorMapper AUTHOR_MAPPER = Mappers.getMapper(AuthorMapper.class);
//     Author updateAuthorFromDto(AuthorDto authorDto, @MappingTarget Author author);

    @Mapping(target = "books", source = "bookId")
    Author authorDtoToAuthor(AuthorDto authorDto);

@Mapping(target = "bookId", source = "ids")
 List<Book> mapSetIdsToListBooks(Set<Integer> ids);

   Book bookDtoToBook(BookDto bookDto);

@Mapping(target = "id", source = "id")

//@Mapping(target = "id", qualifiedBy = {BookService.class, BookServiceImpl.class})
Book getBook(Integer id);
//default
//{
//    BookService bookDao = new BookServiceImpl();
//    return bookDtoToBook(bookDao.getBookById(id));}
}
