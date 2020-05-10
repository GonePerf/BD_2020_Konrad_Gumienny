public class Pracownik{
	private int id_pracownika;
    private String imie;
    private String nazwisko;
    private long pesel;

    public Pracownik(String imie, String nazwisko, long pesel) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
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

    public long getPesel() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public int getId_pracownika() {
        return id_pracownika;
    }

    public String aktualizuj_pracownika(){
        return "UPDATE pracownicy SET imie = "+getImie()
                +", nazwisko = "+getNazwisko()+", PESEL = "+ getPesel()
                +" WHERE id_pracownika = "+getId_pracownika()+";";
    }
}