package Classes;

public class Ksiazka{
	private int id_ksiazki;
    private String tytul;
    private int rok_wydania;
    private boolean dostepnosc;
    private String autor;

    public Ksiazka(int id_ksiazki, String tytul, int rok_wydania, String autor) {
        this.id_ksiazki = id_ksiazki;
        this.tytul = tytul;
        this.rok_wydania = rok_wydania;
        this.autor = autor;
        this.dostepnosc = true;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public int getRok_wydania() {
        return rok_wydania;
    }

    public void setRok_wydania(int rok_wydania) {
        this.rok_wydania = rok_wydania;
    }

    public boolean isDostepnosc() {
        return dostepnosc;
    }

    public void setDostepnosc(boolean dostepnosc) {
        this.dostepnosc = dostepnosc;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String id_autora) {
        this.autor = id_autora;
    }

    public int getId_ksiazki() {
        return id_ksiazki;
    }

    public void wypozycz(){
        this.dostepnosc = false;
    }

    public void zwroc(){
        this.dostepnosc = true;
    }

    @Override
    public String toString() {
        return "Ksiazka{" +
                "id_ksiazki=" + id_ksiazki +
                ", tytul='" + tytul + '\'' +
                ", rok_wydania=" + rok_wydania +
                ", dostepnosc=" + dostepnosc +
                ", autor=" + autor +
                '}';
    }
}