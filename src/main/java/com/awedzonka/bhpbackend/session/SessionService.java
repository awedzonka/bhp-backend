package com.awedzonka.bhpbackend.session;

import com.awedzonka.bhpbackend.redis.RedisBhpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final CookieSessionProvider cookieSessionProvider;
    private final RedisBhpClient redisBhpClient;

    public CookieSession createSession() {
        RedisSession sessionForRedis = cookieSessionProvider.getSessionForRedis();
        CookieSession sessionForBrowser = cookieSessionProvider.getSessionForBrowser(sessionForRedis.getHashKey());

        redisBhpClient.setKey(sessionForRedis.getHashKey(), sessionForRedis.getValue());
        redisBhpClient.setExpireTime(sessionForRedis.getHashKey(), 30);

        return sessionForBrowser;
    }
}
