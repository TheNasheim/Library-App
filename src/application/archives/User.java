package application.archives;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String _Name;
    private UserRights _Rights;
    private ArrayList<Book> borrowedBooks;


    public User(String name, UserRights rights) {
        borrowedBooks = new ArrayList<>();
        _Name = name;
        _Rights = rights;
    }

    public String getName() {
        return _Name;
    }

    public UserRights getRights() {
        return _Rights;
    }

    public void addBorrowedBook(Book book){
        borrowedBooks.add(book);
    }

    public void removeBorrowedBook(Book book){
        borrowedBooks.remove(book);
    }

    public ArrayList<Book> showAllBorrowedBooks(){
        return borrowedBooks;
    }

}
