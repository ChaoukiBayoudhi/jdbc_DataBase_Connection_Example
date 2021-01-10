public class Main {
    public static void main(String[] args)
    {
        DbConnection dbCon=new DbConnection();
        dbCon.getAllPlayers("select * from player")
        .forEach(x-> System.out.println(x));
    }
}
