package org.joydeep.subscriber;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LogSubscriberFactory implements SubscriberFactory{

    @Override
    public Subscriber generate() {
        log.info("Generating LogSubscriber");
        return new LogSubscriber();
    }
}
