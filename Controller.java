package sample;

import Classes.Ksiazka;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    Connection connection;
    Statement statement;
    private ObservableList<Ksiazka> listaDostepnychKsiazek = FXCollections.observableArrayList();
    @FXML
    TableView<Ksiazka> tbl_view_dostepne;
    @FXML
    TableColumn<Ksiazka, String> dostepne_tytul;
    @FXML
    TableColumn<Ksiazka, String> dostepne_autor;
    @FXML
    TableColumn<Ksiazka, Integer> dostepne_rok_wydania;
    @FXML
    TextField szukaj_dostepne;
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
        ResultSet resultSet = statement.executeQuery(
                "select id_ksiazki, tytul, rok_wydania, dostepnosc, autorzy.imie, autorzy.nazwisko " +
                        "from ksiazki, autorzy " +
                        "where ksiazki.dostepnosc = 1 and ksiazki.id_autora = autorzy.id_autora");
        while(resultSet.next()){


            Ksiazka ksiazka = new Ksiazka(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(5)+" "+resultSet.getString(6));
            System.out.println(ksiazka.toString());
            listaDostepnychKsiazek.add(ksiazka);
        }
        tbl_view_dostepne.setItems(listaDostepnychKsiazek);
    }
    public void getAutor(){

    }

    @FXML
    public void setTblDostepne() throws SQLException {

        listaDostepnychKsiazek.clear();

        ResultSet resultSet = statement.executeQuery(
                "select id_ksiazki, tytul, rok_wydania, dostepnosc, autorzy.imie, autorzy.nazwisko " +
                        "from ksiazki, autorzy " +
                        "where ksiazki.tytul like '"+szukaj_dostepne.getText()+"%' and ksiazki.dostepnosc = 1 and ksiazki.id_autora = autorzy.id_autora");
        while(resultSet.next()){


            Ksiazka ksiazka = new Ksiazka(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(5)+" "+resultSet.getString(6));
            System.out.println(ksiazka.toString());
            listaDostepnychKsiazek.add(ksiazka);
        }
        tbl_view_dostepne.setItems(listaDostepnychKsiazek);
    }

    @FXML
    public void setTblNiedostepne(){}

}
