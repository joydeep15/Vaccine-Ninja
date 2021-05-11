package org.joydeep.service;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.joydeep.model.Center;
import org.joydeep.model.Centers;
import org.joydeep.model.District;
import org.joydeep.publisher.Publisher;
import org.joydeep.utils.HttpGet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Log4j2
@RequiredArgsConstructor
public class FindCentersTask implements Runnable{

    @Getter private final List<District> districts;
    @Getter private final Integer min_age;
    @Getter private final List<Publisher> publishers;
    private static final String fetchCenters = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=%d&date=%s";
    private static final Integer weeksInFuture = 6;

    Gson gson = new Gson();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @SneakyThrows
    @Override
    public void run() {

        log.info("Starting PollingTask. Thead-" + Thread.currentThread().getId());

        List<Predicate<Center>> filters = new ArrayList<Predicate<Center>>(){{
            add(Filters.availabilityPredicate());
            add(Filters.agePredicate(min_age, min_age+1));
        }};

        Centers centers = getAllCenters(districts, weeksInFuture, filters);
        log.info(centers);

//        if(!centers.getCenters().isEmpty()){
            //found available vaccine Centers
            publishers.forEach(publisher -> publisher.publish("Update: Center found. Available Centers: "+centers));
//        }

    }

    private Centers getAllCenters(List<District> districts, int weeks,
                                              List<Predicate<Center>> filters) throws IOException {
        Centers centers = new Centers();
        centers.setCenters(new ArrayList<>());
        for (District district : districts){
            Centers centersForDistrict = getCentersForDistrict(district, weeks, filters);
            centers.getCenters().addAll(centersForDistrict.getCenters());
        }
        return centers;
    }

    private Centers getCentersForDistrict(District district, int weeks,
                                              List<Predicate<Center>> filters) throws IOException {

        LocalDateTime myDateObj = LocalDateTime.now();
        Centers centers = new Centers();
        centers.setCenters(new ArrayList<>());

        for (int currWeek = 0; currWeek <= weeks; currWeek++){

            myDateObj = myDateObj.plusDays(currWeek * 7L);
            String formattedDate = dateFormatter.format(myDateObj);

            Centers centersForThisWeek = getCentersForDate(district, formattedDate);
            for (Predicate<Center> filter : filters){
                centersForThisWeek.filter(filter);
            }
            centers.getCenters().addAll(centersForThisWeek.getCenters());

        }

        return centers;

    }

    private Centers getCentersForDate(District district, String date) throws IOException {

        String jsonData = HttpGet.sendHttpGet(
                String.format(fetchCenters, district.getDistrict_id(), date)
        );
        return gson.fromJson(jsonData, Centers.class);

    }
}
