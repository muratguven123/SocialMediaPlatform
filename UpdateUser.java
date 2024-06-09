package Controller;

import Model.Database;
import Model.User;
import View.Alert;

import java.sql.SQLException;

public class UpdateUser {
    private Database database;
    private User user;
    public  UpdateUser(User user, Database database){
        this.database = database;
        this.user = user;
    }
    public boolean isEmailUsed(String email)  {
        try {
            User u = new User();
            u.setEmail(email);
            return new CreatUser(user,database).isEmailUsed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean update(){
        boolean updated = false;
        String update = "UPDATE `socialmedia`.`users` SET `ID` = '"+user.getID()+"', `firstName` = '"+user.getFirstName()+"', `LastName` = '"+user.getLastName()+"', `Email` = '"+user.getEmail()+"', `password` = '"+user.getPassword()+"' WHERE `ID` = '"+user.getID()+"'";

        try{
            database.getStatement().execute(update);
            updated = true;
        }catch (SQLException e){
            new Alert(e.getMessage(),null);
            updated = false;
        }
        return updated;
    }
}
