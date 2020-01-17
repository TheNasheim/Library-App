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
       // loadBooks();
        books = new ArrayList<>();
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

    public ArrayList<Book> getAllBooks(){
        return books;
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
