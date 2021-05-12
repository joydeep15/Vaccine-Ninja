package org.joydeep.subscriber;

import lombok.extern.log4j.Log4j2;
import org.joydeep.utils.PropertyReader;

@Log4j2
public class FileSubscriberFactory implements SubscriberFactory{

    @Override
    public Subscriber generate() {
        String fileName = PropertyReader.getResultsFilePath();
        //TODO fileNameCheck
        if (fileName != null){
            log.info("Generating FileSubscriber: "+ fileName);
            return new FileSubscriber(fileName);
        }
        log.error("Unable to generate FileSubscriber. Check Configuration");
        return null;
    }
}
