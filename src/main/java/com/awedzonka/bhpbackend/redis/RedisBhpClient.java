package com.awedzonka.bhpbackend.redis;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisURI;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Tutaj robię proste połączenie. W sieci są przykłądy z pulą połączeń. W naszym przypadku to wystarczy.
 * W Javie poleca się używanie klasy Jedis, pewnie mniej kodu i klasa automatycznie zamyka połączenie. Potem ...
 */

@Component
public class RedisBhpClient {

    private final RedisClient redisClient = new RedisClient(RedisURI.create("redis://bhp-backend-redis:6379"));

    public String setKey(String key, String value) {
        RedisConnection<String, String> connection = redisClient.connect();
        String result = connection.set(key, value);
        connection.close();
        return result;
    }

    public String getValue(String key) {
        RedisConnection<String, String> connection = redisClient.connect();
        String value = connection.get(key);
        connection.close();
        // W dokumentacji bylo jeszcze to, ale na moje potrzeby nie ubijam, bo wolniej działa i nie trzeba znowu tworzyć połączenia.
//        redisClient.shutdown();
        return value;
    }

    public Boolean exist(String key) {
        RedisConnection<String, String> connection = redisClient.connect();
        Boolean result = connection.exists(key);
        connection.close();
        return result;
    }

    public Long deleteKey(String key) {
        RedisConnection<String, String> connection = redisClient.connect();
        Long result = connection.del(key);
        connection.close();
        return result;
    }

    public List<String> getAllKeys() {
        RedisConnection<String, String> connection = redisClient.connect();
        List<String> keys = connection.keys("*");
        connection.close();
        return keys;
    }

    public String deleteAllKeys() {
        RedisConnection<String, String> connection = redisClient.connect();
        String value = connection.flushall();
        connection.close();
        return value;
    }
}

