package nl.rooftopenergy.bionic.pojo.weather.info;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * It describes precipitation to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/18/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Precipitation implements Serializable {
    @JsonProperty("3h")
    protected Double _3h;

    protected Precipitation(){}

    protected Precipitation(Double _3h) {
        this._3h = _3h;
    }

    /**
     * Gets precipitation volume for last 3 hours, mm
     * @return precipitation volume
     */
    public Double get_3h() {
        return _3h;
    }

    /**
     * Sets precipitation volume for last 3 hours, mm
     * @param _3h precipitation volume
     */
    public void set_3h(Double _3h) {
        this._3h = _3h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Precipitation precipitation = (Precipitation) o;

        if (_3h != null ? !_3h.equals(precipitation._3h) : precipitation._3h != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return _3h != null ? _3h.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RainInfo{" +
                "_3h=" + _3h +
                '}';
    }
}
