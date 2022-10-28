package com.spotifinder.user;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {


    private static ModelMapper mapper = new ModelMapper();

    static UserDto mapToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    static List<UserDto> mapToDto(List<User> users) {
        return users.
                stream()
                .map(UserMapper::mapToDto)
                .collect(Collectors.toList());
    }

    static User mapToModel(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    static List<User> mapToModel(List<UserDto> userDtos) {
        return userDtos
                .stream()
                .map(UserMapper::mapToModel)
                .collect(Collectors.toList());
    }
}
