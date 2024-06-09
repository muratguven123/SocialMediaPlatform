package Controller;

import Model.Database;
import Model.User;
import View.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadAllUsers {
    private ArrayList<User> users;
    public ReadAllUsers(Database database,User user) {
        String selectQuery = "SELECT * FROM users";
        try{
            ResultSet rs = database.getStatement().executeQuery(selectQuery);
            while (rs.next()){
                User u = new User();
                u.setID(rs.getInt("ID"));
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                if(u.getID()!=user.getID())  users.add(u);
            }

        } catch (SQLException e) {
            new Alert(e.getMessage(),null);
        }
    }
    public ArrayList<User> getList(){
        return users;
    }
}
