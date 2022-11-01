package com.spotifinder.user;

import java.util.List;

public interface UserService {


    List<UserDto> findAll();

    UserDto findByUuid(String uuid);

    UserDto save(UserDto userDto);

    UserDto updateByUuid(String uuid, UserDto requestBody);

    void deleteByUuid(String uuid);
}
