package com.awedzonka.bhpbackend.session;

import lombok.Data;

@Data
public class RedisSession {
    private final String hashKey;
    private final String value;
}
