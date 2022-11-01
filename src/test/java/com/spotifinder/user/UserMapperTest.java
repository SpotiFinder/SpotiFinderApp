package com.spotifinder.user;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {


    private User user = new User(1L, "123-421", "nazwa_usera", "haslo",
            "email", "imie", "nazwisko", Role.USER);
    private UserDto userDto = new UserDto(1L, "123-421", "nazwa_usera", "haslo",
            "email", "imie", "nazwisko", Role.USER);

    @Test
    void should_mapToDto() {

        //given
        UserDto mappedUserToDto = UserMapper.mapToDto(user);
        //then
        assertEquals(userDto, mappedUserToDto);
    }

    @Test
    void should_map_list_toDto() {

        //given
        List<User> users = List.of(user, user, user);
        List<UserDto> userDtos = List.of(userDto, userDto, userDto);
        //when
        List<UserDto> convertedUsers = UserMapper.mapToDto(users);
        //then
        assertEquals(userDtos, convertedUsers);

    }

    @Test
    void should_mapToModel() {

        //given
        User mappedUserDtoToModel = UserMapper.mapToModel(userDto);
        //then
        assertEquals(user, mappedUserDtoToModel);
    }

    @Test
    void should_map_list_toModel() {

        //given
        List<UserDto> userDtos = List.of(userDto, userDto, userDto);
        List<User> users = List.of(user, user, user);
        //when
        List<User> convertedDtos = UserMapper.mapToModel(userDtos);
        //then
        assertEquals(users, convertedDtos);

    }
}