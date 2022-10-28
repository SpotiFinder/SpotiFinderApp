package com.spotifinder.user;

import com.spotifinder.common.PageMappingInfo;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PageMappingInfo.API_PATH+"/")
public class UserRestController {

    private final UserServiceImpl userService;

    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(PageMappingInfo.USER_API_PATH)
    public UserDto getUser(@PathVariable(name = "uuid") String uuid) {
        return userService.findByUuid(uuid);
    }
}
