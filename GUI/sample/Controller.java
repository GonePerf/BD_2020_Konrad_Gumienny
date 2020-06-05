package sample;

import Classes.Ksiazka;
import Classes.Wypozyczenie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.*;

public class Controller {
    Connection connection;
    Statement statement;
    private static String imiePracownika;
    private static String nazwiskoPracownika;
    private static int id_pracownika;
    private ObservableList<Ksiazka> listaDostepnychKsiazek = FXCollections.observableArrayList();
    private ObservableList<Wypozyczenie> listaNiedostepnychKsiazek = FXCollections.observableArrayList();

    static void setPracownik(String imie, String nazwisko, int id_pracownik){
        imiePracownika = imie;
        nazwiskoPracownika = nazwisko;
        id_pracownika = id_pracownik;
    }

    @FXML
    TableView<Ksiazka> tbl_view_dostepne;
    @FXML
    TableView<Wypozyczenie> tbl_view_niedostepne;
    @FXML
    TableColumn<Ksiazka, String> dostepne_tytul;
    @FXML
    TableColumn<Ksiazka, String> dostepne_autor;
    @FXML
    TableColumn<Ksiazka, Integer> dostepne_rok_wydania;
    @FXML
    TableColumn<Wypozyczenie,String> niedostepne_tytul;
    @FXML
    TableColumn<Wypozyczenie,String> niedostepne_autor;
    @FXML
    TableColumn<Wypozyczenie,Integer> niedostepne_wypozyczajacy;
    @FXML
    TextField szukaj_dostepne;
    @FXML
    TextField szukaj_niedostepne;
    @FXML
    Button btn_wypozycz;
    @FXML
    Button btn_zwroc;
    @FXML
    Label labelImie;
    @FXML
    Label labelNazwisko;

