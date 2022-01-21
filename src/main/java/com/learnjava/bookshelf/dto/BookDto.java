package com.learnjava.bookshelf.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private int id;
    private String title;
    private double price;
    private Set<Integer> categoryId;
    private Set<Integer> authorId;

}
