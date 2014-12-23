package nl.rooftopenergy.bionic.transfer;

/**
 * Created by Alex Iakovenko on 12/18/14.
 */
public class WeatherDailyDataTransfer{

    private Long dt;
    private Double tempDay;
    private Double tempNight;
    private Double pressure;
    private Integer humidity;
    private String sky;
    private String skyDescription;
    private String skyIcon;
    private Integer clouds;
    private Double wind;

    public WeatherDailyDataTransfer(){}

    public WeatherDailyDataTransfer(Long dt, Double tempDay, Double tempNight, Double pressure,
                                    Integer humidity, String sky, String skyDescription,
                                    String skyIcon, Integer clouds, Double wind) {
        this.dt = dt;
        this.tempDay = tempDay;
        this.tempNight = tempNight;
        this.pressure = pressure;
        this.humidity = humidity;
        this.sky = sky;
        this.skyDescription = skyDescription;
        this.skyIcon = skyIcon;
        this.clouds = clouds;
        this.wind = wind;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Double getTempDay() {
        return tempDay;
    }

    public void setTempDay(Double tempDay) {
        this.tempDay = tempDay;
    }

    public Double getTempNight() {
        return tempNight;
    }

    public void setTempNight(Double tempNight) {
        this.tempNight = tempNight;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public String getSkyDescription() {
        return skyDescription;
    }

    public void setSkyDescription(String skyDescription) {
        this.skyDescription = skyDescription;
    }

    public void setSkyIcon(String skyIcon) {
        this.skyIcon = skyIcon;
    }

    public String getSkyIcon() {
        return skyIcon;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Double getWind() {
        return wind;
    }

    public void setWind(Double wind) {
        this.wind = wind;
    }
}
