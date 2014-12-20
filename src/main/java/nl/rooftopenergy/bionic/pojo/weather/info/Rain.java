package nl.rooftopenergy.bionic.pojo.weather.info;

/**
 * It describes precipitation (rain) to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/19/14.
 */
public class Rain extends Precipitation {

    public Rain(){}

    public Rain(Double threeH){
        super(threeH);
    }

}
