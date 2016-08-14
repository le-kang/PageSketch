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
public class PageDAO extends DAO {
    public PageDAO() throws NamingException {
        super();
    }
    
    public void create(Page page) {
        String query = "INSERT INTO PAGES "
                + "(ID, PAGE_NAME, DESCRIPTION, CURRENT_VERSION, AUTHOR, PUBLISHED) VALUES "
                + "(?, ?, ?, 1, ?, ?)";
        String id = page.getId();
        String pageName = page.getName();
        String Description = page.getDescription();
        String author = page.getAuthor();
        boolean published = page.getPublished();
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, id, pageName, Description, author, published)) {
            ps.executeUpdate(); 
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void createNewVersion(String id, PageVersion pageVersion) {
        String query = "INSERT INTO PAGE_VERSIONS "
                + "(ID, VERSION, CODE) VALUES "
                + "(?, ?, ?)";
        int version = pageVersion.getVersion();
        String code = pageVersion.getCode();
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, id, version, code)) {
            ps.executeUpdate();    
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void update(Page page) {
        String query = "UPDATE PAGES "
                + "SET PAGE_NAME = ?, DESCRIPTION = ?, CURRENT_VERSION = ?, PUBLISHED = ? "
                + "WHERE ID = ?";
        String id = page.getId();
        String pageName = page.getName();
        String Description = page.getDescription();
        int currentVersion = page.getCurrentVersion();
        boolean published = page.getPublished();
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, pageName, Description, currentVersion, published, id)) {
            ps.executeUpdate(); 
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public Page find(String id) {
        String query = "SELECT * FROM PAGES WHERE ID = ?";
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, id);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Page page = new Page();
                page.setId(id);
                page.setName(rs.getString("page_name"));
                page.setDescription(rs.getString("description"));
                page.setCurrentVersion(rs.getInt("current_version"));
                page.setAuthor(rs.getString("author"));
                page.setPublished(rs.getBoolean("published"));
                page.setVersions(findAllPageVersions(id));
                return page;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean found(String name, String author) {
        String query = "SELECT * FROM PAGES WHERE PAGE_NAME = ? AND AUTHOR = ?";
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, name, author);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean found(String id, String name, String author) {
        String query = "SELECT * FROM PAGES WHERE PAGE_NAME = ? AND AUTHOR = ? AND ID != ?";
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, id, name, author);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<PageVersion> findAllPageVersions(String id) {
        ArrayList<PageVersion> versions = new ArrayList();
        String query = "SELECT * FROM PAGE_VERSIONS WHERE ID = ? ORDER BY VERSION DESC";
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, id);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PageVersion pv = new PageVersion();
                pv.setId(id);
                pv.setVersion(rs.getInt("version"));
                pv.setCode(rs.getString("code"));
                pv.setCreatedAt(rs.getTimestamp("created_at"));
                versions.add(pv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return versions;
    }
    
    public ArrayList<Page> findAllPages(String username) {
        ArrayList<Page> pages = new ArrayList();
        String query = "SELECT * FROM PAGES WHERE CREATED_BY = ?";
        try (Connection conn = this.ds.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, query, username);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Page page = new Page();
                page.setId(rs.getString("id"));
                page.setName(rs.getString("page_name"));
                page.setDescription(rs.getString("description"));
                page.setCurrentVersion(rs.getInt("current_version"));
                page.setAuthor(rs.getString("created_by"));
                page.setPublished(rs.getBoolean("published"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pages;
    }
}