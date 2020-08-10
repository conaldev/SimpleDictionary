package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import service.ManageAccounts;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogIn {
    @FXML
    TextField email;
    @FXML
    PasswordField passWord;

    public void logIn(ActionEvent event) {
        Pattern patternEmail = Pattern.compile("^[A-Za-z0-9+_.-]{3,}@(.+)$");
        Matcher matcherE = patternEmail.matcher(email.getText());
        if (!matcherE.matches()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Email");
            alert.setHeaderText("Hint: Enter a valid email !");
            alert.show();
        } else {
            if(ManageAccounts.getInstance().submitLogIn(email.getText(),passWord.getText())) {
                Parent loader = null;
                try {
                    loader = FXMLLoader.load(getClass().getResource("../front_end/MenuMain.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(loader));
                stage.show();
            }else{
                if(ManageAccounts.getInstance().accountAlreadyExist(email.getText())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("You entered wrong password");
                    alert.setHeaderText("You entered wrong password");
                    alert.show();
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Account no exists!");
                    alert.setHeaderText("Account no exists!");
                    alert.show();
                }
            }
        }
    }

    public void signUp(ActionEvent event) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("SIGN UP");
        ButtonType signUpButtonType = new ButtonType("Sign Up", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(signUpButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField newEmail = new TextField();
        newEmail.setPromptText("Your email");
        PasswordField newPassword = new PasswordField();
        newPassword.setPromptText("Password");

        gridPane.add(new Label("Email:"), 0, 0);
        gridPane.add(newEmail, 1, 0);
        gridPane.add(new Label("Password:"), 0, 1);
        gridPane.add(newPassword, 1, 1);

        Node signupButton = dialog.getDialogPane().lookupButton(signUpButtonType);
        signupButton.setDisable(true);

        newEmail.textProperty().addListener((observableValue, oldValue, newValue) -> {
            signupButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(gridPane);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == signUpButtonType)
                return new Pair<>(newEmail.getText(), newPassword.getText());
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(emailPassword -> {
            Pattern patternEmail = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
            Matcher matcherE = patternEmail.matcher(newEmail.getText());
            if (!matcherE.matches()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Email");
                alert.setHeaderText("Hint: Enter a valid email !");
                alert.show();
            } else {
                if (ManageAccounts.getInstance().accountAlreadyExist(newEmail.getText())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Account already exists! ");
                    alert.setHeaderText("Account already exists!");
                    alert.show();
                } else
                    ManageAccounts.getInstance().addAccount(newEmail.getText(), newPassword.getText());
            }
        });
    }
}
