package com.awedzonka.bhpbackend.session;

import com.awedzonka.bhpbackend.model.User;
import com.awedzonka.bhpbackend.redis.RedisBhpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final CookieSessionProvider cookieSessionProvider;
    private final RedisBhpClient redisBhpClient;

    public CookieSession createSession(User user) {
        RedisSession sessionForRedis = cookieSessionProvider.createSessionForRedis(user);
        return cookieSessionProvider.createSessionForBrowser(sessionForRedis.getHashKey());
    }
}
