package com.spotifinder.user;

import com.spotifinder.exception.UserNotFoundException;
import com.spotifinder.utils.UuidGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
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
        Optional<UserDto> userDtoOptional = userRepository.findByUuid(uuid);
        UserDto response = userDtoOptional.orElseThrow(() -> new UserNotFoundException("User with provided Uuid {" + uuid + "} doesn't exist"));
        return response;
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
        userDtoToUpdate.setFirstName(requestBody.getFirstName());
        userDtoToUpdate.setLastName(requestBody.getLastName());
        userDtoToUpdate.setEmail(requestBody.getEmail());
        userDtoToUpdate.setUsername(requestBody.getUsername());

        User userUpdated = userRepository.saveAndFlush(UserMapper.mapToModel(userDtoToUpdate));
        UserDto responseBody = UserMapper.mapToDto(userUpdated);
        return responseBody;
    }

    @Override
    public void deleteByUuid(String uuid) {
        UserDto userToDelete = findByUuid(uuid);
        userRepository.delete(UserMapper.mapToModel(userToDelete));
    }
}
