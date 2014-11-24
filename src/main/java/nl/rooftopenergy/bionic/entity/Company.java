package nl.rooftopenergy.bionic.entity;

import javax.persistence.*;

/**
 * Created by UFO on 17.11.2014.
 */
@Entity
@Table(name = "Company")
public class Company {
    private Integer companyId;
    private String companyName;
    private String town;
    private String street;
    private Integer zipcode;

    @Id
    @Column(name = "CompanyID")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "CompanyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "Town")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Basic
    @Column(name = "Street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "Zipcode")
    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (companyId != company.companyId) return false;
        if (companyName != null ? !companyName.equals(company.companyName) : company.companyName != null)
            return false;
        if (street != null ? !street.equals(company.street) : company.street != null) return false;
        if (town != null ? !town.equals(company.town) : company.town != null) return false;
        if (zipcode != null ? !zipcode.equals(company.zipcode) : company.zipcode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        return result;
    }
}