    @FXML
    public void initialize() {
        try{
            labelImie.setText(imiePracownika);
            labelNazwisko.setText(nazwiskoPracownika);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            dostepne_tytul.setCellValueFactory(new PropertyValueFactory<>("tytul"));
            dostepne_autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
            dostepne_rok_wydania.setCellValueFactory(new PropertyValueFactory<>("rok_wydania"));
            niedostepne_tytul.setCellValueFactory(new PropertyValueFactory<>("tytul_ksiazki"));
            niedostepne_autor.setCellValueFactory(new PropertyValueFactory<>("data_wypozyczenia"));
            niedostepne_wypozyczajacy.setCellValueFactory(new PropertyValueFactory<>("imie_i_nazwisko_czytelnika"));
            //DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SID","username","password");
            connection =
                    DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLCDB","sys as sysdba","Oradoc_db1");

            statement = connection.createStatement();
            listCreate();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void listCreate() throws SQLException {
        listaDostepnychKsiazek.clear();
        ResultSet resultSet = statement.executeQuery(
                "select id_ksiazki, tytul, rok_wydania, dostepnosc, autorzy.imie, autorzy.nazwisko " +
                        "from ksiazki, autorzy " +
                        "where ksiazki.dostepnosc = 1 and ksiazki.id_autora = autorzy.id_autora");
        while(resultSet.next()){


            Ksiazka ksiazka = new Ksiazka(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(5)+" "+resultSet.getString(6));
            //System.out.println(ksiazka.toString());
            listaDostepnychKsiazek.add(ksiazka);
        }
        tbl_view_dostepne.setItems(listaDostepnychKsiazek);

        resultSet = statement.executeQuery(
                "select id_wypozyczenia, ksiazki.tytul, czytelnicy.imie, czytelnicy.nazwisko, data_wypozyczenia, ksiazki.id_ksiazki " +
                        "from wypozyczenia "+
                        "inner join ksiazki on wypozyczenia.id_ksiazki = ksiazki.id_ksiazki " +
                        "inner join czytelnicy on wypozyczenia.id_czytelnika = czytelnicy.id_czytelnika");
        while(resultSet.next()){

            Wypozyczenie wypozyczenie;
            String tytul_ksiazki = resultSet.getString(2);
            String imieNazwisko = resultSet.getString(3) + " " + resultSet.getString(4);
            String data_wypozyczenia = resultSet.getString(5).substring(0,10);
            String data_zwrotu = "-";
//            ResultSet resultSet2 = statement.executeQuery("select data_zwrotu from zwroty where id_wypozyczenia = "+resultSet.getInt(1));
//            while (resultSet2.next()) data_zwrotu = resultSet2.getDate(0).toString();

                wypozyczenie = new Wypozyczenie(resultSet.getInt(1), data_wypozyczenia, tytul_ksiazki, imieNazwisko, data_zwrotu, resultSet.getInt(6));

                //System.out.println(wypozyczenie.toString());
                listaNiedostepnychKsiazek.add(wypozyczenie);

        }
        tbl_view_niedostepne.setItems(listaNiedostepnychKsiazek);
    }

    @FXML
    public void setTblDostepne() throws SQLException {
        if(szukaj_dostepne.getText() == "");
        else {
            listaDostepnychKsiazek.clear();

            ResultSet resultSet = statement.executeQuery(
                    "select id_ksiazki, tytul, rok_wydania, dostepnosc, autorzy.imie, autorzy.nazwisko " +
                            "from ksiazki, autorzy " +
                            "where ksiazki.tytul like '" + szukaj_dostepne.getText() + "%' and ksiazki.dostepnosc = 1 and ksiazki.id_autora = autorzy.id_autora");
            while (resultSet.next()) {


                Ksiazka ksiazka = new Ksiazka(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(5) + " " + resultSet.getString(6));
                //System.out.println(ksiazka.toString());
                listaDostepnychKsiazek.add(ksiazka);
            }

            tbl_view_dostepne.setItems(listaDostepnychKsiazek);
        }
    }

    @FXML
    public void setTblNiedostepne() throws SQLException {
        listaNiedostepnychKsiazek.clear();

        ResultSet resultSet = statement.executeQuery(
                "select id_wypozyczenia, ksiazki.tytul, czytelnicy.imie, czytelnicy.nazwisko, data_wypozyczenia, ksiazki.id_ksiazki " +
                        "from wypozyczenia "+
                        "inner join ksiazki on wypozyczenia.id_ksiazki = ksiazki.id_ksiazki " +
                        "inner join czytelnicy on wypozyczenia.id_czytelnika = czytelnicy.id_czytelnika "+
                        "where ksiazki.tytul like '" + szukaj_niedostepne.getText()+"%'");
        while(resultSet.next()){

            Wypozyczenie wypozyczenie;
            String tytul_ksiazki = resultSet.getString(2);
            String imieNazwisko = resultSet.getString(3) + " " + resultSet.getString(4);
            String data_wypozyczenia = resultSet.getString(5).substring(0,10);
            String data_zwrotu = "-";
//            ResultSet resultSet2 = statement.executeQuery("select data_zwrotu from zwroty where id_wypozyczenia = "+resultSet.getInt(1));
//            while (resultSet2.next()) data_zwrotu = resultSet2.getDate(0).toString();

                wypozyczenie = new Wypozyczenie(resultSet.getInt(1), data_wypozyczenia, tytul_ksiazki, imieNazwisko, data_zwrotu,resultSet.getInt(6));

                //System.out.println(wypozyczenie.toString());
                listaNiedostepnychKsiazek.add(wypozyczenie);

        }
        tbl_view_niedostepne.setItems(listaNiedostepnychKsiazek);
    }

    @FXML
    public void dodajKsiazke() throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("dodajKsiazke.fxml"));
        Stage newWindow = new Stage();
        newWindow.setTitle("Dodawanie książki");
        newWindow.setScene(new Scene(root));
        newWindow.setAlwaysOnTop(true);
        newWindow.show();
        if(!newWindow.isShowing()) listCreate();

    }
    @FXML
    public void dodajCzytelnika() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dodajCzytelnika.fxml"));
        Stage newWindow = new Stage();
        newWindow.setTitle("Dodawanie czytelnika");
        newWindow.setScene(new Scene(root));
        newWindow.setAlwaysOnTop(true);
        newWindow.show();
    }
    @FXML
    public void dodajAutora() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dodajAutora.fxml"));
        Stage newWindow = new Stage();
        newWindow.setTitle("Dodawanie autora");
        newWindow.setScene(new Scene(root));
        newWindow.setAlwaysOnTop(true);
        newWindow.show();
    }
    @FXML
    public void infoBtn() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("info.fxml"));
        Stage newWindow = new Stage();
        newWindow.setTitle("INFO");
        newWindow.setScene(new Scene(root));
        newWindow.setAlwaysOnTop(true);
        newWindow.show();
    }
//    @FXML
//    public void activeBtnZwroc(){
//        btn_zwroc.setDisable(false);
//    }
    @FXML
    public void wypozycz() throws IOException {
        WypozyczKsiazke.setId_ksiazki(tbl_view_dostepne.getSelectionModel().getSelectedItem().getId_ksiazki(),id_pracownika);
        Parent root = FXMLLoader.load(getClass().getResource("wypozyczKsiazke.fxml"));
        Stage newWindow = new Stage();
        newWindow.setTitle("Wypożyczanie");
        newWindow.setScene(new Scene(root));
        newWindow.setAlwaysOnTop(true);
        newWindow.show();
        newWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    listCreate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @FXML
    public void zwroc() throws IOException {
        ZwrocKsiazke.setId_ksiazki(tbl_view_niedostepne.getSelectionModel().getSelectedItem().getId_ksiazki(),id_pracownika,
                tbl_view_niedostepne.getSelectionModel().getSelectedItem().getId_wypozyczenia());
        Parent root = FXMLLoader.load(getClass().getResource("zwrocKsiazke.fxml"));
        Stage newWindow = new Stage();
        newWindow.setTitle("Wypożyczanie");
        newWindow.setScene(new Scene(root));
        newWindow.setAlwaysOnTop(true);
        newWindow.show();
    }
}
