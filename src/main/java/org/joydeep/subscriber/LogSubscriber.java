package org.joydeep.subscriber;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LogSubscriber implements Subscriber {


    @Override
    public void publish(String body) {
        log.info("Log Publisher: "+body);
    }
}
