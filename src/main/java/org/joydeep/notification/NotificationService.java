package org.joydeep.notification;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.joydeep.subscriber.Subscriber;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
public class NotificationService {

    @Getter @NonNull private final List<Subscriber> subscribers;

    public void register(Subscriber p){
        if(p!=null){
            subscribers.add(p);
        }
    }

    public void notifyAll(String body){
        subscribers.forEach(p -> p.publish(body));
    }

}
