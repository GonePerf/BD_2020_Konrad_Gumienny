public class Ksiazka{
	private int id_ksiazki;
    private String tytul;
    private int rok_wydania;
    private boolean dostepnosc;
    private int id_autora;

    public Ksiazka(String tytul, int rok_wydania, int id_autora) {
        this.tytul = tytul;
        this.rok_wydania = rok_wydania;
        this.id_autora = id_autora;
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

    public int getId_autora() {
        return id_autora;
    }

    public void setId_autora(int id_autora) {
        this.id_autora = id_autora;
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
}