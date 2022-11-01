package com.spotifinder.authentication;

import com.spotifinder.user.User;

public interface AuthenticationService {

    User signInAndReturnJWT(User signInRequest);

    boolean isLoggedUserIsAdmin();

    boolean isUserLogged();
}

