package com.spotifinder.common;

public class PageMappingInfo {

    // api
    public static final String API_PATH = "/api";

    // users
    public static final String USER_API_PATH = "/users";
    public static final String UUID_PATH = "/{uuid}";
    public static final String USER_UUID_PATH = PageMappingInfo.USER_API_PATH + PageMappingInfo.UUID_PATH;

    // authentication
    public static final String LOGIN_PAGE = "/login";
    public static final String REGISTER_PAGE = "/register";
}
