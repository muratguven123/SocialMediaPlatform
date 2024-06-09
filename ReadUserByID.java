package Controller;

import Model.Database;
import Model.User;
import View.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadUserByID{
    private User user;
    public ReadUserByID(int id, Database database){
        String select = "SELECT *FROM 'users' WHERE id = " + id;
        try{
            ResultSet rs = database.getStatement().executeQuery(select);
            rs.next();
            user = new User();
            user.setID(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
        }catch (SQLException e){
                new Alert(e.getMessage(),null);
        }
    }
    public User getUser(){
        return user;
    }
}
