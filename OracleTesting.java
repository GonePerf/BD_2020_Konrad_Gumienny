import java.sql.*;

/*
Przykład na połączenie z Oracle i wysyłanie do serwera zwykłych zapytań sql
*/
public class OracleTesting {

    public static void main(String[] args) {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SID","username","password");
            Connection connection = 
                    DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCLCDB","sys as sysdba","Oradoc_db1");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select imie from czytelnicy");
            while(resultSet.next())
                System.out.println(resultSet.getString(1));

            connection.close();
        } catch(Exception e) { 
            System.out.println(e); 
        }
    }   
}