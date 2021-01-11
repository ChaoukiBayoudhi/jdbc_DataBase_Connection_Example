import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
    static final String dbUrl="jdbc:postgresql://localhost:5432/competitiondb1";
    static final String dbUser="user1";
    static final String dbPassword="user1";
    public static void createTable()
    {
        try(Connection conn= DriverManager.getConnection(dbUrl, dbUser,dbPassword)) {
            String createSql = "Create Table Player(id bigint primary key," +
                    "name varchar,"+
                    " birthdate date," +
                    "tshirtNumber int," +
                    "teamName varchar," +
                    "photo bytea) ";
            PreparedStatement ps = conn.prepareStatement(createSql);
            ps.executeUpdate();
            System.out.println("Table \"Player\" a été crée");
            ps.close();
        }
        catch (SQLException e)
        {
            System.err.format("SQL State %s\n %s", e.getSQLState(),e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static List<Player> getAllPlayers(String sqlReq)
    {
        List<Player> lstplayers=new ArrayList<>();
        ResultSet rs=null;
        try(Connection conn= DriverManager.getConnection(dbUrl, dbUser,dbPassword))
        {
            PreparedStatement ps=conn.prepareStatement(sqlReq);
            rs=ps.executeQuery();
            while(rs.next())
            {
                long id=rs.getLong(1);
                String name=rs.getString("Name");
                LocalDate birthday=rs.getDate("bith_day").toLocalDate();
                int tshirt=rs.getInt("tshirtNumber");
                Player player=new Player();
                player.setId(id);
                player.setName(name);
                player.setBirthdate(birthday);
                player.setTshirtNumber(tshirt);
                lstplayers.add(player);
            }
            rs.close();
            return lstplayers;
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

    public static void addRemoveUpdatePlayer(String updateReq)
    {
        try(Connection conn= DriverManager.getConnection(dbUrl, dbUser,dbPassword)) {
            PreparedStatement ps = conn.prepareStatement(updateReq);

            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e)
        {
            System.err.format("SQL State %s\n %s", e.getSQLState(),e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
