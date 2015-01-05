package nl.rooftopenergy.bionic.pojo.weather.info;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * It describes weather forecast to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by Alex Iakovenko 12/18/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoSys implements Serializable {
    private String pod;

    public InfoSys(){}

    public InfoSys(String pod) {
        this.pod = pod;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoSys infoSys = (InfoSys) o;

        if (pod != null ? !pod.equals(infoSys.pod) : infoSys.pod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return pod != null ? pod.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "InfoSys{" +
                "pod='" + pod + '\'' +
                '}';
    }
}
