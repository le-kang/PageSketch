package au.edu.uts.aip.pagesketch;

import java.sql.*;
import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Le Kang
 */
public class DAO {
    protected final DataSource ds;
    
    public DAO() throws NamingException {
        this.ds = (DataSource)InitialContext.doLookup("jdbc/aip");
    }
    
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
            } else {
                ps.setString(index, (String) arg);
            }
            index++;
        }
        return ps;
    }
}
