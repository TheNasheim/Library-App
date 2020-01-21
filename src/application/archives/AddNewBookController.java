package application.archives;

import application.FileUtility;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;

public class AddNewBookController {


    public TextField txtTitle;
    public TextField txtAuthor;
    public TextField txtCategory;
    public TextArea txtSynopsis;
    public Label lblStatus;
    public Button btnSaveAndClose;

    private Book newBook = null;
    private ArrayList<Book> books;

    public Book getNewBook() {
        return newBook;
    }

    public void onSave_Click(MouseEvent mouseEvent) {
        //books = FileUtility("")
        if(checkTextFields()) {
            newBook = new Book(txtTitle.getText(), txtAuthor.getText(), txtCategory.getText(), true, txtSynopsis.getText());
            final Node source = (Node) mouseEvent.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            //Stage stage = (Stage) btnSaveAndClose.getScene().getWindow();
            //stage.close();
        }
    }

    private boolean checkTextFields(){
        boolean filled = false;
        if(txtTitle.getText().length() == 0 && txtAuthor.getText().length() == 0 && txtCategory.getText().length() == 0 && txtSynopsis.getText().length() == 0)
            filled = true;
        else if(txtTitle.getText().length() == 0)
            statusText("There is no Title!");
        else if(txtAuthor.getText().length() == 0)
            statusText("There is no Author!");
        else if(txtCategory.getText().length() == 0)
            statusText("There is no Category!");
        else if(txtSynopsis.getText().length() == 0)
            statusText("There is no Synopsis!");
        else
            filled = true;
        return filled;
    }

    private void statusText(String status){
        lblStatus.setText("Status: " + status);
    }
}
