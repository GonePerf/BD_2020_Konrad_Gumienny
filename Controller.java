package sample;

import Classes.Ksiazka;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller {
    Connection connection;
    Statement statement;
    private ObservableList<Ksiazka> listaDostepnychKsiazek = FXCollections.observableArrayList();
    private ObservableList<Ksiazka> listaNiedostepnychKsiazek = FXCollections.observableArrayList();

    @FXML
    TableView<Ksiazka> tbl_view_dostepne;
    @FXML
    TableView<Ksiazka> tbl_view_niedostepne;
    @FXML
    TableColumn<Ksiazka, String> dostepne_tytul;
    @FXML
    TableColumn<Ksiazka, String> dostepne_autor;
    @FXML
    TableColumn<Ksiazka, Integer> dostepne_rok_wydania;
    @FXML
    TextField szukaj_dostepne;
    @FXML
    TextField szukaj_niedostepne;
    @FXML
    Button btn_wypozycz;
    @FXML
    Button btn_zwroc;

    @FXML
    public void initialize() {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            dostepne_tytul.setCellValueFactory(new PropertyValueFactory<>("tytul"));
            dostepne_autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
            dostepne_rok_wydania.setCellValueFactory(new PropertyValueFactory<>("rok_wydania"));
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
                "select id_ksiazki, tytul, rok_wydania, dostepnosc, autorzy.imie, autorzy.nazwisko " +
                        "from ksiazki, autorzy " +
                        "where ksiazki.dostepnosc = 0 and ksiazki.id_autora = autorzy.id_autora");
        while(resultSet.next()){


            Ksiazka ksiazka = new Ksiazka(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(5)+" "+resultSet.getString(6));
            //System.out.println(ksiazka.toString());
            listaNiedostepnychKsiazek.add(ksiazka);
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
                "select id_ksiazki, tytul, rok_wydania, dostepnosc, autorzy.imie, autorzy.nazwisko " +
                        "from ksiazki, autorzy " +
                        "where ksiazki.tytul like '"+szukaj_niedostepne.getText()+"%' and ksiazki.dostepnosc = 0 and ksiazki.id_autora = autorzy.id_autora");
        while(resultSet.next()){


            Ksiazka ksiazka = new Ksiazka(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(5)+" "+resultSet.getString(6));
            //System.out.println(ksiazka.toString());
            listaNiedostepnychKsiazek.add(ksiazka);
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
}
