package nl.rooftopenergy.bionic.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by UFO on 17.11.2014.
 */
@Entity
@Table(name = "Consumption")
public class Consumption {
    private Integer consumptionId;
    private Timestamp dateReading;
    private Integer consumptionReading;
    private Company company;

    @Id
    @Column(name = "ConsumptionID")
    public Integer getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(Integer consumptionId) {
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
    @Column(name = "ConsumptionReading")
    public Integer getConsumptionReading() {
        return consumptionReading;
    }

    public void setConsumptionReading(Integer consumptionReading) {
        this.consumptionReading = consumptionReading;
    }

    @ManyToOne
    @JoinColumn(name = "CompanyID")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Consumption that = (Consumption) o;

        if (consumptionId != that.consumptionId) return false;
        if (consumptionReading != null ? !consumptionReading.equals(that.consumptionReading) : that.consumptionReading != null)
            return false;
        if (dateReading != null ? !dateReading.equals(that.dateReading) : that.dateReading != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = consumptionId;
        result = 31 * result + (dateReading != null ? dateReading.hashCode() : 0);
        result = 31 * result + (consumptionReading != null ? consumptionReading.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }
}
