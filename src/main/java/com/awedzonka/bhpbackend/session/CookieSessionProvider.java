package com.awedzonka.bhpbackend.session;

import com.awedzonka.bhpbackend.env.EnvDictionary;
import com.awedzonka.bhpbackend.env.EnvInterface;
import com.awedzonka.bhpbackend.lib.HashCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CookieSessionProvider {
    private final HashCreator hashCreator;
    private final EnvInterface env;

    public RedisSession getSessionForRedis() {
        return new RedisSession(hashCreator.createSha256(getHashForSession()), "{}");
    }

    public CookieSession getSessionForBrowser(String value) {
        return new CookieSession(value);
    }

    private String getHashForSession() {
        StringBuilder sb = new StringBuilder();
        sb
            .append(LocalDateTime.now())
            .append(env.get(EnvDictionary.SECRET_KEY));
        return sb.toString();

    }
}
