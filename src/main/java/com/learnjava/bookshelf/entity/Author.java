package com.learnjava.bookshelf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Author {
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (!(obj instanceof Author)) return false;
//        Author author = (Author) obj;
//        return  Objects.equals(getId(), author.getId());
//
//    }
//
//    @Override
//    public int hashCode() {
//                return id;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(unique = true)
    private String name;
    private String surname;
    @JsonFormat(pattern = "dd-MM-yyyy" )
    private LocalDate birthDate;
@ManyToMany(mappedBy = "authors",fetch = FetchType.LAZY)
    private List<Book> books;
}
