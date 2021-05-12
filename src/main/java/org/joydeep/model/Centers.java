package org.joydeep.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Log4j2
@ToString
public class Centers {

    @Getter @Setter private List<Center> centers;

    public void filter(Predicate<Center> predicate){
        log.debug("applying predicate: " + predicate);
        log.trace("Before Filtering: " + getCenters());
        List<Center> filteredCenters = new ArrayList<>();

        for (Center center : centers){
            if(predicate.test(center)){
                filteredCenters.add(center);
            }
        }

        this.setCenters(filteredCenters);
        log.trace("After Filtering: "+getCenters());
    }


}
