package application.archives;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookManager {

    private ArrayList<Book> books = new ArrayList<>();

    public BookManager() {
        // Let this be empty for now.
    }

    /**
     *
     * @param bookIn
     */
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
        this.books = booksIn;
    }

    /**
     *
     * @return list of all books
     */
    public ArrayList<Book> getAllBooks(){
        return books;
    }

    /**
     *
     * @param category
     * @return list of books with category
     */
    public ArrayList<Book> getCategoryOfBooks(String category){
        ArrayList<Book> booksOut = new ArrayList<>();
        for(Book book : books){
            if(book.getCategory().equals(category))
                booksOut.add(book);
        }
        return booksOut;
    }


}
