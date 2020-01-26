package application.archives;

import application.FileUtility;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class UserManager {

    private User activeUser;
    private ArrayList<User> users;

    public UserManager() {
        loadUsers();
    }

    /**
     *
     * @param searchName userName
     * @return the selected user
     */
    public User getUserbyName(String searchName) {
        User user;
        if(users.stream().anyMatch(x -> x.getName().equals(searchName))) {
            user =  users.stream().filter((d) -> d.getName().equals(searchName)).findFirst().get();
            return user;
        }
        else {
            if(searchName.contains("Librarian")){
                user = new User(searchName, UserRights.ADMIN);
                add(user);
                saveUsers();
                return user;
            }
            else{
                user = new User(searchName, UserRights.NORMAL);
                add(user);
                saveUsers();
                return user;
            }
        }
    }

    /**
     *
     * @param userIn add the user to arraylist
     */
    public void add(User userIn){
        users.add(userIn);
    }

    public ArrayList<User>getUsersbyName(String name){
        ArrayList<User> searchUsers = new ArrayList<>();
        if(name.length() == 0)
            return users;
        for(User user : users){
            if(user.getName().contains(name)){
                searchUsers.add(user);
            }
        }
        return searchUsers;
    }

    /**
     *
     * @return teh active user
     */
    public User getActiveUser() {
        return activeUser;
    }

    /**
     *
     * @param activeUser sets the active user
     */
    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    /**
     *
     * @return the Admin or normal
     */
    public UserRights getActiveUserRights() { return activeUser.getRights(); }

    /**
     * Loads in the user file.
     */
    public void loadUsers() {
        Path path = Paths.get("users.src");
        if (Files.exists(path))
            users = (ArrayList<User>) FileUtility.loadObject("users.src");
        else {
            users = new ArrayList<>();
            saveUsers();
        }
    }

    /**
     * saves the user file.
     */
    public void saveUsers(){
        FileUtility.saveObject("users.src", users, StandardOpenOption.CREATE);
    }







}
