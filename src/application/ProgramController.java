package application;

import application.archives.Book;
import application.archives.BookManager;
import application.archives.User;
import application.archives.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class ProgramController {

    @FXML
    private TableView tvListofObjects;
    @FXML
    private Label lblusername;
    @FXML
    private TextField txtUserName;

    private BookManager _bookManager;
    private UserManager _userManager;


    public void initializeGUI(UserManager userManager){

        this._userManager = userManager;
        _bookManager = new BookManager();
        //createNewBooks();
        displayBooks();
        showAllBooks();
    }


    /**
     * Here we create a list of books for testing.
     */
    private void createNewBooks(){
        _bookManager.add(new Book("Artificial Intelligence 1st Edition","Margaret Boden","ComputerScience",true,"Artificial Intelligence in BASIC presents some of the central ideas and practical applications of artificial intelligence (AI) using the BASIC programs. This eight-chapter book aims to explain these ideas of AI that can be used to produce programs on microcomputers. After providing an overview of the concept of AI, this book goes on examining the features and difficulties of a heuristic solution in a wide range of human problems. The discussion then shifts to the application of a heuristic solution to a two-ply search program for a two-person game. The following chapters are devoted to the other components of AI, including the expert systems, memory structure, pattern recognition, and language. The concluding chapter deals with the alternative and auxiliary approaches to the study of AI and its practical applications. Computer scientists and programmers will find this work invaluable."));
        _bookManager.add(new Book("Hands-on Machine Learning with Scikit-Learn, Keras, and TensorFlow", "Aurelien Geron", "ComputerScience",true, "Through a series of recent breakthroughs, deep learning has boosted the entire field of machine learning. Now, even programmers who know close to nothing about this technology can use simple, efficient tools to implement programs capable of learning from data. This practical book shows you how. By using concrete examples, minimal theory, and two production-ready Python frameworks-Scikit-Learn and TensorFlow-author Aurelien Geron helps you gain an intuitive understanding of the concepts and tools for building intelligent systems. You'll learn a range of techniques, starting with simple linear regression and progressing to deep neural networks. With exercises in each chapter to help you apply what you've learned, all you need is programming experience to get started." ));
        //_bookManager.saveBooks();
    }

    private void displayBooks(){
        TableColumn<String, Book> column1 = new TableColumn<>("Book Title");
        column1.setPrefWidth(300f);
        column1.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));

        TableColumn<String, Book> column2 = new TableColumn<>("Author Name");
        column2.setPrefWidth(100f);
        column2.setCellValueFactory(new PropertyValueFactory<>("authorName"));

        TableColumn<String, Book> column3 = new TableColumn<>("Category");
        column3.setPrefWidth(100f);
        column3.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, Boolean> column4 = new TableColumn<>("Available");
        column4.setPrefWidth(60f);
        column4.setCellValueFactory(new PropertyValueFactory<>("available"));

        column4.setCellFactory(col -> new TableCell<Book, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item ? "YES" : "NO" );
            }
        });
        column4.setStyle("-fx-alignment: CENTER-RIGHT;");

        tvListofObjects.getColumns().add(column1);
        tvListofObjects.getColumns().add(column2);
        tvListofObjects.getColumns().add(column3);
        tvListofObjects.getColumns().add(column4);

    }

    private void showAllBooks(){
        ArrayList<Book> books = _bookManager.getAllBooks();
        for(Book book : books){
            tvListofObjects.getItems().add(book);
        }
        tvListofObjects.getSelectionModel().selectFirst();
    }

    public void onMouse_Click(MouseEvent mouseEvent) throws IOException {
        Book selectedBook = (Book)tvListofObjects.getSelectionModel().getSelectedItem();
        txtUserName.setText(selectedBook.getBookTitle());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BookInfo.fxml"));
        Parent root = fxmlLoader.load();
        BookInfoController controller = fxmlLoader.<BookInfoController>getController();
        controller.initializeGUI(selectedBook);
        Stage stage = new Stage();
        stage.setTitle(selectedBook.getBookTitle());
        stage.setScene(new Scene(root, 370 , 350));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

}

