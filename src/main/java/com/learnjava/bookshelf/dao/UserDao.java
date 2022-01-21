package com.learnjava.bookshelf.dao;

import com.learnjava.bookshelf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
