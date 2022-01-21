package com.learnjava.bookshelf.dto;

import com.learnjava.bookshelf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


public class UserMapperImpl implements UserMapper {

//    @Autowired
private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userDto.getUsername() );
        user.setPassword(passwordEncoder.encode( userDto.getPassword() ));
        user.setRole( userDto.getRole() );
        user.setEmail( userDto.getEmail() );

        return user;
    }

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        userDto.setEmail( user.getEmail() );
        userDto.setRole( user.getRole() );

        return userDto;
    }

    @Override
    public String userPasswordToUserDtoPassword(User user) {
        if ( user == null ) {
            return null;
        }

        String string = new String();

        return string;
    }

    @Override
    public String userDtoPasswordToUserPassword(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        String string = new String();

        return string;
    }
}
