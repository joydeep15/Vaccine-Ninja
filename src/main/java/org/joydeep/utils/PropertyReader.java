package org.joydeep.utils;

import org.joydeep.service.Configuration;

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


}
