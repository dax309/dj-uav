package com.cleaner.djuav;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = TestConfiguration.class)
@ActiveProfiles("test")
class DjUavApplicationTests {

    @Test
    void contextLoads() {
    }

}
