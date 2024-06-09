package Controller;

import Model.Database;
import Model.Post;
import View.Alert;

import java.sql.SQLException;

public class CreatePost {
    private Post post;
    private Database database;
    public CreatePost(Post post, Database database){
        this.post = post;
        this.database = database;
    }
    public boolean posted(){
        boolean posted = false;
        String insert = "INSERT INTO posts (ID, Content, User, DateTime) VALUES (?, ?, ?, ?)";
        try{
            database.getStatement().execute(insert);
            posted = true;
        }catch (SQLException e){
            new Alert(e.getMessage(), null);
            posted = false;
        }
        return posted;
    }

}
