package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.*;
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
    private TableColumn<SearchedWord, String> word;
    @FXML
    private TableColumn<SearchedWord, String> pronounce;
    @FXML
    private TableColumn<SearchedWord, String> type;
    @FXML
    private TableColumn<SearchedWord, String> meaning;
    @FXML
    public TextField searchWord;

    static final ObservableList<SearchedWord> searchedWordList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        word.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("searchedWord"));
        pronounce.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("pronounce"));
        type.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("type"));
        meaning.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("meaning"));
        table.setItems(searchedWordList);
    }


    public void search(ActionEvent event) {
        String keySearchWord = searchWord.getText();
        if (!UserControl.getInstance().isWordExist(keySearchWord)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dictionary doesn't have this word!");
            alert.setHeaderText("Dictionary doesn't have this word! :((");
            alert.show();
        } else {

            searchedWordList.add(0, new SearchedWord(keySearchWord, UserControl.getInstance().searchPronunciation(keySearchWord), UserControl.getInstance().searchWordClass(keySearchWord), UserControl.getInstance().searchMeaning(keySearchWord)));
            word.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("searchedWord"));
            pronounce.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("pronounce"));
            type.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("type"));
            meaning.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("meaning"));
            table.setItems(searchedWordList);
        }
    }
}
