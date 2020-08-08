import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimpleDictionary extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("font_end/login.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
