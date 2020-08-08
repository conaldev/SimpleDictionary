package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.SearchedWord;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuMain implements Initializable {

    @FXML
    private TableView<SearchedWord> table;
    @FXML
    private TableColumn<SearchedWord,String> word;
    @FXML
    private TableColumn<SearchedWord,String> pronounce;
    @FXML
    private TableColumn<SearchedWord,String> type;
    @FXML
    private TableColumn<SearchedWord,String> meaning;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<SearchedWord> searchedWordList = FXCollections.observableArrayList(new SearchedWord("hello", "/helo/", "adj", "xin chào"), new SearchedWord("bonjour", "/bonjour/", "verb", "chàojkldsa;fg;fff"));
        word.setCellValueFactory(new PropertyValueFactory<SearchedWord,String>("searchedWord"));
        pronounce.setCellValueFactory(new PropertyValueFactory<SearchedWord,String>("pronounce"));
        type.setCellValueFactory(new PropertyValueFactory<SearchedWord,String>("type"));
        meaning.setCellValueFactory(new PropertyValueFactory<SearchedWord,String>("meaning"));
        table.setItems(searchedWordList);
    }
    public void search(ActionEvent event) {
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("../font_end/wordDetail.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader));
        stage.show();
    }
}
