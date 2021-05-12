package org.joydeep.subscriber;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SubscriberType {
    FILE("file", new FileSubscriberFactory()),
    LOG("log", new LogSubscriberFactory()),
    TWILIO("twilio", new TwilioSubscriberFactory());

    @Getter private final String subscriberName;
    @Getter private final SubscriberFactory subscriberFactory;
}
