package Controller;

import Model.Database;
import Model.Post;
import Model.User;
import View.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenerateTimeLİne {

    private ArrayList<Post> post;

    public GenerateTimeLİne(User user, Database database) {
        post = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if(user.getFriendsIDs().size()!=0){

        }
        for (int i = 0; i < user.getFriendsIDs().size(); i++) {
            sb.append("'User' = '" + user.getFriendsIDs().get(i) + "'\n");
            if(i!=user.getFriendsIDs().size()-1){
                sb.append("OR");
            }else{
                sb.append(";");
            }
            String select = "SELECT * FROM 'posts' WHERE"+sb.toString();
            try{
                ResultSet rs = database.getStatement().executeQuery(select);
                ArrayList<Integer> usersIDs = new ArrayList<>();
                while (rs.next()) {
                    Post post = new Post();
                    post.setID(rs.getInt("ID"));
                    post.setContent(rs.getString("content"));
                    usersIDs.add(rs.getInt("User"));
                    post.setDateTimeToString(rs.getNString("DateTime"));
                 //   post.add(post);
                }
            } catch (SQLException e) {
                new Alert(e.getMessage(),null);
            }
        }
        for(int i = 0; i < user.getFriendsIDs().size(); i++){
         //   post.get(i).setUser(new ReadUserByID(database,user));
        }
    }
        public ArrayList<Post> getPost() {
             return post;
    }
}
