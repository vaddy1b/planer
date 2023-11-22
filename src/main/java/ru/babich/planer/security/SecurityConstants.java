package ru.babich.planer.security;

public class SecurityConstants {

    public static final String SIGN_UP_URL = "/auth/**";

    public static final String SECRET = "SecretKeyGenJWT";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final String CONTENT_TYPE = "app/json";

    public static final Long EXPIRATION_TIME = 600_000l;            //~10 min of waiting

}
