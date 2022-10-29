package com.spotifinder.authentication;

import com.spotifinder.common.PageMappingInfo;
import com.spotifinder.security.jwt.JwtProvider;
import com.spotifinder.user.User;
import com.spotifinder.user.UserDto;
import com.spotifinder.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthenticationRestController {

    private AuthenticationService authenticationService;

    private UserService userService;

    private JwtProvider jwtProvider;

    public AuthenticationRestController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping(PageMappingInfo.REGISTER_PAGE)
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {

        if (Optional.of(userService.findByUsername(userDto.getUsername())).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @PostMapping(PageMappingInfo.LOGIN_PAGE)
    public ResponseEntity<?> login(@RequestBody User user) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }
}