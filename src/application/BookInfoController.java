package application;

import application.archives.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookInfoController {

    @FXML

    public Button btnClose;
    public Label lblName;
    public Label lblAuthor;
    public Label lblsynopsis;


    public void initializeGUI(Book book){
    lblName.setText(book.getBookTitle());
    lblAuthor.setText(book.getAuthorName());
    lblsynopsis.setText(book.getDescription());
    }


    public void mouse_Click(MouseEvent mouseEvent) {
        Stage stage = (Stage)btnClose.getScene().getWindow();
        stage.close();
    }
}
