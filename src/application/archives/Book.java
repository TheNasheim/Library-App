package application.archives;


import java.io.Serializable;
import java.util.Calendar;

public class Book implements Serializable {

    private String _bookTitle;
    private String _authorName;
    private String _category;
    private String _description;
    private String _borrowedToName;
    private User _BorrowedToUser;

    private boolean _available;
    private Calendar _returnDate;

    public Book(String bookTitle, String authorName, String category, boolean available, String description) {
        _bookTitle = bookTitle;
        _authorName = authorName;
        _category = category;
        _available = available;
        _description = description;
        _borrowedToName = "";
    }

    public String getBorrowedToName() {
        return _borrowedToName;
    }

    public User getBorrowedToUser() {
        return _BorrowedToUser;
    }

    public void setBorrowedTo(User borrowedTo) {
        if(borrowedTo == null){
            _BorrowedToUser = null;
            _borrowedToName = "";
            setAvailable(true);}
        else{
            setAvailable(false);
            _BorrowedToUser = borrowedTo;
            _borrowedToName = borrowedTo.getName();
        }
    }

    public String getBookTitle() {
        return _bookTitle;
    }

    public String getAuthorName() {
        return _authorName;
    }

    public String getCategory() {
        return _category;
    }

    public String getDescription() {
        return _description;
    }

    public boolean getAvailable() {
        return _available;
    }

    public void setAvailable(boolean available) {
        _available = available;
    }

    public void setDescription(String about) {
        _description = about;
    }

    public Calendar getReturnDate() {
        return _returnDate;
    }

    public void setReturnDate(Calendar returnDate) {
        this._returnDate = returnDate;
    }

}

