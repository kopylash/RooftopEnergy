package nl.rooftopenergy.bionic.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by UFO on 17.11.2014.
 */
@Entity
@Table(name = "RTFBoxData")
public class RtfBoxData {
    private Integer readingId;
    private Timestamp date;
    private Integer production;
    private Integer consumption;
    private RtfBox rtfBox;

    @Id
    @Column(name = "ReadingID")
    public Integer getReadingId() {
        return readingId;
    }

    public void setReadingId(Integer readingId) {
        this.readingId = readingId;
    }

    @Basic
    @Column(name = "Date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp fromDt) {
        this.date = fromDt;
    }


    @Basic
    @Column(name = "Production")
    public Integer getProduction() {
        return production;
    }

    public void setProduction(Integer reading1) {
        this.production = reading1;
    }

    @Basic
    @Column(name = "Consumption")
    public Integer getConsumption() {
        return consumption;
    }

    public void setConsumption(Integer reading2) {
        this.consumption = reading2;
    }

    @ManyToOne
    @JoinColumn(name = "RTFBoxID")
    public RtfBox getRtfBox() {
        return rtfBox;
    }

    public void setRtfBox(RtfBox rtfBox) {
        this.rtfBox = rtfBox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RtfBoxData that = (RtfBoxData) o;

        if (readingId != that.readingId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (production != null ? !production.equals(that.production) : that.production != null) return false;
        if (consumption != null ? !consumption.equals(that.consumption) : that.consumption != null) return false;
        if (rtfBox != null ? !rtfBox.equals(that.rtfBox) : that.rtfBox != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = readingId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (production != null ? production.hashCode() : 0);
        result = 31 * result + (consumption != null ? consumption.hashCode() : 0);
        result = 31 * result + (rtfBox != null ? rtfBox.hashCode() : 0);
        return result;
    }
}
