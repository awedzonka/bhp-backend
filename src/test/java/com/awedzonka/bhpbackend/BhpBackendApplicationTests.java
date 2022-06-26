package com.awedzonka.bhpbackend;

import com.awedzonka.bhpbackend.config.BhpBackendApplication;
import com.awedzonka.bhpbackend.lib.LoggerService;
import com.awedzonka.bhpbackend.redis.RedisBhpClient;
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

    @Test
    void contextLoads() {
    }

    // docker-compose exec bhp-backend-java mvn -Dtest=BhpBackendApplicationTests#redis test
    @Test
    void redis() {
        redisBhpClient.setKey("1234", "12345");
        loggerService.info(redisBhpClient.getValue("1234"));
    }

}
