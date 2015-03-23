package nl.rooftopenergy.bionic.rest.util;

import java.util.HashMap;

/**
 * Created by alex on 3/23/15.
 */
public class Location {
    private HashMap<String, Double> data;

    public Location(){
        data = new HashMap<String, Double>();
    }
    public Location(HashMap data){
        this.data = data;
    }

    public void put(String key, Double value){
         data.put(key, value);
    }
    public Double get(String key){
        return data.get(key);
    }
}
