package org.joydeep.limiter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class SimpleRateLimiter {

    @Getter private final int timeDuration;
    @Getter private final TimeUnit timeUnit;
    private Date lastPublishedDate = null;

    // send 1 notification every timeduration

    public boolean send(){
        if(lastPublishedDate == null){
            lastPublishedDate = new Date();
            return true;
        }

        Date now = new Date();
        Date nextPublish = new Date(lastPublishedDate.getTime() + timeUnit.toMillis(timeDuration));

        if(now.compareTo(nextPublish) >= 0){

            lastPublishedDate = now;
            return true;

        }

        return false;

    }




}
