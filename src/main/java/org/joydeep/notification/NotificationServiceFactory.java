package org.joydeep.notification;

import org.joydeep.notification.NotificationService;
import org.joydeep.subscriber.Subscriber;
import org.joydeep.subscriber.SubscriberType;

import java.util.*;

public class NotificationServiceFactory {

    public static NotificationService generateNotificationService(String subscribers){

        NotificationService notificationService = new NotificationService(new ArrayList<>());
        if(subscribers == null){
            return notificationService;
        }

        List<String> subscriberList = Arrays.asList(subscribers.split(","));
        Map<String, SubscriberType> subscriberTranslationMap = getSubscriberTranslationMap();

        for (String subscriber : subscriberList){
            String key = subscriber.trim().toLowerCase(Locale.ROOT);
            if(subscriberTranslationMap.containsKey(key)){
                notificationService.register(
                        subscriberTranslationMap.get(key)
                                .getSubscriberFactory()
                                .generate());
            }
        }

        return notificationService;

    }

    public static Map<String, SubscriberType> getSubscriberTranslationMap(){

        Map<String, SubscriberType> translationMap = new HashMap<>();
        for(SubscriberType subscriberType : SubscriberType.values()){
            translationMap.put(subscriberType.getSubscriberName(), subscriberType);
        }
        return translationMap;

    }
}
