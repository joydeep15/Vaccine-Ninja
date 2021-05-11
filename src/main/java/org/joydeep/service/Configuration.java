package org.joydeep.service;

import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

public class Configuration {

    private static Configuration instance = null;
    @Getter @Setter private Properties properties;
    private Configuration(){}

    public static Configuration getInstance(){

        if(instance == null){

            synchronized (Configuration.class){
                if(instance == null){
                    instance = new Configuration();
                }
            }

        }
        return instance;
    }

}
