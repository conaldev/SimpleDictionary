package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.UserControl;

public class AddNewWord {
    @FXML
    private TextField newWord;
    @FXML
    private TextField newPronounce;
    @FXML
    private TextField newType;
    @FXML
    private TextField newMeaning;

    public void addWord(ActionEvent event){
        UserControl.getInstance().addNewWorld(newWord.getText(),newPronounce.getText(),newType.getText(),newMeaning.getText());
    }
}
