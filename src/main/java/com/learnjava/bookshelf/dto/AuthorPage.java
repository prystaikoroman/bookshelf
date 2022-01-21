package com.learnjava.bookshelf.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorPage {
    private List<AuthorDto> authors;
    private long totalElements;
    private int currentPage;
    private boolean islast;

}
