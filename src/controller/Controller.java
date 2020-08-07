package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import service.DicionaryHashMap;

import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    TextField email;
    @FXML
    TextField passWord;

    public void logIn(ActionEvent event) {
        Pattern patternEmail = Pattern.compile("^[a-z][a-z0-9_.]{5,32}@\\w{2,}(\\.[a-z0-9]{2,4}){1,2}$");
        Pattern patternPassWord = Pattern.compile("^.{6,20}$");
        Matcher matcherP = patternPassWord.matcher(passWord.getText());
        Matcher matcherE = patternEmail.matcher(email.getText());
        if(!matcherE.matches() && !matcherP.matches()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid =_=");
            alert.setHeaderText("Invalid Email + Invalid Password");
            alert.show();
        }

    }
    public void signUp(ActionEvent event) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("SIGN UP");
        ButtonType signUpButtonType = new ButtonType("Sign Up", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(signUpButtonType,ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20,150,10,10));

        TextField newEmail = new TextField();
        newEmail.setPromptText("Your email");
        PasswordField newPassword = new PasswordField();
        newPassword.setPromptText("Password");

        gridPane.add(new Label("Email:"),0,0);
        gridPane.add(newEmail,1,0);
        gridPane.add(new Label("Password:"),0,1);
        gridPane.add(newPassword,1,1);

        Node signupButton = dialog.getDialogPane().lookupButton(signUpButtonType);
        signupButton.setDisable(true);

        newEmail.textProperty().addListener((observableValue, oldValue, newValue) -> {
            signupButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(gridPane);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == signUpButtonType)
                return new Pair<>(newEmail.getText(), newPassword.getText());
            return null;
        });
        Optional<Pair<String,String>> result = dialog.showAndWait();
        result.ifPresent(emailPassword -> {
            System.out.println("ok dc roi");
        });
    }
}
