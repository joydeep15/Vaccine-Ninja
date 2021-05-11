package org.joydeep.publisher;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.joydeep.limiter.SimpleRateLimiter;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TwilioPublisher implements Publisher {



    // Find your Account Sid and Token at twilio.com/console
    @Getter public static String ACCOUNT_SID;
    @Getter public static String AUTH_TOKEN;
    @Getter public final String MESSAGING_SERVICE_ID;
    @Getter public final List<PhoneNumber> numbers;
    @Getter public final SimpleRateLimiter simpleRateLimiter;

    public TwilioPublisher(@NonNull String sid, @NonNull String auth_token, @NonNull String messaging_sid,
                           @NonNull SimpleRateLimiter simpleRateLimiter,
                           @NonNull List<String> stringNums){
        ACCOUNT_SID = sid;
        AUTH_TOKEN = auth_token;
        this.MESSAGING_SERVICE_ID = messaging_sid;
        this.simpleRateLimiter = simpleRateLimiter;

        this.numbers = new ArrayList<>();
        stringNums.forEach( n-> this.numbers.add(new PhoneNumber(n)));

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


    }

    @Override
    public void publish(String body) {

        if(!simpleRateLimiter.send()){
            log.debug("Rate Limiter Active. Skipping Send");
            return;
        }

        for (PhoneNumber number : numbers){

            log.info("Sending Message to: "+number+ " : "+body);
            Message message = Message.creator(
                    number,
                    getMESSAGING_SERVICE_ID(),
                    body)
                    .create();

            log.info(message.getSid());

        }

    }
}
