package application.archives;

import application.FileUtility;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class BookManager {

    private ArrayList<Book> books;

    public BookManager() {
        loadBooks();
    }

    public void add(Book bookIn){
        books.add(bookIn);
    }

    public void removeAtIndex(int index){
        books.remove(index);
    }

    public int bookCount(){
        return books.size();
    }

    public void fillBooks(ArrayList<Book> booksIn){
        books = booksIn;
    }

    public ArrayList<Book> getBooks(String strIn, boolean available){
        if(!available){
            if(strIn.isEmpty()){
                return books;
            }
            else { // returns all searched books.
                ArrayList<Book> otherBooks = new ArrayList<>();
                for(Book book : books){
                    if(book.getAvailable()) otherBooks.add(book);
                }
                return otherBooks;
            }
        }
        else{
            if(strIn.isEmpty()){ // Returns all available books
                ArrayList<Book> otherBooks = new ArrayList<>();
                for(Book book : books){
                    if(book.getAvailable()) otherBooks.add(book);
                }
                return otherBooks;
            }
            else { // returns all available searched books.
                ArrayList<Book> otherBooks = new ArrayList<>();
                for(Book book : books){
                    if(book.getAvailable()) {
                        if (book.getBookTitle().contains(strIn)) otherBooks.add(book);
                        else if (book.getAuthorName().contains(strIn)) otherBooks.add(book);
                    }
                }
                return otherBooks;
            }
        }
    }

    public ArrayList<Book> getUserBorrowedBooks(User selectedUser) {
        ArrayList<Book> otherBooks = new ArrayList<>();
        for(Book book : books){
            if(book.getBorrowedToUser() == selectedUser) otherBooks.add(book);
        }
        return otherBooks;
    }

    public ArrayList<Book> getAllBorrowedBooks() {
        ArrayList<Book> borrowedBooks = new ArrayList<>();
        for(Book book : books){
            if(book.getBorrowedToUser() != null) borrowedBooks.add(book);
        }
        return borrowedBooks;
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    public boolean borrowSelectedBook(Book selectedBook, User userWhoBorrows) {
        boolean done = false;
        if(userWhoBorrows == null)
            return done;
        for(Book book : books){
            if(book.getBookTitle().equals(selectedBook.getBookTitle()) && book.getAvailable()){
                book.setBorrowedTo(userWhoBorrows);
                book.setAvailable(false);
                done = true;
                break;
            }
        }
        return done;
    }

    public boolean returnSelectedBook(Book selectedBook) {
        boolean done = false;
        for(Book book : books){
            if(book.getBookTitle().equals(selectedBook.getBookTitle())){
                book.setBorrowedTo(null);
                book.setAvailable(true);
                done = true;
            }
        }
        return done;
    }

    public int getUserOverdueBooks(User selectedUser){
        int count = 0;
        for(Book book : books){
            if(book.getBorrowedToUser() == selectedUser) count++;
        }
        return count;
    }

    public ArrayList<Book> getCategoryOfBooks(String category){
        ArrayList<Book> booksOut = new ArrayList<>();
        for(Book book : books){
            if(book.getCategory().equals(category))
                booksOut.add(book);
        }
        return booksOut;
    }

    public void loadBooks() {
        Path path;
        path = Paths.get("books.src");
        if (Files.exists(path))
            books = (ArrayList<Book>) FileUtility.loadObject("books.src");
        else {
            books = new ArrayList<>();
            //saveBooks();
        }
    }

    public void saveBooks(){
        FileUtility.saveObject("books.src", books, StandardOpenOption.CREATE);
    }


}
