package au.edu.uts.aip.pagesketch;

/**
 *
 * @author Le Kang
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String group;

    /**
     * 
     * @param username
     * @param password
     * @param email
     * @param group 
     */
    public User(String username, String password, String email, String group) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.group = group;
    }

    /**
     * 
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return 
     */
    public String getGroup() {
        return group;
    }

    /**
     * 
     * @param group 
     */
    public void setGroup(String group) {
        this.group = group;
    }
}
