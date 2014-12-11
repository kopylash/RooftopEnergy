package nl.rooftopenergy.bionic.entity;

import javax.persistence.*;

/**
 * Created by UFO on 17.11.2014.
 */
@Entity
@Table(name = "User")
public class User {
    private Integer userId;
    private String username;
    private String email;
    private Company company;

    @Id
    @Column(name = "UserID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "Username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne
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

        User user = (User) o;

        if (userId != user.userId) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (company != null ? !company.equals(user.company) : user.company != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }
}
