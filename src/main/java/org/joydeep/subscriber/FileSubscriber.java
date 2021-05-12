package org.joydeep.subscriber;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
@RequiredArgsConstructor
public class FileSubscriber implements Subscriber {

    @Getter public final String filePath;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

    @Override
    public void publish(String body) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            LocalDateTime now = LocalDateTime.now();
            writer.append(dateFormatter.format(now)).append("\t");
            writer.append(body).append("\n");
            writer.close();
        } catch (IOException e) {
            log.error("Error while writing to file:  "+filePath);
            log.error(body);
            log.error(e);
        }
    }
}
