package sample;

import Classes.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;

public class DodajKsiazke {
    Connection connection;
    Statement statement;
    private ObservableList<Autor> listaAutorow = FXCollections.observableArrayList();
    @FXML
    TextField tytul_ksiazki;
    @FXML
    TextField rok_wydania;
    @FXML
    Button button;
    @FXML
    ComboBox<Autor> autorzy;

    @FXML
    public void initialize() throws SQLException {
        connection =
                DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLCDB","sys as sysdba","Oradoc_db1");

        statement = connection.createStatement();
        listCreate();
    }
    @FXML
    public void dodajKsiazkeDoBazy() throws SQLException {

        String query = "INSERT INTO ksiazki VALUES (ksiazki_seq.nextval, '"+tytul_ksiazki.getText()+"', "+rok_wydania.getText()+", 1, "+autorzy.getSelectionModel().getSelectedItem().getId_autora()+")";
        statement.executeQuery(query);
        dispose();
    }

    @FXML
    public void dispose(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public void listCreate() throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "select * " +
                        "from autorzy");
        while(resultSet.next()){
            Autor autor = new Autor(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            listaAutorow.add(autor);
        }
        autorzy.setItems(listaAutorow);

    }
}
