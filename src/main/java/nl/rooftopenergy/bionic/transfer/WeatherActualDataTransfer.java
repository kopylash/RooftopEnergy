package nl.rooftopenergy.bionic.transfer;

/**
 * Provides data to join data such as sunrise afl sunset for
 * weather forecast.
 *
 * Created by alex on 1/2/15.
 */
public class WeatherActualDataTransfer {
    private Long dt;
    private Long sunrise;
    private Long sunset;
    private Double speed;
    private Double deg;
    private String description;

    public WeatherActualDataTransfer(){}

    public WeatherActualDataTransfer(Long dt, Long sunrise,
                                     Long sunset, Double speed,
                                     Double deg, String description) {
        this.dt = dt;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.speed = speed;
        this.deg = deg;
        this.description = description;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
