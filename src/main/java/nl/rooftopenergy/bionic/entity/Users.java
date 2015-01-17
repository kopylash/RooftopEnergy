package nl.rooftopenergy.bionic.entity;

import javax.persistence.*;

/**
 * Created by Владислав on 14.01.2015.
 */
@Entity
@Table(name = "users")
public class Users {
    private String username;
    private String password;
    private Boolean enabled;
    private User user;

    @Id
    @Column(name="username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Basic
    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Basic
    @Column(name = "enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @OneToOne
    @JoinColumn(name = "UserID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
