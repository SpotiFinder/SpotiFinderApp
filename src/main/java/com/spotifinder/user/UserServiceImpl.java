package com.spotifinder.user;

import com.spotifinder.exception.UserNotFoundException;
import com.spotifinder.utils.UuidGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> allUsers = userRepository.findAll();
        return UserMapper.mapToDto(allUsers);
    }

    @Override
    public UserDto findByUuid(String uuid) {
        Optional<User> userOptional = userRepository.findByUuid(uuid);
        User response = userOptional.orElseThrow(() -> new UserNotFoundException("User with provided Uuid {" + uuid + "} doesn't exist"));
        UserDto responseDto = UserMapper.mapToDto(response);
        return responseDto;
    }

    @Override
    public UserDto save(UserDto userDto) {
        userDto.setUuid(UuidGenerator.generateUuid());
        userDto.setRole(Role.USER);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(UserMapper.mapToModel(userDto));
        return UserMapper.mapToDto(savedUser);
    }

    @Override
    public UserDto updateByUuid(String uuid, UserDto requestBody) {
        UserDto userDtoToUpdate = findByUuid(uuid);
        userDtoToUpdate.setPassword(passwordEncoder.encode(requestBody.getPassword()));

        if (requestBody.getFirstName() != null) userDtoToUpdate.setFirstName(requestBody.getFirstName());
        if (requestBody.getEmail() != null) userDtoToUpdate.setEmail(requestBody.getEmail());
        if (requestBody.getLastName() != null) userDtoToUpdate.setLastName(requestBody.getLastName());
        if (requestBody.getUsername() != null) userDtoToUpdate.setUsername(requestBody.getUsername());

        User userUpdated = userRepository.saveAndFlush(UserMapper.mapToModel(userDtoToUpdate));
        UserDto responseBody = UserMapper.mapToDto(userUpdated);
        return responseBody;
    }

    @Override
    public void deleteByUuid(String uuid) {
        UserDto userToDelete = findByUuid(uuid);
        User user = UserMapper.mapToModel(userToDelete);
        userRepository.delete(user);
    }

    @Override
    public UserDto findByUsername(String username) {
        User response = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with provided username {" + username + "} doesn't exist"));
        return UserMapper.mapToDto(response);
    }
}