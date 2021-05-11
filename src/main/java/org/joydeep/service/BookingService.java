package org.joydeep.service;

import lombok.extern.log4j.Log4j2;
import org.joydeep.limiter.SimpleRateLimiter;
import org.joydeep.model.District;
import org.joydeep.publisher.LogPublisher;
import org.joydeep.publisher.Publisher;
import org.joydeep.publisher.TwilioPublisher;
import org.joydeep.utils.Loader;
import org.joydeep.utils.PropertyReader;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

        List<Publisher> publishers = new ArrayList<Publisher>(){{
           add(new TwilioPublisher(
                   PropertyReader.getTwilioSID(), PropertyReader.getTwilioAuthToken(), PropertyReader.getTwilioMessagingSID(),
                   new SimpleRateLimiter(1, TimeUnit.MINUTES), PropertyReader.getTwilioNumbers()
           ));
           add(new LogPublisher());
        }};

        publishers.forEach( p -> p.publish("Subscribed To Alerts From VaccineNinja for Districts:  "+districtsToMonitor
                +"\nLooking for Open slots for age: "+getMinAge()));

        ScheduledExecutorService sx = Executors.newScheduledThreadPool(1);
        sx.scheduleAtFixedRate(new FindCentersTask(districtsToMonitor, getMinAge(), publishers), 0, getPollingInterval(), TimeUnit.SECONDS);

    }

    private static Integer getMinAge() {
        String ageProperty = Configuration.getInstance().getProperties()
                .getProperty(MIN_AGE);
        return ageProperty == null ? DEFAULT_MIN_AGE : Integer.parseInt(ageProperty);
    }

    private static Integer getPollingInterval() {
        String pollingIntervalProperty = Configuration.getInstance().getProperties()
                .getProperty(INTERVAL_SECONDS);
        return pollingIntervalProperty == null ? DEFAULT_POLLING_INTERVAL_SECONDS : Integer.parseInt(pollingIntervalProperty);
    }

    public static List<District> getDistrictsToMonitor() throws IOException {
        String propertyVal = Configuration.getInstance().getProperties().getProperty(MONITORED_DISTRICTS);
        if(propertyVal == null){
            log.error("Unable to load property: "+ MONITORED_DISTRICTS);
            throw new NullPointerException("Unable to load property: "+ MONITORED_DISTRICTS);
        }
        String[] toMonitor = propertyVal.split(",");
        Map<String, District> districtMap = Loader.getDistrictMap();
        List<District> monitoringDistricts = new ArrayList<>();

        for(String district : toMonitor){
            if(districtMap.containsKey(district.toLowerCase(Locale.ROOT))){
                monitoringDistricts.add(districtMap.get(district.toLowerCase(Locale.ROOT)));
            }else {
                log.error("No mapping found for district: "+district);
            }
        }
        return monitoringDistricts;
    }

}
