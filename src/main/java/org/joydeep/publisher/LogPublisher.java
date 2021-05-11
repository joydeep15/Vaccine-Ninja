package org.joydeep.publisher;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LogPublisher implements Publisher{


    @Override
    public void publish(String body) {
        log.info("Log Publisher: "+body);
    }
}
