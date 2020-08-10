package controller;

import javafx.application.Platform;
import javafx.scene.control.*;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.SearchedWord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuMain{

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

    public void search(ActionEvent event) {
        String keySearchWord = searchWord.getText();
        if (!UserControl.getInstance().isWordExist(keySearchWord)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dictionary doesn't have this word!");
            alert.setHeaderText("Dictionary doesn't have this word! :((");
            alert.show();
        } else {
            SearchedWord.getArrayList().add(keySearchWord);
            searchedWordList.add(0, new SearchedWord(keySearchWord, UserControl.getInstance().searchPronunciation(keySearchWord), UserControl.getInstance().searchWordClass(keySearchWord), UserControl.getInstance().searchMeaning(keySearchWord)));
            word.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("searchedWord"));
            pronounce.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("pronounce"));
            type.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("type"));
            meaning.setCellValueFactory(new PropertyValueFactory<SearchedWord, String>("meaning"));
            table.setItems(searchedWordList);
        }
        searchWord.clear();
    }
    public void addNewWord(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("../front_end/addNewWord.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }
    public void editWord(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("../front_end/editWord.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }
    public void deleteWord(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("../front_end/deleteWord.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }
    public void export(ActionEvent event) {
        try {
            UserControl.getInstance().exportToFile(SearchedWord.getArrayList());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exported to file");
            alert.setHeaderText("Successed!!Exported to file!");
            alert.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logOut(ActionEvent event) {
       searchedWordList.clear();
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("../front_end/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader));
        stage.show();
    }
}
