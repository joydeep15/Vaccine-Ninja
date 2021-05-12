package org.joydeep.service;

import org.joydeep.model.Center;
import org.joydeep.model.Session;

import java.util.function.Predicate;

public class Filters {

    public static Predicate<Center> agePredicate(int minAge){

        return center -> {

            for (Session session : center.getSessions()){
                if(session.getMin_age_limit() <= minAge ){
                    return true;
                }
            }

            return false;

        };
    }

    public static Predicate<Center> availabilityPredicate(){
        return center -> {
            for (Session session : center.getSessions()){
                if(session.getAvailable_capacity() > 0 ){
                    return true;
                }
            }
            return false;
        };
    }
}
