package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Login {
    @FXML
    TextField imie;
    @FXML
    PasswordField haslo;
    @FXML
    Label label;

    Connection connection;
    Statement statement;
    @FXML
    public void initialize() throws SQLException {
        connection =
                DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLCDB","sys as sysdba","Oradoc_db1");

        statement = connection.createStatement();
    }

    @FXML
    public void zaloguj() throws IOException {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT imie,nazwisko, id_pracownika FROM pracownicy" +
                    " where imie = '" + imie.getText() + "' AND PESEL = " + haslo.getText());
            if (resultSet.next()) {
                Controller.setPracownik(resultSet.getString(1), resultSet.getString(2),resultSet.getInt(3));
                Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
                Stage newWindow = new Stage();
                newWindow.setTitle("Biblioteka");
                newWindow.setScene(new Scene(root, 1200, 1000));
                newWindow.setAlwaysOnTop(true);
                Stage stage = (Stage) imie.getScene().getWindow();
                stage.close();
                newWindow.show();
            } else {
                label.setText("Nieprawidłowe dane!!!");
            }
        }
        catch (SQLException e){
            label.setText("Znaki niedozwolone");
        }

    }
}
