package au.edu.uts.aip.pagesketch;

/**
 *
 * @author Le Kang
 */
public class Following {
    private String username;
    private String followedUser;

    /**
     * 
     * @param username
     * @param followedUser 
     */
    public Following(String username, String followedUser) {
        this.username = username;
        this.followedUser = followedUser;
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
    public String getFollowedUser() {
        return followedUser;
    }

    /**
     * 
     * @param followedUser 
     */
    public void setFollowedUser(String followedUser) {
        this.followedUser = followedUser;
    }
}
