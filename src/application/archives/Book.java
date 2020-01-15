package application.archives;

import java.util.Calendar;

public class Book {

    String bookName;
    String authorName;
    String category;
    String about;
    Calendar returnDate;


    public Book(String bookName, String authorName, String category) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.category = category;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {

        return authorName;
    }

    public String getCategory() {
        return category;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Calendar getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "authorName='" + authorName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

