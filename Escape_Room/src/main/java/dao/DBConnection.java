package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class DBConnection {

    protected Connection connection;
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;
    private static final String URL = "jdbc:mysql://localhost";
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
        } catch (ClassNotFoundException | SQLException e){
            System.err.println("Error while connecting to the DB.");
        }
        return connection;
    }

    public PreparedStatement getPreparedStatement(){
        return preparedStatement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void closeResultSet() {
        try {
            resultSet.close();
        } catch (SQLException ex) {
            System.err.println("Error. Couldn't close resultSet.");
        }
    }

    public void closeStatement() {
        try {
            preparedStatement.close();
        } catch (SQLException ex) {
            System.err.println("Error. Couldn't close statement.");
        }
    }

    public void closeConnection() {
        try {
            if (resultSet != null) {
                closeResultSet();
            }
            if (preparedStatement != null) {
                closeStatement();
            }
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error. Couldn't close the connection properly.");
        }
    }

    public static String readPassword() throws IOException {
        Path fileName = Path.of("src/Password.txt");
        String password = Files.readString(fileName);

        return password;
    }


}


