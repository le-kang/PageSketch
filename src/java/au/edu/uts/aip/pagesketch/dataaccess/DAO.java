package au.edu.uts.aip.pagesketch.dataaccess;

import java.sql.*;
import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * A abstract class looks up the JDBC pool and prepare statement for reuse
 * 
 * @author Le Kang
 */
public abstract class DAO {
    protected final DataSource ds;
    
    /**
     * Look up the JDBC connection pool
     * 
     * @throws NamingException 
     */
    public DAO() throws NamingException {
        this.ds = (DataSource)InitialContext.doLookup("jdbc/aip");
    }
    
    /**
     * Dynamical create prepared statement
     * 
     * @param conn
     * @param query
     * @param args
     * @return
     * @throws SQLException 
     */
    protected PreparedStatement createPreparedStatement(Connection conn, String query, Object... args) 
            throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        int index = 1;
        // inspired from the stackoverflow issue:
        // http://stackoverflow.com/questions/11777103/set-parameters-dynamically-to-prepared-statement-in-jdbc
        for (Object arg: args) {
            if (arg instanceof Date) {
                ps.setTimestamp(index, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(index, (Integer) arg);
            } else if (arg instanceof Boolean) {
                ps.setBoolean(index, (boolean) arg);
            } else {
                ps.setString(index, (String) arg);
            }
            index++;
        }
        return ps;
    }
}
