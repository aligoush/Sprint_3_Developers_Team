package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class MySQLConnection {
    private static MySQLConnection instance = null;
    private Connection connection;

    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;


    private static final String URL = "jdbc:mysql://localhost/escaperoom";
    private static final String USER = "root";
    private String password;

    private MySQLConnection(){
        try {
            this.password = readPassword();
            connection = DriverManager.getConnection(URL, USER, password);
        } catch (SQLException | IOException e) {
            if(e instanceof SQLException){
                System.err.println("Error while connecting to the DB.");
            }
            if(e instanceof IOException){
                System.err.println("Error. Could not read the file.");
            } else{
                System.out.println("Couldnt connect");
            }
        }
    }

    public static MySQLConnection getInstance() throws SQLException {
        if(instance == null || instance.getConnection().isClosed()){
            instance = new MySQLConnection();
        }
        return instance;
    }

    public Connection getConnection() {
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
        Path fileName = Path.of("Escape_Room/src/Password.txt");
        String password = Files.readString(fileName);

        return password;
    }


}
