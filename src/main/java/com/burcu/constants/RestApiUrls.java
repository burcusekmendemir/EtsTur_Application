package com.burcu.constants;

public class RestApiUrls {



    private static final String VERSION ="/v1";
    private static final String API ="/api";
    private static final String TEST="/test";
    private static final String DEV="/dev";
    private static final String PROD="/prod";

    private static final String ROOT= DEV + VERSION;
    public static final String USER_PROFILE = ROOT + "/user-profile";
    public static final String AUTH = ROOT + "/auth";
    public static final String OTEL = ROOT + "/otel";
    public static final String SAVE_PROPERTIES = "/save-properties";
    public static final String SAVE_CATEGORY = "/save-category";
    public static final String SAVE_ADDRESS = "/save-address";
    public static final String SAVE_CATEGORY_PROPERTIES ="/save-category-properties";
    public static final String SAVE_ROOM = "/save-room";
    public static final String SAVE_IMAGE = "/save-image";
    public static final String SAVE_OTEL = "/save-otel";
    public static final String ACTIVATE_STATUS = "/activate-status";
    public static final String SAVE_TAGS = "/save-tags";
    public static final String ADD_FAVOURITE = "/add-favourite";
    public static final String FIND_BY_TOKEN = "find-by-token";
    public static final String UPDATE = "update";
    public static final String GET_FAV_OTELS = "get-fav-otels";
    public static final String FILTER_LIST = "/filter-list";

    public static final String ADD= "/add";
    public static final String GET_ALL= "/get-all";
    public static final String GET_BY_ID= "/get-by-id";
    public static final String DELETE_BY_ID= "/delete-by-id";

    public static final String REGISTER= "/register";
    public static final String LOGIN= "/login";

}
