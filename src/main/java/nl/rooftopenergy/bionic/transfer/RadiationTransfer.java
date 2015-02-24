package nl.rooftopenergy.bionic.transfer;

import java.util.HashMap;

/**
 * Transfer irradiation data.
 * It contains information about geographical latitude, panel tilt
 * day and power (electricity) which could be produced for this day.
 *
 * Created by Alexander Iakovenko on 2/24/15.
 */
public class RadiationTransfer {
    private Double latitude;
    private Double panelTilt;
    private HashMap<Integer, Double> data;

    public RadiationTransfer(Double latitude, Double panelTilt){
        this.latitude = latitude;
        this.panelTilt = panelTilt;
        data = new HashMap<Integer, Double>();
    }

    public RadiationTransfer(){}

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getPanelTilt() {
        return panelTilt;
    }

    public void setPanelTilt(Double panelTilt) {
        this.panelTilt = panelTilt;
    }

    public HashMap<Integer, Double> getData() {
        return data;
    }

    public void setData(HashMap<Integer, Double> data) {
        this.data = data;
    }
}
