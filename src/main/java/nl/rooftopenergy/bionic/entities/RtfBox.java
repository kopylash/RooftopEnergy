package nl.rooftopenergy.bionic.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by UFO on 17.11.2014.
 */
@Entity
public class RtfBox {
    private int rtfBoxId;
    private int readingId;
    private Timestamp fromDt;
    private Timestamp toDt;
    private Integer reading1;
    private Integer reading2;
    private Timestamp created;
    private Integer solarpanels;

    @Id
    @Column(name = "RTFBoxID")
    public int getRtfBoxId() {
        return rtfBoxId;
    }

    public void setRtfBoxId(int rtfBoxId) {
        this.rtfBoxId = rtfBoxId;
    }

    @Basic
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

    @Basic
    @Column(name = "Solarpanels")
    public Integer getSolarpanels() {
        return solarpanels;
    }

    public void setSolarpanels(Integer solarpanels) {
        this.solarpanels = solarpanels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RtfBox rtfBox = (RtfBox) o;

        if (readingId != rtfBox.readingId) return false;
        if (rtfBoxId != rtfBox.rtfBoxId) return false;
        if (created != null ? !created.equals(rtfBox.created) : rtfBox.created != null) return false;
        if (fromDt != null ? !fromDt.equals(rtfBox.fromDt) : rtfBox.fromDt != null) return false;
        if (reading1 != null ? !reading1.equals(rtfBox.reading1) : rtfBox.reading1 != null) return false;
        if (reading2 != null ? !reading2.equals(rtfBox.reading2) : rtfBox.reading2 != null) return false;
        if (solarpanels != null ? !solarpanels.equals(rtfBox.solarpanels) : rtfBox.solarpanels != null) return false;
        if (toDt != null ? !toDt.equals(rtfBox.toDt) : rtfBox.toDt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rtfBoxId;
        result = 31 * result + readingId;
        result = 31 * result + (fromDt != null ? fromDt.hashCode() : 0);
        result = 31 * result + (toDt != null ? toDt.hashCode() : 0);
        result = 31 * result + (reading1 != null ? reading1.hashCode() : 0);
        result = 31 * result + (reading2 != null ? reading2.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (solarpanels != null ? solarpanels.hashCode() : 0);
        return result;
    }
}
