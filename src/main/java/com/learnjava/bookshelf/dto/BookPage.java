package com.learnjava.bookshelf.dto;

import com.learnjava.bookshelf.entity.Book;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookPage {

    private List<BookDto> books;
    private long totalElements;
    private int currentPage;
    private boolean islast;

}
