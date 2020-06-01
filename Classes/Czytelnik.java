package Classes;

public class Czytelnik{
    private int id_czytelnika;
    private String imie;
    private String nazwisko;
    private long pesel;
    private String data_urodzenia;
    private int wypozyczenia;

    public Czytelnik(String imie, String nazwisko, long pesel, String data_urodzenia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.data_urodzenia = data_urodzenia;
        this.wypozyczenia = 0;
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

    public String getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(String data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public int getId_czytelnika() {
        return id_czytelnika;
    }

    public int getWypozyczenia() {
        return wypozyczenia;
    }

    //FUNCTIONS FOR CZYTELNIK

    public boolean wypozycz_ksiazke(){
        if(wypozyczenia < 10) {
            wypozyczenia++;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean zwroc_ksiazke(){
        if(wypozyczenia > 0) {
            wypozyczenia--;
            return true;
        }
        else{
            return false;
        }
    }

    public String aktualizuj_czytelnika(){
        return "UPDATE czytelnicy SET imie = "+getImie()
                +", nazwisko = "+getNazwisko()+", PESEL = "+ getPesel()
                +", data_urodzenia = "+getData_urodzenia()+", wypozyczenia = "+getWypozyczenia()
                +" WHERE id_czytelnika = "+getId_czytelnika()+";";
    }

}