package cz.antoninvf.beans;

import java.util.Date;

public class User {
    private int userId;
    private String fullName;
    private String email;
    private String hashedPassword;
    private Date createdAt;
    private Date updatedAt;

    public User(int userId, String fullName, String email, String hashedPassword, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
