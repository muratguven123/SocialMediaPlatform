package Model;

import View.Alert;

import java.sql.*;

public class Database {
    private String user = "root";
    private String pass = "guven123.";
    private String url = "jdbc:mysql://localhost:3306/socialmedia";
    private Statement statement;

    public Database() throws SQLException {
        try{
            Connection connection = DriverManager.getConnection(url,user,pass);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            new Alert(e.getMessage(),null);
        }
    }
    public Statement getStatement(){
        return statement;
    }

}
