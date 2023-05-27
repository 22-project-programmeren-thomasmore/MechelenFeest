package be.thomasmore.screeninfo.model;

import jakarta.persistence.*;

@Entity
public class EndUser {
    @SequenceGenerator(name = "UserSeqGen", sequenceName = "UserSeq", initialValue = 5, allocationSize = 1)
    @GeneratedValue(generator = "UserSeqGen")
    @Id
    private Integer id;
    private String emailAddress;
    private String username;
    private String password;
    private String role;
    private boolean enabled;

    private String taal; // NEDERLANDS , ENGLISH
    private boolean getUpdates;

    public EndUser(String emailAddress, String username, String password, String role, boolean getUpdates) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.role = role;
        this.getUpdates = getUpdates;
        this.enabled=false;
        taal = "NEDERLANDS";
    }

    public EndUser() {

    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isGetUpdates() {
        return getUpdates;
    }

    public void setGetUpdates(boolean getUpdates) {
        this.getUpdates = getUpdates;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTaal() {
        return taal;
    }

    public void setTaal(String taal) {
        this.taal = taal;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
