package nl.rooftopenergy.bionic.pojo.weather.city;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Class describes geo location of the city where it is placed.
 * It is used for parsing forecast JSON file from http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/18/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates implements Serializable {
    private Double lon;
    private Double lat;

    public Coordinates(){}

    public Coordinates(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    /**
     * Gets city geo location, longitude;
     * @return longitude
     */
    public Double getLon() {
        return lon;
    }

    /**
     * Sets city geo location, longitude;
     * @param lon
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * Gets city geo location, latitude;
     * @return latitude
     */
    public Double getLat() {
        return lat;
    }

    /**
     * Sets city geo location, latitude;
     * @param lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (lat != null ? !lat.equals(that.lat) : that.lat != null) return false;
        if (lon != null ? !lon.equals(that.lon) : that.lon != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lon != null ? lon.hashCode() : 0;
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
