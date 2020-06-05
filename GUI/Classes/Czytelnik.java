package Classes;

public class Czytelnik{
    private int id_czytelnika;
    private String imie;
    private String nazwisko;
    private long pesel;
    private String data_urodzenia;
    private int wypozyczenia;

    public Czytelnik(int id_czytelnika, String imie, String nazwisko ){
        this.id_czytelnika = id_czytelnika;
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
    @Override
    public String toString(){
        return getImie()+" "+getNazwisko();
    }

}