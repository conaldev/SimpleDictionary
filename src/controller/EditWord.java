package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.DicionaryHashMap;
import service.UserControl;

import static util.Constant.DICTIONARY_FILE;


public class EditWord {
    @FXML
    TextField word;
    @FXML
    TextField pronounce;
    @FXML
    TextField type;
    @FXML
    TextField meaning;

    public void editWord(ActionEvent event){
        if(!pronounce.getText().equals(""))
            UserControl.getInstance().changePronounce(word.getText(),pronounce.getText());
        if(!type.getText().equals(""))
            UserControl.getInstance().changeType(word.getText(),type.getText());
        if(!meaning.getText().equals(""))
            UserControl.getInstance().changeMeaning(word.getText(),meaning.getText());
        UserControl.getInstance().setDicHashMap(DicionaryHashMap.getInstance().readToHashMapDic(DICTIONARY_FILE));
    }
}
