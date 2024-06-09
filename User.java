package Model;

import java.util.ArrayList;

public class User {
    private int ID;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private ArrayList<Post> posts;
    private ArrayList<Comment> comments;
    private ArrayList<Post> likes;
    private ArrayList<User> friends;
    private ArrayList<Integer> friendIds;

    public User(){
        posts = new ArrayList<>();
        comments = new ArrayList<>();
        likes = new ArrayList<>();
        friends = new ArrayList<>();
        friendIds = new ArrayList<>();
    }
    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    public String getFirstName(){
        return FirstName;
    }
    public void setFirstName(String FirstName){
        this.FirstName = FirstName;
    }
    public String getLastName(){
        return LastName;
    }
    public void setLastName(String LastName){
        this.LastName = LastName;
    }
    public String getEmail(){
        return Email;
    }
    public String getName(){
        return FirstName + " " + LastName;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }
    public String getPassword(){
        return Password;
    }
    public void setPassword(String Password){
        this.Password = Password;
    }
    public ArrayList<Post> getPosts(){
        return posts;
    }
    public void setPosts(ArrayList<Post> posts){
        this.posts = posts;
    }
    public ArrayList<Comment> getComments(){
        return comments;
    }
    public void setComments(ArrayList<Comment> comments){
        this.comments = comments;
    }
    public ArrayList<Post> getLikes(){
        return likes;
    }
    public void setLikes(ArrayList<Post> likes){
        this.likes = likes;
    }
    public ArrayList<User> getFriends(){
        return friends;
    }
    public void setFriends(ArrayList<User> friends){
        this.friends = friends;
        friendIds = new ArrayList<>();
        for(User u : friends){
            friendIds.add(u.getID());
        }
    }
        public ArrayList<Integer> getFriendsIDs(){
        ArrayList<Integer> friendsIDs = new ArrayList<>();
         for(User u : friends){
             friendsIDs.add(u.getID());
         }
         return friendsIDs;
        }
        public void setFriendsIDs(ArrayList<Integer> friendIDs){
        this.friendIds = friendIDs;
        }
        public ArrayList<Integer> getFriendIds(){
        return friendIds;
        }
        public boolean isFriend(User u){
          return friendIds.contains(u.getID());
        }
        public void addFriend(User f){
            friends.add(f);
            friendIds.add(f.getID());
        }
        public void removeFriend(User f){
        friends.remove(f);
        friendIds.remove((Integer) f.getID());

        }
}
