package com.learnjava.bookshelf.dao;

import com.learnjava.bookshelf.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Integer> {

//todo : query

    @Query("Select b From Book b Where b.title Like :title")
    Optional<Book> findByTitle(String title) ;

    @Query(value = "Select b From Book b left join fetch b.authors auths left join fetch b.categories ctg order by b.title", countQuery = "Select Count(b) from Book b")
    Page<Book> myFindAll(Pageable pageable);
}
