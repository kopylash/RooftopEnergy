package nl.rooftopenergy.bionic.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by UFO on 17.11.2014.
 */
@Entity
public class Production {
    private int productionId;
    private Timestamp dateProduction;
    private Integer productionReading;
    private Company company;

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
    public Timestamp getDateProduction() {
        return dateProduction;
    }

    public void setDateProduction(Timestamp dateProduction) {
        this.dateProduction = dateProduction;
    }

    @Basic
    @Column(name = "ProductionReading")
    public Integer getProductionReading() {
        return productionReading;
    }

    public void setProductionReading(Integer productionReading) {
        this.productionReading = productionReading;
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

        Production that = (Production) o;

        if (productionId != that.productionId) return false;
        if (dateProduction != null ? !dateProduction.equals(that.dateProduction) : that.dateProduction != null) return false;
        if (productionReading != null ? !productionReading.equals(that.productionReading) : that.productionReading != null)
            return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productionId;
        result = 31 * result + (dateProduction != null ? dateProduction.hashCode() : 0);
        result = 31 * result + (productionReading != null ? productionReading.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }
}
