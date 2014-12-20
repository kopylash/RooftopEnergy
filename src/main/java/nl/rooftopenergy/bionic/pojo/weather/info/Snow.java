package nl.rooftopenergy.bionic.pojo.weather.info;


/**
 * It describes precipitation (snow) to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/19/14.
 */
public class Snow extends Precipitation {

    public Snow(){}

    public Snow(Double threeH){
        super(threeH);
    }
}
