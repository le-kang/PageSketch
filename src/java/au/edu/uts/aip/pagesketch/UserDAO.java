package au.edu.uts.aip.pagesketch;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Le Kang
 */
public class UserDAO extends DAO{
    public UserDAO() throws NamingException {
        super();
    }
    
    /**
     * 
     * @param user
     * @throws NoSuchAlgorithmException
     */
    public void create(User user) throws NoSuchAlgorithmException {
        String query = "INSERT INTO USERS "
                + "(USERNAME, PASSWORD, FULL_NAME, EMAIL, BIO) VALUES "
                + "(?, ?, ?, ?, ?)";
        String username = user.getUsername();
        String password = Sha.hash256(user.getPassword());
        String fullName = user.getFullName();
        String email = user.getEmail();
        String bio = user.getBio();
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, username, password, fullName, email, bio)) {
            ps.executeUpdate();    
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    /**
     * 
     * @param username
     * @return 
     */
    public User find(String username) {
        String query = "SELECT * FROM USERS WHERE USERNAME = ?";
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, username);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setUsername(username);
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setBio(rs.getString("bio"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param username
     * @return 
     */
    public ArrayList<Activity> findAllActivity(String username) {
        ArrayList<Activity> activities = new ArrayList();
        String query = "SELECT * FROM USER_ACTIVITIES WHERE USERNAME = ? ORDER BY CREATED_AT DESC";
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, username);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setUsername(rs.getString("username"));
                activity.setAction(rs.getString("user_action"));
                activity.setPageName(rs.getString("page_name"));
                activity.setPageId(rs.getString("page_id"));
                activity.setCreatedAt(rs.getTimestamp("created_at"));
                activities.add(activity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activities;
    }
}
