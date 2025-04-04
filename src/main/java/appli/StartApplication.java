package appli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    static Stage mainStage;
    @Override

    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("accueil/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 201.0, 419.0);
        mainStage.setTitle("TodoList");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void changeScene(String nomDuFichierFxml ) throws IOException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(StartApplication.class.getResource(nomDuFichierFxml + "View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setUserData(fxmlLoader.getController());
        mainStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
    public static Object getControllerFromStage() {
        return mainStage.getScene().getUserData();
    }

}