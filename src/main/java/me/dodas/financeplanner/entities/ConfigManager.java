package me.dodas.financeplanner.entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    Properties properties = new Properties();
    static private ConfigManager configManager = new ConfigManager();
    
    private ConfigManager(){}
    
    public static ConfigManager getInstance() {
        return configManager;
    }

    public void loadConfig(){
        try{
            FileInputStream file = new FileInputStream("properties/config.properties");
            properties.load(file);
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value){
        properties.setProperty(key, value);
    }

}