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
public class Consumption {
    private int consumptionId;
    private Timestamp dateReading;
    private Integer consuptionReading;

    @Id
    @Column(name = "ConsumptionID")
    public int getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(int consumptionId) {
        this.consumptionId = consumptionId;
    }

    @Basic
    @Column(name = "DateReading")
    public Timestamp getDateReading() {
        return dateReading;
    }

    public void setDateReading(Timestamp dateReading) {
        this.dateReading = dateReading;
    }

    @Basic
    @Column(name = "ConsuptionReading")
    public Integer getConsuptionReading() {
        return consuptionReading;
    }

    public void setConsuptionReading(Integer consuptionReading) {
        this.consuptionReading = consuptionReading;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Consumption that = (Consumption) o;

        if (consumptionId != that.consumptionId) return false;
        if (consuptionReading != null ? !consuptionReading.equals(that.consuptionReading) : that.consuptionReading != null)
            return false;
        if (dateReading != null ? !dateReading.equals(that.dateReading) : that.dateReading != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = consumptionId;
        result = 31 * result + (dateReading != null ? dateReading.hashCode() : 0);
        result = 31 * result + (consuptionReading != null ? consuptionReading.hashCode() : 0);
        return result;
    }
}
