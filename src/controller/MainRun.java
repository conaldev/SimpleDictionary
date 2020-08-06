package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import service.DicionaryHashMap;

import java.util.HashMap;

public class MainRun extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    String pathSrcDic = "dictionary.txt";
    DicionaryHashMap fileDicConverted = new DicionaryHashMap(pathSrcDic);
    HashMap<String, String> dicHashMap = fileDicConverted.readToHashMapDic();

    @Override
    public void start(Stage primaryStage) {

    }
}
