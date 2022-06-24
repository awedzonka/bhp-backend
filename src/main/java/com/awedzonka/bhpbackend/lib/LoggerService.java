package com.awedzonka.bhpbackend.lib;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggerService {
    private final Logger LOGGER = LoggerFactory.getLogger("Logger");

    public void info(String log) {
        LOGGER.info(log);
    }

}
