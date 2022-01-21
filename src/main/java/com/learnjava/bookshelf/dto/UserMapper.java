package com.learnjava.bookshelf.dto;

import com.learnjava.bookshelf.entity.User;


public interface UserMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto (User user);

    String userPasswordToUserDtoPassword(User user);

    String userDtoPasswordToUserPassword(UserDto userDto);

}
