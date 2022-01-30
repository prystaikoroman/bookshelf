package com.learnjava.bookshelf.service;

import com.learnjava.bookshelf.dao.UserDao;
import com.learnjava.bookshelf.dto.UserDto;
import com.learnjava.bookshelf.dto.UserMapper;
import com.learnjava.bookshelf.dto.UserMapperImpl;
import com.learnjava.bookshelf.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private  UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserMapperImpl userMapper = new UserMapperImpl();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }

    @Override
    public String createUser(UserDto userDto) {

        final User save = userDao.save(userMapper.userDtoToUser(userDto));
        return save.getUsername();
    }
}
