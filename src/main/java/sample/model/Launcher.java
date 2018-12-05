package sample.model;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model.getInstance(primaryStage).MainWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
