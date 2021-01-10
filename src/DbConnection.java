import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
    static final String dbUrl="jdbc:postgresql://localhost:5432/competitiondb";
    static final String dbUser="user1";
    static final String dbPassword="user1";
    public List<Player> getAllPlayers(String sqlReq)
    {
        List<Player> lstplayers=new ArrayList<>();
        ResultSet rs=null;
        try(Connection conn= DriverManager.getConnection(dbUrl, dbUser,dbPassword))
        {

        }
        catch (SQLException e)
        {
            System.err.format("SQL State %s\n %s", e.getSQLState(),e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return lstplayers;
    }
}
