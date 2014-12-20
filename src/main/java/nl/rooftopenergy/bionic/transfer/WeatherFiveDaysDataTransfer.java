package nl.rooftopenergy.bionic.transfer;


/**
 * Created by Alex Iakovenko on 12/18/14.
 */
public class WeatherFiveDaysDataTransfer {
    private Long dt;
    private String dateText;
    private Double rain;
    private Double snow;
    private Double tempDay;
    private Double tempNight;
    private Double pressure;
    private Integer humidity;
    private String sky;
    private String skyDescription;
    private String skyIcon;
    private Integer clouds;

    public WeatherFiveDaysDataTransfer(){}

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

    public Double getSnow() {
        return snow;
    }

    public void setSnow(Double snow) {
        this.snow = snow;
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

    public String getSkyIcon() {
        return skyIcon;
    }

    public void setSkyIcon(String skyIcon) {
        this.skyIcon = skyIcon;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherFiveDaysDataTransfer that = (WeatherFiveDaysDataTransfer) o;

        if (clouds != null ? !clouds.equals(that.clouds) : that.clouds != null) return false;
        if (dt != null ? !dt.equals(that.dt) : that.dt != null) return false;
        if (dateText != null ? !dateText.equals(that.dateText) : that.dateText != null) return false;
        if (humidity != null ? !humidity.equals(that.humidity) : that.humidity != null) return false;
        if (pressure != null ? !pressure.equals(that.pressure) : that.pressure != null) return false;
        if (rain != null ? !rain.equals(that.rain) : that.rain != null) return false;
        if (sky != null ? !sky.equals(that.sky) : that.sky != null) return false;
        if (skyDescription != null ? !skyDescription.equals(that.skyDescription) : that.skyDescription != null)
            return false;
        if (skyIcon != null ? !skyIcon.equals(that.skyIcon) : that.skyIcon != null) return false;
        if (snow != null ? !snow.equals(that.snow) : that.snow != null) return false;
        if (tempDay != null ? !tempDay.equals(that.tempDay) : that.tempDay != null) return false;
        if (tempNight != null ? !tempNight.equals(that.tempNight) : that.tempNight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dt != null ? dt.hashCode() : 0;
        result = 31 * result + (dateText != null ? dateText.hashCode() : 0);
        result = 31 * result + (rain != null ? rain.hashCode() : 0);
        result = 31 * result + (snow != null ? snow.hashCode() : 0);
        result = 31 * result + (tempDay != null ? tempDay.hashCode() : 0);
        result = 31 * result + (tempNight != null ? tempNight.hashCode() : 0);
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (sky != null ? sky.hashCode() : 0);
        result = 31 * result + (skyDescription != null ? skyDescription.hashCode() : 0);
        result = 31 * result + (skyIcon != null ? skyIcon.hashCode() : 0);
        result = 31 * result + (clouds != null ? clouds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherFiveDaysDataTransfer{" +
                "date=" + dt +
                ", dateText='" + dateText + '\'' +
                ", rain=" + rain +
                ", snow=" + snow +
                ", tempDay=" + tempDay +
                ", tempNight=" + tempNight +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", sky='" + sky + '\'' +
                ", skyDescription='" + skyDescription + '\'' +
                ", skyIcon='" + skyIcon + '\'' +
                ", clouds=" + clouds +
                '}';
    }
}
