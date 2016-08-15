package au.edu.uts.aip.pagesketch;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

/**
 * This class stores basic information of a user
 *
 * @author Le Kang
 */
public class User {
    @Size(min=1, max=30, message="Username is mandatory and no more than 30 characters.")
    private String username;
    @Size(min=8, max=20, message="Password is mandatory and between 8 and 20 characters.")
    private String password;
    @Size(min=1, message="Full name is mandatory")
    private String fullName;
    @Pattern(regexp="^(.+)@(.+)$", message="Email is empty or invalid")
    private String email;
    private String bio;
    private Date createdAt;

    /**
     * Get the username of the user 
     * 
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set the password of the user
     * 
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the password of the user
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the full name of the user
     * 
     * @return 
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Set the full name of the user
     * 
     * @param fullName 
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Get the email of the user
     * 
     * @return 
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the user
     * 
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the bio of the user
     * 
     * @return 
     */
    public String getBio() {
        return bio;
    }

    /**
     * Set the bio of the user
     * 
     * @param bio 
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Get when the user is registered
     * 
     * @return 
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set when the user is registered
     * 
     * @param createdAt 
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
}
