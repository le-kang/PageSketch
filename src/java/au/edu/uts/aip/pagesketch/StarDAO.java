package au.edu.uts.aip.pagesketch;

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
public class StarDAO extends DAO {
    public StarDAO() throws NamingException {
        super();
    }
    
    public void create(Star star) {
        String query = "INSERT INTO STARS (USERNAME, PAGE_ID) VALUES (?, ?)";
        String username = star.getUsername();
        String pageId = star.getPageId();
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, username, pageId)) {
            ps.executeUpdate(); 
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public ArrayList<String> findWhoStarred(String pageId) {
        ArrayList<String> users = new ArrayList();
        String query = "SELECT * FROM STARS WHERE PAGE_ID = ?";
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, pageId);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    public void delete(Star star) {
        String query = "DELETE FROM STARS WHERE USERNAME = ? AND PAGE_ID = ?";
        String username = star.getUsername();
        String pageId = star.getPageId();
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, username, pageId)) {
            ps.executeUpdate(); 
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
