package nl.rooftopenergy.bionic.pojo.weather.info;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * It describes temperature to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/18/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature implements Serializable {
    private Double day;
    private Double min;
    private Double max;
    private Double night;
    private Double eve;
    private Double morn;

    public Temperature(){}

    public Temperature(Double day, Double min, Double max, Double night, Double eve, Double morn) {
        this.day = day;
        this.min = min;
        this.max = max;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    /**
     * Gets day temperature, Kelvin (subtract 273.15 to convert to Celsius)
     * @return day temperature.
     */
    public Double getDay() {
        return day;
    }

    /**
     * Sets day temperature, Kelvin (subtract 273.15 to convert to Celsius)
     * @param day temperature.
     */
    public void setDay(Double day) {
        this.day = day;
    }

    /**
     * Gets min daily temperature, Kelvin
     * @return min daily temperature.
     */
    public Double getMin() {
        return min;
    }

    /**
     * Sets min daily temperature, Kelvin
     * @param min temperature.
     */
    public void setMin(Double min) {
        this.min = min;
    }

    /**
     * Gets max daily temperature, Kelvin
     * @return max daily temperature.
     */
    public Double getMax() {
        return max;
    }

    /**
     * Sets max daily temperature, Kelvin
     * @param max daily temperature.
     */
    public void setMax(Double max) {
        this.max = max;
    }

    /**
     * Gets night temperature, Kelvin
     * @return night temperature.
     */
    public Double getNight() {
        return night;
    }

    /**
     * Sets night temperature, Kelvin
     * @param night temperature.
     */
    public void setNight(Double night) {
        this.night = night;
    }

    /**
     * Gets evening temperature, Kelvin
     * @return evening temperature.
     */
    public Double getEve() {
        return eve;
    }

    /**
     * Sets evening temperature, Kelvin
     * @param eve evening temperature.
     */
    public void setEve(Double eve) {
        this.eve = eve;
    }

    /**
     * Gets morning temperature, Kelvin
     * @return morning temperature.
     */
    public Double getMorn() {
        return morn;
    }

    /**
     * Sets morning temperature, Kelvin
     * @param morn temperature.
     */
    public void setMorn(Double morn) {
        this.morn = morn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Temperature that = (Temperature) o;

        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (eve != null ? !eve.equals(that.eve) : that.eve != null) return false;
        if (max != null ? !max.equals(that.max) : that.max != null) return false;
        if (min != null ? !min.equals(that.min) : that.min != null) return false;
        if (morn != null ? !morn.equals(that.morn) : that.morn != null) return false;
        if (night != null ? !night.equals(that.night) : that.night != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (min != null ? min.hashCode() : 0);
        result = 31 * result + (max != null ? max.hashCode() : 0);
        result = 31 * result + (night != null ? night.hashCode() : 0);
        result = 31 * result + (eve != null ? eve.hashCode() : 0);
        result = 31 * result + (morn != null ? morn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "day=" + day +
                ", min=" + min +
                ", max=" + max +
                ", night=" + night +
                ", eve=" + eve +
                ", morn=" + morn +
                '}';
    }
}
