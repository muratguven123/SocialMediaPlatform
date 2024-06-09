package Controller;

import Model.Database;
import Model.User;
import View.Alert;

import java.sql.SQLException;

public class RemoveFriend {
    private User user,f;
    private Database database;

    public RemoveFriend(User user, Database database,User f) {
        this.user = user;
        this.database = database;
        this.f = f;
    }
    public boolean isRemoved(){
        boolean removed = false;
        String delete = "DELETE FROM 'friends' WHERE 'User'="+user.getID()+" AND 'Friend' ="+f.getID();
        try {
            database.getStatement().execute(delete);
            removed = true;
        }catch (SQLException e) {
            new Alert(e.getMessage(),null);
            removed = false;
        }
        return removed;
    }

}
