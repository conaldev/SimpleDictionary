package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class WordDetail {
    @FXML
    TextArea pronounce;
    @FXML
    TextArea type;
    @FXML
    TextArea meaning;
    @FXML
    javafx.scene.control.Label word;

    public void goBack(ActionEvent event) {
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("../font_end/MenuMain.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader));
        stage.show();
    }
}
