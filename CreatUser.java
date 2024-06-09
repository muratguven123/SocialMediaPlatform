package Controller;

import Model.Database;
import Model.User;
import View.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreatUser {
    private User u;
    private Database database;


    public CreatUser(User u, Database database) throws SQLException {
        this.u = u;
        this.database = database;
    }
    public void create(){
        String insert = "INSERT INTO `socialmedia`.`users` (`İD`, `firstName`, `LastName`, `Email`, `password`) VALUES (<{İD: }>, <{"+u.getFirstName()+": }>, <{"+u.getLastName()+": }>, <{"+u.getEmail()+": }>, <{"+u.getPassword()+": }>);";

        try{
            database.getStatement().execute(insert);
        }catch (SQLException e){
            new Alert(e.getMessage(),null);
        }
    }

    public boolean isEmailUsed(){
        String select = "SELECT * FROM 'users' WHERE `email` = '"+u.getEmail()+"';";
        boolean used = false;
        try{
            ResultSet rs = database.getStatement().executeQuery(select);
            used = rs.next();
        }catch (SQLException e){
            new Alert(e.getMessage(),null);
        }
        return used;
    }
    public User getUser() throws SQLException {
        u.setComments(new ArrayList<>());
        u.setFriends(new ArrayList<>());
        u.setLikes(new ArrayList<>());
        u.setPosts(new ArrayList<>());
        String select = "SELECT * FROM 'users' WHERE `email` = '"+u.getEmail()+"'AND `password` ="+u.getPassword();
        boolean used = false;
        try{
            ResultSet rs = database.getStatement().executeQuery(select);
            used = rs.next();
        }catch (SQLException e){
            new Alert(e.getMessage(),null);
        }
        return u;
    }
}
