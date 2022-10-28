package com.spotifinder.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDto {


    private Long id;
    private String uuid;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;


}
