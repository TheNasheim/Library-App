package application;

import application.archives.Book;
import application.archives.BookManager;
import application.archives.UserManager;
import application.archives.UserRights;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class ProgramController {

    @FXML
    public Label lblStatus;
    public TextField txtSearch;
    public Button btnReturn;
    public Button btnBorrow;
    public TableView tvListofObjects;
    public CheckBox cbAvailable;

    private BookManager _bookManager;
    private UserManager _userManager;

    public void initializeGUI(UserManager userManager){

        this._userManager = userManager;
        _bookManager = new BookManager();
        createNewBooks();
        displayBookTable();
        searchBooks();
        btnReturn.setDisable(true);
    }

    /**
     * Here we create a list of books for testing.
     */
    private void createNewBooks(){
        _bookManager.add(new Book("Hands-on Machine Learning with Scikit-Learn, Keras, and TensorFlow", "Aurelien Geron", "ComputerScience",true, "Through a series of recent breakthroughs, deep learning has boosted the entire field of machine learning. Now, even programmers who know close to nothing about this technology can use simple, efficient tools to implement programs capable of learning from data. This practical book shows you how. By using concrete examples, minimal theory, and two production-ready Python frameworks-Scikit-Learn and TensorFlow-author Aurelien Geron helps you gain an intuitive understanding of the concepts and tools for building intelligent systems. You'll learn a range of techniques, starting with simple linear regression and progressing to deep neural networks. With exercises in each chapter to help you apply what you've learned, all you need is programming experience to get started." ));

        _bookManager.add(new Book("AI for Games, Third Edition", "Ian Millington", "ComputerScience",true, "AI is an integral part of every video game. This book helps professionals keep up with the constantly evolving technological advances in the fast growing game industry and equips students with up-to-date information they need to jumpstart their careers. This revised and updated Third Edition includes new techniques, algorithms, data structures and representations needed to create powerful AI in games." ));
        _bookManager.add(new Book("How to Create a Mind ", "Ray Kurzweil", "ComputerScience",true, "'Ray Kurzweil is the best person I know at predicting the future of artificial intelligence.' Bill GatesIn How to Create a Mind, Ray Kurzweil offers a provocative exploration of the most important project in human-machine civilisation: reverse engineering the brain to understand precisely how it works and using that knowledge to create even more intelligent machines. Kurzweil explores how the brain functions, how the mind emerges from the brain, and the implications of vastly increasing the powers of our intelligence in addressing the world's problems. He thoughtfully examines emotional and moral intelligence and the origins of consciousness and envisions the radical - arguably inevitable - future of our merging with the intelligent technology we are creating." ));

        Book testbook = new Book("Artificial Intelligence 1st Edition","Margaret Boden","ComputerScience",true,"Artificial Intelligence in BASIC presents some of the central ideas and practical applications of artificial intelligence (AI) using the BASIC programs. This eight-chapter book aims to explain these ideas of AI that can be used to produce programs on microcomputers. After providing an overview of the concept of AI, this book goes on examining the features and difficulties of a heuristic solution in a wide range of human problems. The discussion then shifts to the application of a heuristic solution to a two-ply search program for a two-person game. The following chapters are devoted to the other components of AI, including the expert systems, memory structure, pattern recognition, and language. The concluding chapter deals with the alternative and auxiliary approaches to the study of AI and its practical applications. Computer scientists and programmers will find this work invaluable.");
        _bookManager.add(testbook);
        _bookManager.borrowSelectedBook(testbook, _userManager.getUserbyName("Johan"));
        //_bookManager.saveBooks();
    }

    private void displayBookTable(){
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
        column4.setStyle("-fx-alignment: CENTER;");
        tvListofObjects.getColumns().add(column1);
        tvListofObjects.getColumns().add(column2);
        tvListofObjects.getColumns().add(column3);
        tvListofObjects.getColumns().add(column4);
    }

    /**
     * Start to clear all rows to be shure. then fills it up with all books in the array.
     */
    private void searchBooks(){
        String search = txtSearch.getText();
        boolean available = cbAvailable.isSelected();
        tvListofObjects.getItems().clear();
        ArrayList<Book> books = _bookManager.getBooks(search, available);
        displayBooks(books);
    }

    private void displayBooks(ArrayList<Book> books){
        tvListofObjects.getItems().clear();
        if(books.size() > 0) {
            for (Book book : books) {
                tvListofObjects.getItems().add(book);
            }
            tvListofObjects.getSelectionModel().selectFirst();
            getIsBookBorrowed();
        }
    }

    public void onReadMore_Click(MouseEvent mouseEvent) throws IOException {
        Book selectedBook = (Book)tvListofObjects.getSelectionModel().getSelectedItem();
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

    public void onBorrow_Click(MouseEvent mouseEvent) {
        Book selectedBook = (Book)tvListofObjects.getSelectionModel().getSelectedItem();
        if(selectedBook.getAvailable()) {
            boolean done = _bookManager.borrowSelectedBook(selectedBook, _userManager.getActiveUser());
            if (done)
                if (selectedBook.getBookTitle().length() > 20)
                    setNewStatus("Book " + selectedBook.getBookTitle().substring(0, 20) + "... has now been borrowed to " + _userManager.getActiveUser().getName());
                 else
                    setNewStatus("Book " + selectedBook.getBookTitle() + " has now been borrowed to " + _userManager.getActiveUser().getName());
            else
                setNewStatus("Book can not be borrowed.");
        }
        searchBooks();
    }

    public void tvListofObjects_Click(MouseEvent mouseEvent) {
        getIsBookBorrowed();

    }

    private void getIsBookBorrowed(){
        Book selectedBook = (Book)tvListofObjects.getSelectionModel().getSelectedItem();
        btnBorrow.setDisable(!selectedBook.getAvailable());
        if(!selectedBook.getAvailable()){
            getIsBookReturnable(selectedBook);
            //
        }
        else setNewStatus("");
    }

    private void getIsBookReturnable(Book selectedBook) {
        if (selectedBook.getBorrowedTo().getName().contains(_userManager.getActiveUser().getName())) {
            btnReturn.setDisable(false);
            setNewStatus("Selected book is borrowed by You");
        } else {
            btnReturn.setDisable(true);
            if (_userManager.getActiveUserRights() == UserRights.ADMIN)
                setNewStatus("Selected book is borrowed by " + selectedBook.getBorrowedTo().getName());
        }
    }

    private void setNewStatus(String newStatus){
        lblStatus.setText("Status: " + newStatus);
    }

    public void onReturn_Click(MouseEvent mouseEvent) {
        Book selectedBook = (Book)tvListofObjects.getSelectionModel().getSelectedItem();
        _bookManager.returnSelectedBook(selectedBook);
        setNewStatus("Book has now been returned.");
        searchBooks();
    }

    public void onSearch_Click(MouseEvent mouseEvent) {
        searchBooks();
    }

    public void onMyBorrowedBooks_Click(MouseEvent mouseEvent) {
        ArrayList<Book> books = _bookManager.getMyBorrowedBooks(_userManager.getActiveUser());
        setNewStatus("Displaying users borrowed books.");
        displayBooks(books);
    }
}

