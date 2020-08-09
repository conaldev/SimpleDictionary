import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.DicionaryHashMap;
import service.ManageAccounts;
import service.UserControl;

public class SimpleDictionary extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        UserControl.getInstance().setDicHashMap(new DicionaryHashMap().readToHashMapDic("dictionary.txt"));
        ManageAccounts.getInstance();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("front_end/login.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
