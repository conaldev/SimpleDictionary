package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.UserControl;



public class DeleteWord {
    @FXML
    TextField word;
    public void deleteWord(){
        UserControl.getInstance().deleteWord(word.getText());
    }
}
