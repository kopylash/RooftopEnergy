package nl.rooftopenergy.bionic.transfer;

/**
 * Created by Bess on 18.12.14.
 */
public class UserDataTransfer {

    private String userName;
    private String country;
    private String province;
    private String street;
    private Integer zipCode;
    private String city;
    private String company;
    private String panelType;
    private String description;

    public UserDataTransfer(){

    }

    /**
     * @param userName
     * @param description
     * @param panelType
     * @param company
     * @param city
     * @param zipCode
     * @param street
     * @param province
     * @param country
     */
    public UserDataTransfer(String userName, String description, String panelType, String company, String city, Integer zipCode, String street, String province, String country) {
        this.userName = userName;
        this.description = description;
        this.panelType = panelType;
        this.company = company;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.province = province;
        this.country = country;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPanelType() {
        return panelType;
    }

    public void setPanelType(String panelType) {
        this.panelType = panelType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
