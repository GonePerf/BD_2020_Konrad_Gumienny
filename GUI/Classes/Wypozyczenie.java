package Classes;

public class Wypozyczenie{
	private int id_wypozyczenia;
    private String data_wypozyczenia;
    private String tytul_ksiazki;
    private String imie_i_nazwisko_czytelnika;
    private String data_zwrotu;

    public Wypozyczenie(int id_wypozyczenia, String data_wypozyczenia, String tytul_ksiazki, String imie_i_nazwisko_czytelnika, String data_zwrotu) {
        this.id_wypozyczenia = id_wypozyczenia;
        this.data_wypozyczenia = data_wypozyczenia;
        this.tytul_ksiazki = tytul_ksiazki;
        this.imie_i_nazwisko_czytelnika = imie_i_nazwisko_czytelnika;
        this.data_zwrotu = data_zwrotu;
    }

    public int getId_wypozyczenia() {
        return id_wypozyczenia;
    }

    public void setId_wypozyczenia(int id_wypozyczenia) {
        this.id_wypozyczenia = id_wypozyczenia;
    }

    public String getData_wypozyczenia() {
        return data_wypozyczenia;
    }

    public void setData_wypozyczenia(String data_wypozyczenia) {
        this.data_wypozyczenia = data_wypozyczenia;
    }

    public String getTytul_ksiazki() {
        return tytul_ksiazki;
    }

    public void setTytul_ksiazki(String tytul_ksiazki) {
        this.tytul_ksiazki = tytul_ksiazki;
    }

    public String getImie_i_nazwisko_czytelnika() {
        return imie_i_nazwisko_czytelnika;
    }

    public void setImie_i_nazwisko_czytelnika(String imie_i_nazwisko_czytelnika) {
        this.imie_i_nazwisko_czytelnika = imie_i_nazwisko_czytelnika;
    }

    public String getData_zwrotu() {
        return data_zwrotu;
    }

    public void setData_zwrotu(String data_zwrotu) {
        this.data_zwrotu = data_zwrotu;
    }

    @Override
    public String toString() {
        return "Tytuł: "+getTytul_ksiazki()+" Autor: " +getData_zwrotu()+
                " Wypożyczający: "+getImie_i_nazwisko_czytelnika()+" Data wypożyczenia: "+getData_wypozyczenia();
    }
}