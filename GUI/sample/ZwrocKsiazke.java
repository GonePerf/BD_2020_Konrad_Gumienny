package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ZwrocKsiazke {
    Connection connection;
    Statement statement;
    private static int id_ksiazki;
    private static int id_pracownika;
    private static int id_wypozyczenia;

    @FXML
    Label label;

    public static void setId_ksiazki(int id,int pr, int wyp){
        id_ksiazki = id;
        id_pracownika = pr;
        id_wypozyczenia = wyp;
    }

    @FXML
    public void initialize() throws SQLException {
        connection =
                DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLCDB","sys as sysdba","Oradoc_db1");

        statement = connection.createStatement();
    }

    @FXML
    public void dispose(){
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void zwroc() throws SQLException {
        String sql = "INSERT INTO zwroty VALUES (zwroty_seq.nextval,"+id_wypozyczenia+","+id_pracownika+",SYSDATE)";
        String sql2 = "UPDATE ksiazki SET dostepnosc = 1 where id_ksiazki = "+id_ksiazki;
        statement.executeQuery(sql);
        statement.executeQuery(sql2);
        dispose();
    }

}
