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

    /**
     *
     * @param bookIn adds the book
     */
    public void add(Book bookIn){
        books.add(bookIn);
    }

    /**
     *
     * @return how many books there are in the array
     */
    public int bookCount(){
        return books.size();
    }

    /**
     *
     * @param booksIn fills the Array from booksIn
     */
    public void fillBooks(ArrayList<Book> booksIn){
        books = booksIn;
    }

    /**
     * @return the selected books. With or without borrowed.
     */
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

    /**
     *
     * @param selectedUser
     * @return books that user borrowed
     */
    public ArrayList<Book> getUserBorrowedBooks(User selectedUser) {
        ArrayList<Book> otherBooks = new ArrayList<>();
        for(Book book : books){
            if(book.getBorrowedToUser() == selectedUser) otherBooks.add(book);
        }
        return otherBooks;
    }

    /**
     *
     * @return all borrowed books
     */
    public ArrayList<Book> getAllBorrowedBooks() {
        ArrayList<Book> borrowedBooks = new ArrayList<>();
        for(Book book : books){
            if(book.getBorrowedToUser() != null) borrowedBooks.add(book);
        }
        return borrowedBooks;
    }

    /**
     *
     * @param book removes the book from array
     */
    public void removeBook(Book book){
        books.remove(book);
    }

    /**
     *
     * @param selectedBook what book to borrow
     * @param userWhoBorrows who wants to borrow the book
     * @return if it is possible or not.
     */
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

    /**
     *
     * @param selectedBook return selected book.
     * @return if it is possible or not.
     */
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

    /**
     *
     * @param selectedUser
     * @return how many books that are overdue from selectedUser.
     */
    public int getUserOverdueBooks(User selectedUser){
        int count = 0;
        for(Book book : books){
            if(book.getBorrowedToUser() == selectedUser) count++;
        }
        return count;
    }

    /**
     * Loads the books
     */
    public void loadBooks() {
        Path path;
        path = Paths.get("books.src");
        if (Files.exists(path))
            books = (ArrayList<Book>) FileUtility.loadObject("books.src");
        else {
            books = new ArrayList<>();
        }
    }

    /**
     * Saves the books.
     */
    public void saveBooks(){
        FileUtility.saveObject("books.src", books, StandardOpenOption.CREATE);
    }


}
