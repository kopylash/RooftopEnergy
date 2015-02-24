package nl.rooftopenergy.bionic.rest.util;

import java.util.HashMap;

/**
 * Provides API to calculate solar irradiation.
 *
 * Created by Alexander Iakovenko on 2/24/15.
 */
public class Solar {
    private Double latitude;
    private Double panelTilt;
    private Integer numberOfPoints;
    private Integer day;

    public Solar(Double latitude, Double panelTilt, Integer numberOfPoints ){
        this.latitude = latitude;
        this.panelTilt = panelTilt;
        this.numberOfPoints = numberOfPoints;
    }

    public void setDay(Integer day){
        final int FULL_YEAR = 365;
        if (day > 1 && day <= FULL_YEAR) {
            this.day = day;
        } else {
            throw new NumberFormatException("Value should be between 1 and 365");
        }
    }

    public double toRad(double value){
        return value * Math.PI / 180;
    }

    public double toDeg(double value){
        return 180 * value / Math.PI;
    }

    public double declination(int day){
        return 23.45 * Math.sin(toRad(360.0/365.0*(day-81)));
    }

    public double am(double value,int day, double fi){
        double dec = toRad(declination(day));
        double hra = toRad(15 * (value - 12));
        double elevation = Math.asin(Math.sin(dec)*Math.sin(fi)+Math.cos(dec)*Math.cos(fi)*Math.cos(hra));
        double declination = toRad(90) - elevation;
        return 1 / (1E-4 + Math.cos(declination));
    }

    public HashMap<Integer, Double> calculateTotal(){

        HashMap<Integer, Double> data = new HashMap<Integer, Double>();
        for (int i = 0; i < numberOfPoints; i++){
            double value = calculate(i);
            data.put(i+1, value);
        }
        return data;
    }

    public HashMap<Integer, Double> calculateOneDay() {
        if (day != null) {
            HashMap<Integer, Double> data = new HashMap<Integer, Double>();
            int jDay = day;
            double value = calculate(jDay);
            data.put(jDay, value);
            return data;
        }
        return null;
    }


    private  double calculate(int jDay){
        double dec = toRad(declination(jDay));
        double lat = toRad(latitude);
        double x = -(Math.sin(lat) * Math.sin(dec));
        x = x / (Math.cos(lat) * Math.cos(dec));
        if (x > 1.0)
            x = 1.0;
        if (x < -1.0)
            x = -1.0;
        double fi = Math.acos(x);
        double h = toDeg(fi * 1 / 15.0);
        double sunrise = 12.0 - h;
        double sunset = 12.0 + h;

        double stot = 0.0;
        double elevation = 0.0;
        if (h > 0) {
            for (double t = sunrise; t <= sunset; t++) {
                double am = am(t, jDay, lat);
                double x1 = Math.pow(0.7, am);
                stot = stot + 1.353 * Math.pow(x1, 0.678);
            }
//                elevation = Math.asin(Math.sin(dec) * Math.sin(lat) + Math.cos(dec) * Math.cos(lat));
            if (lat < 0) {
                elevation = toRad(90) + lat - dec;
            } else {
                elevation = toRad(90) - lat + dec;
            }
        }
        return stot * Math.sin(toRad(panelTilt) + elevation);
    }
}
