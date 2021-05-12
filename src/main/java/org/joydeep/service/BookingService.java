package org.joydeep.service;

import lombok.extern.log4j.Log4j2;
import org.joydeep.model.Center;
import org.joydeep.model.District;
import org.joydeep.notification.NotificationService;
import org.joydeep.notification.NotificationServiceFactory;
import org.joydeep.subscriber.FileSubscriber;
import org.joydeep.subscriber.LogSubscriber;
import org.joydeep.subscriber.Subscriber;
import org.joydeep.utils.Configuration;
import org.joydeep.utils.Loader;
import org.joydeep.utils.PropertyReader;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import static org.joydeep.utils.Constants.*;

@Log4j2
public class BookingService {

    public static void main(String[] args) throws IOException {

        if(args == null || args.length < 1){
            log.error("Specify properties file as arg[0]");
            return;
        }

        String propertyFileName = args[0];
        Loader.loadProperties(propertyFileName);

        List<District> districtsToMonitor = getDistrictsToMonitor();
        districtsToMonitor.forEach( d -> log.info("Monitoring district: "+d));

        List<Predicate<Center>> filters = new ArrayList<Predicate<Center>>(){{
            add(Filters.availabilityPredicate());
            add(Filters.agePredicate(PropertyReader.getMinAge()));
        }};

        NotificationService notificationService = NotificationServiceFactory.generateNotificationService(PropertyReader.getSubscribers());
        notificationService.notifyAll("Subscribed To Alerts From VaccineNinja for Districts:  "+districtsToMonitor
                +"\nLooking for Open slots for age: "+ PropertyReader.getMinAge());

        ScheduledExecutorService sx = Executors.newScheduledThreadPool(1);
        sx.scheduleAtFixedRate(new FindCentersTask(districtsToMonitor, notificationService, filters),
                0, PropertyReader.getPollingInterval(), TimeUnit.SECONDS);

    }

    public static List<District> getDistrictsToMonitor() throws IOException {
        String propertyVal = PropertyReader.getMonitoredDistricts();
        if(propertyVal == null){
            log.error("Unable to load property: " + MONITORED_DISTRICTS);
            throw new NullPointerException("Unable to load property: "+ MONITORED_DISTRICTS);
        }
        String[] toMonitor = propertyVal.split(",");
        Map<String, District> districtMap = Loader.getDistrictMap();
        List<District> monitoringDistricts = new ArrayList<>();
        log.trace(districtMap);

        for(String district : toMonitor){
            String key = district.trim().toLowerCase(Locale.ROOT);
            if(districtMap.containsKey(key)){
                monitoringDistricts.add(districtMap.get(key));
            }else {
                log.error("No mapping found for district: "+district);
            }
        }
        return monitoringDistricts;
    }

}
