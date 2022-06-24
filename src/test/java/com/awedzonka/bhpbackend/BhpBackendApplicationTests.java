package com.awedzonka.bhpbackend;

import com.awedzonka.bhpbackend.config.BhpBackendApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {BhpBackendApplication.class})
class BhpBackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
