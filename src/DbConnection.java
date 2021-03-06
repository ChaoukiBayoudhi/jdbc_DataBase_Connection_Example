import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbConnection {
    static final String dbUrl="jdbc:postgresql://localhost:5432/competitiondb1";
    static final String dbUser="user1";
    static final String dbPassword="user1";




    public static void createTable()
    {
        try(Connection conn= DriverManager.getConnection(dbUrl, dbUser,dbPassword)) {
            String createSql = "Create Table Player(id bigint primary key," +
                    "name varchar NOT NULL,"+
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
        //1- create Connection to DB
        try(Connection conn= DriverManager.getConnection(dbUrl, dbUser,dbPassword))
        {
            //2- create the statement
            PreparedStatement ps=conn.prepareStatement(sqlReq);
            //3-execute the query
            rs=ps.executeQuery();
            //4- iterate on the ResultSet to fill the ArrayList
            while(rs.next())
            {
                long id=rs.getLong(1);
                String name=rs.getString("Name");
                //LocalDate birthday=rs.getDate("birthdate").toLocalDate();
                int tshirt=rs.getInt("tshirtNumber");
                Player player=new Player();
                player.setId(id);
                player.setName(name);
               // player.setBirthdate(birthday);
                player.setTshirtNumber(tshirt);
                lstplayers.add(player);
            }
            rs.close();
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
    public static List<Player> getplayersByTeamAndTishirt() {
        List<Player> lstplayers = new ArrayList<>();
        ResultSet rs = null;
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sqlReq = "select name,teamName, tshirtNumber from player " +
                    "where TeamName=? and tshirtNumber in (?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sqlReq);
            System.out.println("Le team ? ");
            Scanner sc = new Scanner(System.in);
            String tn = sc.next();
            ps.setString(1, tn);
            ps.setInt(2, 7);
            ps.setInt(3, 10);
            ps.setInt(4, 11);
            rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString("Name");
                String teamname = rs.getString("teamName");
                int tshirt = rs.getInt("tshirtNumber");
                Player player = new Player();
                player.setId(id);
                player.setName(name);
                player.setTeamName(teamname);
                player.setTshirtNumber(tshirt);
                lstplayers.add(player);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.format("SQL State %s\n %s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstplayers;

    }
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static void insertPlayerAllFields()
    {
        Scanner sc=new Scanner(System.in);
        try(Connection conn= DriverManager.getConnection(dbUrl, dbUser,dbPassword)) {
            String insertReq="insert into player values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insertReq);
            System.out.print("Id ? ");
            ps.setLong(1,sc.nextInt());
            System.out.print("Name ? ");
            sc.nextLine();
            ps.setString(2, sc.nextLine());
            System.out.print("Birthday ('yyyy-MM-dd') ? ");
            String date = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            ps.setObject(3,LocalDate.parse(date,formatter));
            System.out.print("Tshirt Number ? ");
            ps.setInt(4, sc.nextInt());
            System.out.print("Team Name ? ");
            sc.nextLine();
            ps.setString(5, sc.nextLine());
            System.out.println("Photo Path (add photo name and extension) ? ");
            String photoPpath=sc.nextLine();
            File file = new File(photoPpath);
            FileInputStream fis = new FileInputStream(file);

            ps.setBinaryStream(6, fis, (int)file.length());

            ps.executeUpdate();
            System.out.println("player has been added...");
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
