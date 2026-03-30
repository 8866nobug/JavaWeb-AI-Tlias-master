package com.carrot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@SpringBootTest
public class LogTests {
    private static final Logger logger = LoggerFactory.getLogger(LogTests.class);

    @Test
    public void testLog() {

        logger.debug("测试logger的debug功能");
        logger.info("测试logger的info功能");
        logger.error("测试logger的error功能");
    }
}
