package application.archives;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class AddNewBookController {

    public TextField txtTitle;
    public TextField txtAuthor;
    public TextField txtCategory;
    public TextArea txtSynopsis;
    public Label lblStatus;
    public Button btnSaveAndClose;

    private ArrayList<Book> newBooks = new ArrayList<>();

    public ArrayList<Book> getNewBooks() {
        return newBooks;
    }

    public void onSave_Click(MouseEvent mouseEvent) {
        if(checkTextFields()) {
            Book newBook = new Book(txtTitle.getText(), txtAuthor.getText(), txtCategory.getText(), true, txtSynopsis.getText());
            newBooks.add(newBook);
            statusText("A new book has been added.");
            clearTextFields();
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

    private void clearTextFields(){
        txtTitle.setText("");
        txtAuthor.setText("");
        txtCategory.setText("");
        txtSynopsis.setText("");
    }

    private void statusText(String status){
        lblStatus.setText("Status: " + status);
    }
}
