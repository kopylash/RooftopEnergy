package nl.rooftopenergy.bionic.entity;

import javax.persistence.*;

/**
 * Created by UFO on 17.11.2014.
 */
@Entity
@Table(name = "RTFBox")
public class RtfBox {
    private Integer rtfBoxId;
    private Integer solarPanels;
    private Company company;

    @Id
    @Column(name = "RTFBoxID")
    public Integer getRtfBoxId() {
        return rtfBoxId;
    }

    public void setRtfBoxId(Integer rtfBoxId) {
        this.rtfBoxId = rtfBoxId;
    }
    @Basic
    @Column(name = "Solarpanels")
    public int getSolarPanels() {
        return solarPanels;
    }

    public void setSolarPanels(int solarPanels) {
        this.solarPanels = solarPanels;
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

        RtfBox rtfBox = (RtfBox) o;


        if (rtfBoxId != rtfBox.rtfBoxId) return false;
        if (solarPanels != null ? !solarPanels.equals(rtfBox.solarPanels) : rtfBox.solarPanels != null) return false;
        if (company != null ? !company.equals(rtfBox.company) : rtfBox.company != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rtfBoxId;
        result = 31 * result + (solarPanels != null ? solarPanels.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }
}
