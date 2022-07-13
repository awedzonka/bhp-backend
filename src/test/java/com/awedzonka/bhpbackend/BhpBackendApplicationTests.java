package com.awedzonka.bhpbackend;

import com.awedzonka.bhpbackend.config.BhpBackendApplication;
import com.awedzonka.bhpbackend.env.EnvDictionary;
import com.awedzonka.bhpbackend.env.EnvInterface;
import com.awedzonka.bhpbackend.lib.GsonProvider;
import com.awedzonka.bhpbackend.lib.LoggerService;
import com.awedzonka.bhpbackend.model.User;
import com.awedzonka.bhpbackend.redis.RedisBhpClient;
import com.awedzonka.bhpbackend.session.CookieSession;
import com.awedzonka.bhpbackend.session.CookieSessionProvider;
import com.awedzonka.bhpbackend.session.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {BhpBackendApplication.class})
class BhpBackendApplicationTests {

    @Autowired
    RedisBhpClient redisBhpClient;
    @Autowired
    LoggerService loggerService;
    @Autowired
    EnvInterface envInterface;
    @Autowired
    CookieSessionProvider cookieSessionProvider;
    @Autowired
    SessionService sessionService;
    @Autowired
    GsonProvider gsonProvider;

    @Test
    void contextLoads() {
    }

    // docker-compose exec bhp-backend-java mvn -Dtest=BhpBackendApplicationTests#redis test
    @Test
    void redis() {
        redisBhpClient.setKey("1234", "12345");
        loggerService.info(redisBhpClient.getValue("1234"));
    }

    // docker-compose exec bhp-backend-java mvn -Dtest=BhpBackendApplicationTests#getEnvTest test
    @Test
    void getEnvTest() {
        String secret = envInterface.get(EnvDictionary.SECRET_KEY);
        loggerService.info(secret);
    }

    // docker-compose exec bhp-backend-java mvn -Dtest=BhpBackendApplicationTests#getCookieSession test
    @Test
    void getCookieSession() {
        String user = """
            {
              "login": "krowa1",
              "password": "123123"
            }
            """;

        CookieSession session = sessionService.createSession(gsonProvider.get().fromJson(user, User.class));
        loggerService.info(session.toString());
        loggerService.info(redisBhpClient.getValue(session.getValue()));

    }

}
