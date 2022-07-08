package com.awedzonka.bhpbackend.session;

import lombok.Data;

@Data
public class CookieSession {
    private final static String NAME_OF_KEY = "BHP_SID";
    private final String key = CookieSession.NAME_OF_KEY;
    private final String value;
}
