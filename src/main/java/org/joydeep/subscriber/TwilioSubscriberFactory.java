package org.joydeep.subscriber;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.joydeep.limiter.SimpleRateLimiter;
import org.joydeep.utils.PropertyReader;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Log4j2
public class TwilioSubscriberFactory implements SubscriberFactory{

    @Override
    public Subscriber generate() {

        String accountSID = PropertyReader.getTwilioSID();
        String authToken = PropertyReader.getTwilioAuthToken();
        String messageSID = PropertyReader.getTwilioMessagingSID();
        List<String> numbers = PropertyReader.getTwilioNumbers();
        SimpleRateLimiter simpleRateLimiter = new SimpleRateLimiter(30*60, TimeUnit.SECONDS);

        if(notNull(accountSID, authToken, messageSID, numbers, numbers, simpleRateLimiter)){
            log.info("Generating Twilio Subscriber");
            return new TwilioSubscriber(accountSID, authToken, messageSID, simpleRateLimiter, numbers);
        }
        log.error("Unable to generate twilio subscriber. Verify configuration Properties");
        return null;
    }

    public boolean notNull(Object... objects){
        for(Object obj : objects){
            if(obj == null){
                return false;
            }
        }
        return true;
    }
}
