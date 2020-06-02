package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class DodajCzytelnika {
    Connection connection;
    Statement statement;
    @FXML
    TextField imie;
    @FXML
    TextField nazwisko;
    @FXML
    TextField pesel;
    @FXML
    DatePicker data_ur;
    @FXML
    Button button;

    @FXML
    public void initialize() throws SQLException {
        connection =
                DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLCDB","sys as sysdba","Oradoc_db1");

        statement = connection.createStatement();

    }
    @FXML
    public void dodajCzytelnikaDoBazy() throws SQLException {

        String query = "INSERT INTO czytelnicy VALUES (czytelnicy_seq.nextval, '"+imie.getText()+"', "+nazwisko.getText()+", "+pesel.getText()+", "+data_ur.getEditor().getText()+");";
        statement.executeQuery(query);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void dispose(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }


}
