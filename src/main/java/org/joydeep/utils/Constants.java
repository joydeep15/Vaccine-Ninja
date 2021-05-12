package org.joydeep.utils;

public class Constants {

    public static final String MONITORED_DISTRICTS = "districts";
    public static final String INTERVAL_SECONDS = "pollingIntervalSeconds";
    public static final Integer DEFAULT_POLLING_INTERVAL_SECONDS = 15;
    public static final String MIN_AGE = "minAge";
    public static final Integer DEFAULT_MIN_AGE = 18;
    public static final String SUBSCRIBERS = "subscribers";
    public static final String SUBSCRIBER_ID = "subscriber.";

    //Twilio Subscriber Config Properties
    public static final String TWILIO_ID= SUBSCRIBER_ID + "twilio.";
    public static final String TWILIO_SID = TWILIO_ID +"sid";
    public static final String TWILIO_AUTH_TOKEN = TWILIO_ID + "authToken";
    public static final String TWILIO_PH_NUMBERS = TWILIO_ID +"phoneNumbers";
    public static final String TWILIO_MESSAGING_SID = TWILIO_ID +"messagingSID";

    //File Subscriber Config Properties
    public static final String FILE_ID = SUBSCRIBER_ID + "file.";
    public static final String FILESUBSCRIBER_FILEPATH = FILE_ID + "filePath";


}
