package dao;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {

    protected Connection connection;
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;
    //private static final String URL = "jdbc:mysql://localhost";
    private static final String USER = "root";
    private String password;

    public DBConnection(){
        try {
            this.password = readPassword();
        } catch (IOException e){
            System.err.println("Error. Could not read the file.");
        }
    }

    public Connection getConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, this.password);
        } catch
        return connection;
    }

    public static String readPassword() throws IOException {
        Path fileName = Path.of("src/Password.txt");
        String password = Files.readString(fileName);

        return password;
    }


}


