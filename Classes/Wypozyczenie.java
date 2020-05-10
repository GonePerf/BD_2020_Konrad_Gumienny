public class Wypozyczenie{
	private int id_wypozyczenia;
    private String data_wypozyczenia;
    private int id_ksiazki;
    private int id_czytelnika;
    private int id_pracownika;

    public Wypozyczenie(String data_wypozyczenia, int id_ksiazki, int id_czytelnika, int id_pracownika) {
        this.data_wypozyczenia = data_wypozyczenia;
        this.id_ksiazki = id_ksiazki;
        this.id_czytelnika = id_czytelnika;
        this.id_pracownika = id_pracownika;
    }

    public int getId_wypozyczenia() {
        return id_wypozyczenia;
    }

    public String getData_wypozyczenia() {
        return data_wypozyczenia;
    }

    public int getId_ksiazki() {
        return id_ksiazki;
    }

    public int getId_czytelnika() {
        return id_czytelnika;
    }

    public int getId_pracownika() {
        return id_pracownika;
    }
}