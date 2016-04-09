
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Amarylis
 */
class DBConnector 
{
    private final static String DBURL = "jdbc:mysql://localhost:3306/loty";
    private final static String DBUser = "root";
    private final static String DBPassword = ""; //hasło do roota
    private final static String DBDriver = "com.mysql.jdbc.Driver";
    
    private Connection connection = null;
    
    public Connection setConnection()
    {
        try
        {
            Class.forName(DBDriver).newInstance();
            connection = DriverManager.getConnection(DBURL, DBUser, DBPassword);
        }
        catch( InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e )
        {
            e.printStackTrace();
        }
        
        return connection;
    }
}
