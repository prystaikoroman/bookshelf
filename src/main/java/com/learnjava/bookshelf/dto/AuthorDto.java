package com.learnjava.bookshelf.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

        private int id;
        private String name;
        private String surname;
        private Set<Integer> bookId;

}
