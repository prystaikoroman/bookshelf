package com.learnjava.bookshelf.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @NotBlank - doesn't matter because we work with Dto
    @Column(nullable = false, length = 50)
    private String title;
    @PositiveOrZero
    private double price;

   @ManyToMany(fetch = FetchType.LAZY)
//   @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"),
//           inverseJoinColumns = @JoinColumn(name = "category_id"))
   private Set<Category> categories;


    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"),
//                    inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;



}
