import java.time.LocalDate;
import java.util.Objects;

public class Player {
    private long id;
    private String name;
    private LocalDate birthdate;
    private int tshirtNumber;
    private String teamName;
    private byte[] photo;

    public Player() {
    }

    public Player(long id, String name, LocalDate birthdate, int tshirtNumber, byte[] photo) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.tshirtNumber = tshirtNumber;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getTshirtNumber() {
        return tshirtNumber;
    }

    public void setTshirtNumber(int tshirtNumber) {
        this.tshirtNumber = tshirtNumber;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return tshirtNumber == player.tshirtNumber &&
                name.equals(player.name) &&
                Objects.equals(birthdate, player.birthdate) &&
                Objects.equals(teamName, player.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthdate, tshirtNumber, teamName);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", tshirtNumber=" + tshirtNumber +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
