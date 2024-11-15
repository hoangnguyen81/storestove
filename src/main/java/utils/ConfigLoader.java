package utils;

import org.checkerframework.checker.units.qual.C;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;

    private static ConfigLoader configLoader;

    public ConfigLoader(){
        properties = PropertyUtil.propertyLoader("src/main/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getPropertyByName(String key){
       return properties.getProperty(key);
    }
}
