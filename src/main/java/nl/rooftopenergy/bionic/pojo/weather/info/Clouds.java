package nl.rooftopenergy.bionic.pojo.weather.info;

import java.io.Serializable;

/**
 * It describes sky condition (cloudiness) to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/18/14.
 */
public class Clouds implements Serializable {
    private Integer all;

    public Clouds(){}
    public Clouds(Integer all) {
        this.all = all;
    }

    /**
     * Gets cloudiness to be expressed in percents.
     * @return cloudiness in %.
     */
    public Integer getAll() {
        return all;
    }

    /**
     * Sets cloudiness to be expressed in percents.
     * @return cloudiness in %.
     */
    public void setAll(Integer all) {
        this.all = all;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clouds cloud = (Clouds) o;

        if (all != null ? !all.equals(cloud.all) : cloud.all != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return all != null ? all.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Cloud{" +
                "all=" + all +
                '}';
    }
}
