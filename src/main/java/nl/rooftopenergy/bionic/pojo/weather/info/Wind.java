package nl.rooftopenergy.bionic.pojo.weather.info;

import java.io.Serializable;

/**
 * It describes wind parameters to parse weather forecast JSON file from
 * http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/18/14.
 */
public class Wind implements Serializable {
    private Double speed;
    private Double deg;

    public Wind(){}

    public Wind(Double speed, Double deg) {
        this.speed = speed;
        this.deg = deg;
    }

    /**
     * Gets wind speed to be expressed in mps
     * @return wind speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * Sets wind speed to be expressed in mps
     * @param speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * Gets wind direction to be expressed in degrees (meteorological)
     * @return wind direction.
     */
    public Double getDeg() {
        return deg;
    }

    /**
     * Sets wind direction to be expressed in degrees (meteorological)
     * @param deg wind direction.
     */
    public void setDeg(Double deg) {
        this.deg = deg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wind wind = (Wind) o;

        if (deg != null ? !deg.equals(wind.deg) : wind.deg != null) return false;
        if (speed != null ? !speed.equals(wind.speed) : wind.speed != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }

    @Override
    public int hashCode() {
        int result = speed != null ? speed.hashCode() : 0;
        result = 31 * result + (deg != null ? deg.hashCode() : 0);
        return result;
    }
}
