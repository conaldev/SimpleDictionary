package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.SearchedWord;

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
}
