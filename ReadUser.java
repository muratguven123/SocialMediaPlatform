package Controller;

import Model.Database;
import Model.User;
import View.Alert;
import View.Friend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadUser {
    private boolean LoggedIn;
    private User user;
    private boolean loggedIn;
    public ReadUser(String email, String password, Database database) throws SQLException {
        String select = "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'";

        try{
            ResultSet rs = database.getStatement().executeQuery(select);
            loggedIn=rs.next();
            if(loggedIn){
                user = new User();
                user.setID(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                String findFriends= "SELECT * FROM 'friends' WHERE 'User = "+user.getID()+";";
                ResultSet rsFriends = database.getStatement().executeQuery(findFriends);
                ArrayList<Integer> friendsIDs = new ArrayList<>();
                while(rsFriends.next()){
                    friendsIDs.add(rsFriends.getInt("Friend"));

                }

            }
        }catch (SQLException e){
            new Alert(e.getMessage(),null);
        }

    }
    public boolean loggedIn(){
        return loggedIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
