package org.joydeep.utils;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.joydeep.model.District;
import org.joydeep.model.Districts;
import org.joydeep.model.State;
import org.joydeep.model.States;
import org.joydeep.service.Configuration;
import org.joydeep.utils.HttpGet;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

@Log4j2
public class Loader {

    public static Gson gson = new Gson();
    public static final String getStates = "https://cdn-api.co-vin.in/api/v2/admin/location/states";
    public static final String getDistricts = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/%d";

    public static Map<String, District> getDistrictMap() throws IOException {

        Map<String, District> districtMap = new HashMap<>();
        States states = getStates();
        for(State state : states.getStates()){
            Districts districts = getDistricts(state);
            for(District district : districts.getDistricts()){
                districtMap.put(district.getDistrict_name().toLowerCase(Locale.ROOT), district);
            }
        }

        return districtMap;

    }

    public static void loadProperties(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        Properties properties = new Properties();
        properties.load(fr);
        Configuration.getInstance().setProperties(properties);
        log.info("Properties file loaded: "+filename);
    }

    public static States getStates() throws IOException {
        String jsonData = HttpGet.sendHttpGet(getStates);
        return gson.fromJson(jsonData, States.class);
    }

    public static Districts getDistricts(State state) throws IOException {

        String jsonData = HttpGet.sendHttpGet(
                String.format(getDistricts, state.getState_id())
        );

        return gson.fromJson(jsonData, Districts.class);

    }
}
