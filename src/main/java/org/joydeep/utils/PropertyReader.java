package org.joydeep.utils;

import java.util.Arrays;
import java.util.List;

import static org.joydeep.utils.Constants.*;

public class PropertyReader {

    public static String getTwilioAuthToken(){
        return Configuration.getInstance().getProperties().getProperty(TWILIO_AUTH_TOKEN);
    }

    public static String getTwilioSID(){
        return Configuration.getInstance().getProperties().getProperty(TWILIO_SID);
    }

    public static List<String> getTwilioNumbers(){
        String numbers = Configuration.getInstance().getProperties().getProperty(TWILIO_PH_NUMBERS);
        if(numbers != null){
            return Arrays.asList(
                    numbers.split(",")
            );
        }

        return null;
    }

    public static String getTwilioMessagingSID(){
        return Configuration.getInstance().getProperties().getProperty(TWILIO_MESSAGING_SID);
    }

    public static Integer getMinAge() {
        String ageProperty = Configuration.getInstance().getProperties()
                .getProperty(MIN_AGE);
        return ageProperty == null ? DEFAULT_MIN_AGE : Integer.parseInt(ageProperty);
    }

    public static Integer getPollingInterval() {
        String pollingIntervalProperty = Configuration.getInstance().getProperties()
                .getProperty(INTERVAL_SECONDS);
        return pollingIntervalProperty == null ? DEFAULT_POLLING_INTERVAL_SECONDS : Integer.parseInt(pollingIntervalProperty);
    }

    public static String getResultsFilePath(){
        return Configuration.getInstance().getProperties().getProperty(FILESUBSCRIBER_FILEPATH);
    }

    public static String getSubscribers(){
        return Configuration.getInstance().getProperties().getProperty(SUBSCRIBERS);
    }

    public static String getMonitoredDistricts(){
        return Configuration.getInstance().getProperties().getProperty(MONITORED_DISTRICTS);
    }
}
