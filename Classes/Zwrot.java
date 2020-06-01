package Classes;

public class Zwrot{
	private int id_zwrotu;
	    private String data_zwrotu;
	    private int id_wypozyczenia;
	    private int id_pracownika;
	    private int liczba_dni;

	    public Zwrot(String data_zwrotu, int id_wypozyczenia, int id_pracownika, int liczba_dni) {
	        this.data_zwrotu = data_zwrotu;
	        this.id_wypozyczenia = id_wypozyczenia;
	        this.id_pracownika = id_pracownika;
	        this.liczba_dni = liczba_dni;
	    }

	    public int getId_zwrotu() {
	        return id_zwrotu;
	    }

	    public String getData_zwrotu() {
	        return data_zwrotu;
	    }

	    public int getId_wypozyczenia() {
	        return id_wypozyczenia;
	    }

	    public int getId_pracownika() {
	        return id_pracownika;
	    }

	    public int getLiczba_dni() {
	        return liczba_dni;
	    }
}