package nl.rooftopenergy.bionic.transfer;

/**
 * Created by alex on 1/2/15.
 */
public class WeatherCloudsTransfer {
    private Long dt;
    private Integer clouds;

    public WeatherCloudsTransfer(){}

    public WeatherCloudsTransfer(Long dt, Integer clouds) {
        this.dt = dt;
        this.clouds = clouds;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }
}
