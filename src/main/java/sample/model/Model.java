package sample.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.controller.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Model {
    private static Model instance;
    private Stage primaryStage;
    private Map<String, Pane> map;

    private Model (Stage primaryStage) {
        map = new HashMap<String, Pane>();
        this.primaryStage = primaryStage;
    }

    public static synchronized Model getInstance(Stage primaryStage) {
       if (instance == null) {
           instance = new Model(primaryStage);
       }
       return instance;
    }

    public static synchronized Model getInstance() {
        return instance;
    }

    public Map<String, Pane> getPanes(){
        return map;
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public Pane AboutWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/About.fxml"));

        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setUserData((AboutController) loader.getController());
        map.put("about", pane);
        return pane;
    }

    public Pane CreationProjectWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CreationProject.fxml"));
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setUserData((CreationProjectController) loader.getController());
        map.put("creationProject", pane);
        return pane;
    }

    public Pane DictionaryWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/Dictionary.fxml"));
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setUserData((DictionaryController) loader.getController());
        map.put("dictionary", pane);
        return pane;
    }

    public Pane ExportProjectWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ExportProject.fxml"));
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setUserData((ExportProjectController) loader.getController());
        map.put("exportProject", pane);
        return pane;
    }

    public Pane ImportProjectWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ImportProject.fxml"));
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setUserData((ImportProjectController) loader.getController());
        map.put("importProject", pane);
        return pane;
    }

    public void MainWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/MainWindow.fxml"));
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setUserData((MainController) loader.getController());
        map.put("main", pane);
        this.primaryStage.setTitle("CrAnES");
        this.primaryStage.setScene(new Scene(pane));
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    public Pane OpenProjectWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/OpenProject.fxml"));
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setUserData((OpenProjectController) loader.getController());
        map.put("openProject", pane);
        return pane;
    }

    public Pane ProjectWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/Project.fxml"));

        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setUserData((ProjectController) loader.getController());
        map.put("project", pane);
        return pane;
    }

    public Pane ScanwordWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/Scanword.fxml"));
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setUserData((ScanwordController) loader.getController());
        map.put("scanword", pane);
        return pane;
    }

    public Pane WordDescriptionWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/WordDescription.fxml"));
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setUserData((WordDescriptionController) loader.getController());
        map.put("wordDescription", pane);
        return pane;
    }

    public Pane WordsListWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/WordsList.fxml"));
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setUserData((WordsListController) loader.getController());
        map.put("wordsList", pane);
        return pane;
    }
}