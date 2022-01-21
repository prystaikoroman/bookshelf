package com.learnjava.bookshelf.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.learnjava.bookshelf.dao.AuthorDao;
import com.learnjava.bookshelf.dto.AuthorDto;
import com.learnjava.bookshelf.entity.Author;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {AuthorServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuthorServiceImplTest {
    @MockBean
    private AuthorDao authorDao;

    @Autowired
    private AuthorServiceImpl authorServiceImpl;


    @Test
    void testCreateAuthor() {
        assertThrows(ResponseStatusException.class, () -> this.authorServiceImpl.createAuthor(new AuthorDto()));
    }

    @Test
    void testCreateAuthor2() {
        Author author = new Author();
        author.setBirthDate(LocalDate.ofEpochDay(1L));
        author.setBooks(new ArrayList<>());
        author.setId(1);
        author.setName("Name");
        author.setSurname("Doe");
        when(this.authorDao.saveAndFlush((Author) any())).thenReturn(author);
        AuthorDto authorDto = new AuthorDto(1, "Name should not be empty", "Doe", new HashSet<>());

        AuthorDto actualCreateAuthorResult = this.authorServiceImpl.createAuthor(authorDto);
        assertSame(authorDto, actualCreateAuthorResult);
        assertEquals(1, actualCreateAuthorResult.getId());
        verify(this.authorDao).saveAndFlush((Author) any());
    }

    @Test
    void testCreateAuthor3() {
        when(this.authorDao.saveAndFlush((Author) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, () -> this.authorServiceImpl
                .createAuthor(new AuthorDto(1, "Name should not be empty", "Doe", new HashSet<>())));
        verify(this.authorDao).saveAndFlush((Author) any());
    }

    @Test
    void testValidateAuthorDto() {
        assertThrows(ResponseStatusException.class, () -> this.authorServiceImpl.validateAuthorDto(new AuthorDto()));
    }

    @Test
    void testValidateAuthorDto2() {
        AuthorDto authorDto = new AuthorDto(1, "Name should not be empty", "Doe", new HashSet<>());

        this.authorServiceImpl.validateAuthorDto(authorDto);
        assertTrue(authorDto.getBookId().isEmpty());
        assertEquals("Doe", authorDto.getSurname());
        assertEquals("Name should not be empty", authorDto.getName());
        assertEquals(1, authorDto.getId());
    }

    @Test
    void testValidateAuthorDto3() {
        AuthorDto authorDto = mock(AuthorDto.class);
        when(authorDto.getName()).thenReturn("Name");
        this.authorServiceImpl.validateAuthorDto(authorDto);
        verify(authorDto, atLeast(1)).getName();
    }

    @Test
    void testValidateAuthorDto4() {
        AuthorDto authorDto = mock(AuthorDto.class);
        when(authorDto.getName()).thenReturn("foo");
        assertThrows(ResponseStatusException.class, () -> this.authorServiceImpl.validateAuthorDto(authorDto));
        verify(authorDto, atLeast(1)).getName();
    }

    @Test
    void testValidateAuthorDto5() {
        AuthorDto authorDto = mock(AuthorDto.class);
        when(authorDto.getName()).thenReturn("42");
        assertThrows(ResponseStatusException.class, () -> this.authorServiceImpl.validateAuthorDto(authorDto));
        verify(authorDto, atLeast(1)).getName();
    }
}

