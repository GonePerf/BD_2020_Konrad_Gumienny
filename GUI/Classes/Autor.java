package Classes;

public class Autor{
	private int id_autora;
    private String imie;
    private String nazwisko;

    public Autor(int id_autora, String imie, String nazwisko) {
        this.id_autora = id_autora;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getId_autora() {
        return id_autora;
    }

    @Override
    public String toString() {
        return getImie()+" "+getNazwisko();
    }
}