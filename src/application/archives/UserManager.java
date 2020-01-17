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
        //return null;
    }

    public String findUserString(String searchName) {
        User user;
        if(users.stream().anyMatch(x -> x.getName().equals(searchName))) {
            user =  users.stream().filter((d) -> d.getName().equals(searchName)).findFirst().get();

            return user.getName() + "BANAN";

        }
        return "null";
    }

    public void add(User userIn){
        users.add(userIn);
    }

    public void removeAtIndex(int index){
        users.remove(index);
    }

    public int userCount(){
        return users.size();
    }

    public void fillUsers(ArrayList<User> usersIn){
        users = usersIn;
    }

    public User getUserbyNamesss(String name){
        for(User user : users){
            if(user.getName().contains(name)){
                return user;
            }
        }
        User newUser = new User(name, UserRights.NORMAL);
        add(newUser);
        saveUsers();
        return newUser;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public UserRights getActiveUserRights() { return activeUser.getRights(); }

    public ArrayList<User> getAllUsers(){
        return users;
    }

    public void loadUsers() {
        Path path;
        path = Paths.get("users.src");
        if (Files.exists(path))
            users = (ArrayList<User>) FileUtility.loadObject("users.src");
        else {
            users = new ArrayList<>();
            saveUsers();
        }
    }

    public void saveUsers(){
        FileUtility.saveObject("users.src", users, StandardOpenOption.CREATE);
    }







}
