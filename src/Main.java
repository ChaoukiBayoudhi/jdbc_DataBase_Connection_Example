public class Main {
    public static void main(String[] args)
    {
        //DbConnection.createTable();
        //DbConnection.addRemoveUpdatePlayer("insert into player(id,name,tshirtNumber,teamName) values("+
            //    "1,'Messi',10,'Barcelon')");
        DbConnection.insertPlayerAllFields();
        System.out.println("player a été ajouté");
        DbConnection.addRemoveUpdatePlayer("insert into player(id,name,tshirtNumber,teamName) values("+
                "2,'CR7',7,'Juventus')");
        System.out.println("player a été ajouté");
        System.out.println("Liste des players :");
        DbConnection.getAllPlayers("select * from player")
        .forEach(x-> System.out.println(x));
    }
}
