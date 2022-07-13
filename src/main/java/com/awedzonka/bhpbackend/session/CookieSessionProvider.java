package com.awedzonka.bhpbackend.session;

import com.awedzonka.bhpbackend.env.EnvDictionary;
import com.awedzonka.bhpbackend.env.EnvInterface;
import com.awedzonka.bhpbackend.lib.GsonProvider;
import com.awedzonka.bhpbackend.lib.HashCreator;
import com.awedzonka.bhpbackend.model.User;
import com.awedzonka.bhpbackend.redis.RedisBhpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CookieSessionProvider {
    private final HashCreator hashCreator;
    private final EnvInterface env;
    private final RedisBhpClient redisBhpClient;
    private final GsonProvider gsonProvider;

    public RedisSession createSessionForRedis(User user) {
        RedisSession sessionForRedis = new RedisSession(hashCreator.createSha256(getHashForSession()), gsonProvider.get().toJson(new SessionCustomerData(user)));
        redisBhpClient.setKey(sessionForRedis.getHashKey(), sessionForRedis.getValue());
        redisBhpClient.setExpireTime(sessionForRedis.getHashKey(), 300);
        return sessionForRedis;
    }

    public CookieSession createSessionForBrowser(String value) {
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
