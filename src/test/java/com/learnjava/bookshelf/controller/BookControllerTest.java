package com.learnjava.bookshelf.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import com.learnjava.bookshelf.dto.BookDto;
import com.learnjava.bookshelf.service.BookService;
import com.learnjava.bookshelf.validator.BookValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BookController.class})
@ExtendWith(SpringExtension.class)
class BookControllerTest {
    @Autowired
    private BookController bookController;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookValidator bookValidator;

    @Test
    void testGetBookByTitle() throws Exception {
        when(this.bookService.getBookByTitle((String) any())).thenReturn(new BookDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book/title/{title}", "Dr");
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(jsonPath(""))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"title\":null,\"price\":0.0,\"categoryId\":null,\"authorId\":null}"));
    }
}

