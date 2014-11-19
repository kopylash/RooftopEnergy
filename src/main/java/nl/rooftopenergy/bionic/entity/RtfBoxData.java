package nl.rooftopenergy.bionic.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by UFO on 17.11.2014.
 */
@Entity
public class RtfBoxData {
    private int readingId;
    private Timestamp fromDt;
    private Timestamp toDt;
    private Integer reading1;
    private Integer reading2;
    private Timestamp created;
    private RtfBox rtfBox;

    @Id
    @Column(name = "ReadingID")
    public int getReadingId() {
        return readingId;
    }

    public void setReadingId(int readingId) {
        this.readingId = readingId;
    }

    @Basic
    @Column(name = "FromDt")
    public Timestamp getFromDt() {
        return fromDt;
    }

    public void setFromDt(Timestamp fromDt) {
        this.fromDt = fromDt;
    }

    @Basic
    @Column(name = "ToDt")
    public Timestamp getToDt() {
        return toDt;
    }

    public void setToDt(Timestamp toDt) {
        this.toDt = toDt;
    }

    @Basic
    @Column(name = "Reading1")
    public Integer getReading1() {
        return reading1;
    }

    public void setReading1(Integer reading1) {
        this.reading1 = reading1;
    }

    @Basic
    @Column(name = "Reading2")
    public Integer getReading2() {
        return reading2;
    }

    public void setReading2(Integer reading2) {
        this.reading2 = reading2;
    }

    @Basic
    @Column(name = "Created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
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
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (fromDt != null ? !fromDt.equals(that.fromDt) : that.fromDt != null) return false;
        if (reading1 != null ? !reading1.equals(that.reading1) : that.reading1 != null) return false;
        if (reading2 != null ? !reading2.equals(that.reading2) : that.reading2 != null) return false;
        if (toDt != null ? !toDt.equals(that.toDt) : that.toDt != null) return false;
        if (rtfBox != null ? !rtfBox.equals(that.rtfBox) : that.rtfBox != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = readingId;
        result = 31 * result + (fromDt != null ? fromDt.hashCode() : 0);
        result = 31 * result + (toDt != null ? toDt.hashCode() : 0);
        result = 31 * result + (reading1 != null ? reading1.hashCode() : 0);
        result = 31 * result + (reading2 != null ? reading2.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (rtfBox != null ? rtfBox.hashCode() : 0);
        return result;
    }
}
