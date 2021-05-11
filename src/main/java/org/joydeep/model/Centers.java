package org.joydeep.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@ToString
public class Centers {

    @Getter @Setter private List<Center> centers;

    public void filter(Predicate<Center> predicate){

        List<Center> filteredCenters = new ArrayList<>();

        for (Center center : centers){
            if(predicate.test(center)){
                filteredCenters.add(center);
            }
        }
        this.setCenters(filteredCenters);
    }


}
