package sample;

import Classes.Ksiazka;
import Classes.Wypozyczenie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class Info {
    Connection connection;
    Statement statement;
    private ObservableList<Wypozyczenie> listaWypozyczen = FXCollections.observableArrayList();
    @FXML
    TableView<Wypozyczenie> tabela;
    @FXML
    TableColumn<Wypozyczenie, String> tytul;
    @FXML
    TableColumn<Wypozyczenie,String> imie_i_nazwisko;
    @FXML
    TableColumn<Wypozyczenie, String> data_wyp;
    @FXML
    TableColumn<Wypozyczenie, String> data_zwr;
    @FXML
    Label odDnia;

    @FXML
    public void initialize() throws SQLException {
        tytul.setCellValueFactory(new PropertyValueFactory<>("tytul_ksiazki"));
        imie_i_nazwisko.setCellValueFactory(new PropertyValueFactory<>("imie_i_nazwisko_czytelnika"));
        data_wyp.setCellValueFactory(new PropertyValueFactory<>("data_wypozyczenia"));
        data_zwr.setCellValueFactory(new PropertyValueFactory<>("data_zwrotu"));
        connection =
                DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLCDB","sys as sysdba","Oradoc_db1");

        statement = connection.createStatement();
        listCreate();

    }
    @FXML
    public void listCreate() throws SQLException {
        listaWypozyczen.clear();
        ResultSet resultSet = statement.executeQuery(
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

            wypozyczenie = new Wypozyczenie(resultSet.getInt(1), data_wypozyczenia, tytul_ksiazki, imieNazwisko,data_zwrotu,resultSet.getInt(6));

            //System.out.println(wypozyczenie.toString());
            listaWypozyczen.add(wypozyczenie);
        }
        tabela.setItems(listaWypozyczen);
    }
    @FXML
    public void listaZwróconych() throws SQLException {
        listaWypozyczen.clear();
        ResultSet resultSet = statement.executeQuery(
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
            if(!data_zwrotu.equals("-")){
                wypozyczenie = new Wypozyczenie(resultSet.getInt(1), data_wypozyczenia, tytul_ksiazki, imieNazwisko, data_zwrotu, resultSet.getInt(6));

                System.out.println(wypozyczenie.toString());
                listaWypozyczen.add(wypozyczenie);
            }
        }
        tabela.setItems(listaWypozyczen);
    }
    @FXML
    public void listaNieZwróconych() throws SQLException {
        listaWypozyczen.clear();
        ResultSet resultSet = statement.executeQuery(
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
            if(data_zwrotu.equals("-")){
                wypozyczenie = new Wypozyczenie(resultSet.getInt(1), data_wypozyczenia, tytul_ksiazki, imieNazwisko,data_zwrotu,resultSet.getInt(6));

                System.out.println(wypozyczenie.toString());
                listaWypozyczen.add(wypozyczenie);
            }

        }
        tabela.setItems(listaWypozyczen);
    }
    @FXML
    public void getInfo() throws SQLException {
        if(tabela.getSelectionModel().getSelectedIndex() == -1){
            System.out.println("Nie wybrano");
        }
        else{
            if(tabela.getSelectionModel().getSelectedItem().getData_zwrotu().equals("-")){
                ResultSet resultSet = statement.executeQuery("SELECT SUM_DAYS('"+tabela.getSelectionModel().getSelectedItem().getData_wypozyczenia()+"',SYSDATE) FROM dual");
                if(resultSet.next()) odDnia.setText("Wypożyczona od: "+resultSet.getInt(1)+" dni");
            }
            else {
                ResultSet resultSet = statement.executeQuery("SELECT SUM_DAYS('"+tabela.getSelectionModel().getSelectedItem().getData_wypozyczenia()+"','"+
                        tabela.getSelectionModel().getSelectedItem().getData_zwrotu()+"') FROM dual");
                if(resultSet.next()) odDnia.setText("Wypożyczona przez: "+resultSet.getInt(1)+" dni");
            }
        }
    }
}
