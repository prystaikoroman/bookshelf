package com.learnjava.bookshelf.dao;

import com.learnjava.bookshelf.entity.Author;
import com.learnjava.bookshelf.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorDao extends JpaRepository<Author, Integer> {

    @Query(value = "Select a From Author a left join fetch a.books b left join fetch b.categories order by a.name"
            , countQuery = "Select Count(a) from Author a")
Page<Author> myFindAll(Pageable pageable);
}
