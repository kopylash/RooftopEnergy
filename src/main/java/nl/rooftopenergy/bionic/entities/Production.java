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
public class Production {
    private int productionId;
    private Timestamp date;
    private Integer productionReading;

    @Id
    @Column(name = "ProductionID")
    public int getProductionId() {
        return productionId;
    }

    public void setProductionId(int productionId) {
        this.productionId = productionId;
    }

    @Basic
    @Column(name = "Date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "ProductionReading")
    public Integer getProductionReading() {
        return productionReading;
    }

    public void setProductionReading(Integer productionReading) {
        this.productionReading = productionReading;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Production that = (Production) o;

        if (productionId != that.productionId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (productionReading != null ? !productionReading.equals(that.productionReading) : that.productionReading != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productionId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (productionReading != null ? productionReading.hashCode() : 0);
        return result;
    }
}
