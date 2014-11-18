package nl.rooftopenergy.bionic.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by UFO on 17.11.2014.
 */
@Entity
public class Companies {
    private int companyId;
    private String companyName;
    private String town;
    private String street;
    private Integer zipcode;

    @Id
    @Column(name = "CompanyID")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
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

        Companies companies = (Companies) o;

        if (companyId != companies.companyId) return false;
        if (companyName != null ? !companyName.equals(companies.companyName) : companies.companyName != null)
            return false;
        if (street != null ? !street.equals(companies.street) : companies.street != null) return false;
        if (town != null ? !town.equals(companies.town) : companies.town != null) return false;
        if (zipcode != null ? !zipcode.equals(companies.zipcode) : companies.zipcode != null) return false;

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
