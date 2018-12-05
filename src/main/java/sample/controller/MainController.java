package sample.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sample.model.Model;

import java.io.IOException;

public class MainController {

    private Model model;
    @FXML
    private MenuItem openMenuItem;
    @FXML
    private MenuItem createMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private MenuItem exportMenuItem;
    @FXML
    private MenuItem importMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private  CheckMenuItem wordsCheckMenuItem;
    @FXML
    private  CheckMenuItem dictionaryCheckMenuItem;
    @FXML
    private CheckMenuItem wordDescriptionCheckMenuItem;
    @FXML
    private Menu fileMenu;
    @FXML
    private Menu helpMenu;
    @FXML
    private SplitPane leftSplitPane;
    @FXML
    private ScrollPane  centerScrollPane;
    @FXML
    private SplitPane rightSplitPane;
    @FXML
    private AnchorPane informationPane;

    @FXML
    private void initialize() {
        leftSplitPane.getItems().add(Model.getInstance().ProjectWindow());
        wordsCheckMenuItem.setSelected(true);
        leftSplitPane.getItems().add(Model.getInstance().WordsListWindow());

        dictionaryCheckMenuItem.setSelected(true);
        rightSplitPane.setPrefWidth(200);
        rightSplitPane.getItems().add(0, model.getInstance().DictionaryWindow());

        wordDescriptionCheckMenuItem.setSelected(true);
        if (dictionaryCheckMenuItem.isSelected()) {
            rightSplitPane.getItems().add(Model.getInstance().WordDescriptionWindow());
        }
        if (!dictionaryCheckMenuItem.isSelected()) {
            wordDescriptionCheckMenuItem.setSelected(false);
        }

    }

    @FXML
    private void selectWordsCheckMenuItem() throws IOException {
        if (wordsCheckMenuItem.isSelected()) {
            leftSplitPane.getItems().add(Model.getInstance().getPanes().get("wordsList"));
        }
        else {
            leftSplitPane.getItems().remove(Model.getInstance().getPanes().get("wordsList"));
        }
    }

    @FXML
    private void selectDictionaryCheckMenuItem() throws IOException {
        if (dictionaryCheckMenuItem.isSelected()) {
            rightSplitPane.setPrefWidth(200);
            rightSplitPane.getItems().add(0, Model.getInstance().getPanes().get("dictionary"));
        }
        if (!dictionaryCheckMenuItem.isSelected()) {
            if (wordDescriptionCheckMenuItem != null) {
                if (wordDescriptionCheckMenuItem.isSelected()) {
                    rightSplitPane.getItems().remove(Model.getInstance().getPanes().get("dictionary"));
                    rightSplitPane.getItems().remove(Model.getInstance().getPanes().get("wordDescription"));
                    wordDescriptionCheckMenuItem.setSelected(false);
                }
            }
            rightSplitPane.getItems().remove(Model.getInstance().getPanes().get("dictionary"));
            rightSplitPane.setPrefWidth(0);
        }
    }

    @FXML
    private void selectWordDescriptionCheckMenuItem() throws  IOException {
        if (wordDescriptionCheckMenuItem.isSelected()) {
            if (dictionaryCheckMenuItem.isSelected()) {
                rightSplitPane.getItems().add(Model.getInstance().getPanes().get("wordDescription"));
            }
            if (!dictionaryCheckMenuItem.isSelected()) {
                wordDescriptionCheckMenuItem.setSelected(false);
            }
        }
        if (!wordDescriptionCheckMenuItem.isSelected()) {
            rightSplitPane.getItems().remove(Model.getInstance().getPanes().get("wordDescription"));
        }
    }

    @FXML
    private void selectCreateMenuItem() throws IOException {
        model.CreationProjectWindow();
    }
}
