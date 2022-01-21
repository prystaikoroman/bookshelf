package com.learnjava.bookshelf.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;
@ManyToMany(mappedBy = "categories")
    private List<Book> books;

}
