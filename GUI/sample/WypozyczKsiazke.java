package sample;

import Classes.Autor;
import Classes.Czytelnik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.sql.*;

public class WypozyczKsiazke {
    Connection connection;
    Statement statement;
    private static int id_ksiazki;
    private static int id_pracownika;
    private ObservableList<Czytelnik> listaCzytelnikow = FXCollections.observableArrayList();
    @FXML
    ComboBox<Czytelnik> czytelnicy;

    @FXML
    public void initialize() throws SQLException {
        connection =
                DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLCDB","sys as sysdba","Oradoc_db1");

        statement = connection.createStatement();
        listCreate();
    }
    public void listCreate() throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "select * " +
                        "from czytelnicy");
        while(resultSet.next()){
            Czytelnik czytelnik = new Czytelnik(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            listaCzytelnikow.add(czytelnik);
        }
        czytelnicy.setItems(listaCzytelnikow);
    }
    public static void setId_ksiazki(int id,int pr){
        id_ksiazki = id;
        id_pracownika = pr;
    }

    @FXML
    public void dispose(){
        Stage stage = (Stage) czytelnicy.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void wypozycz() throws SQLException {
        String sql = "INSERT INTO wypozyczenia VALUES (wypozyczenia_seq.nextval,"+czytelnicy.getSelectionModel().getSelectedItem().getId_czytelnika()+","+id_ksiazki+",SYSDATE,2)";
        String sql2 = "UPDATE ksiazki SET dostepnosc = 0 where id_ksiazki = "+id_ksiazki;
        statement.executeQuery(sql);
        statement.executeQuery(sql2);
        dispose();
    }
}
