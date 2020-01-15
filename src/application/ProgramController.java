package application;

import application.archives.Book;
import application.archives.BookManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ProgramController {



    @FXML
    private TableView tvListofObjects;
    @FXML
    private Label lblusername;
    @FXML
    private TextField txtUserName;

    @FXML
    //private void initialize() { Platform.runLater(() -> {        }); }
    private BookManager bookManager = new BookManager();

    public void initializeGUI(String userName){
        showAllBooks();
        createBooks();
        displayBooks();
        showAllBooks();
        setUsername(userName);
    }

    /**
     * Here we create a list of books for testing.
     */
    private void createBooks(){
        bookManager.add(new Book("Artificial Intelligence 1st Edition","Margaret Boden","ComputerScience"));
        bookManager.add(new Book("Hands-on Machine Learning with Scikit-Learn, Keras, and TensorFlow", "Aurelien Geron", "ComputerScience"));
    }



    private void setUsername(String username){

        lblusername.setText("You are logged in as: " + username);

    }

    private void displayBooks(){
        TableColumn<String, Book> column1 = new TableColumn<>("Book Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        column1.setPrefWidth(300f);
        TableColumn<String, Book> column2 = new TableColumn<>("Author Name");
        column2.setPrefWidth(110f);
        column2.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        TableColumn<String, Book> column3 = new TableColumn<>("Category");
        column3.setPrefWidth(120f);
        column3.setCellValueFactory(new PropertyValueFactory<>("category"));

        tvListofObjects.getColumns().add(column1);
        tvListofObjects.getColumns().add(column2);
        tvListofObjects.getColumns().add(column3);
    }


    private void showAllBooks(){
        ArrayList<Book> books = bookManager.getAllBooks();
        for(Book book : books){
        tvListofObjects.getItems().add(book);
        }
    }


}
