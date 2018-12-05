package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.*;


public class ProjectController {
    @FXML
    private TreeView<String> projectTV;

    private TreeItem<String> root;

    @FXML
    private void initialize() {
        root = new TreeItem<String>();
    }

    private boolean containValueInParentTreeItem(TreeItem<String> parent, String childValue) {
        for (TreeItem<String> child: parent.getChildren()) {
            if (child.getValue().equals(childValue)) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public void loadAll(Map<String, List<String>> scanwords) {
        for (String block: scanwords.keySet()) {
            load(root, block, scanwords.get(block));
        }
    }

    public boolean load (TreeItem<String> parentItem, String block, List<String> scanwords) {
        TreeItem<String> blockItem = new TreeItem<String>(block);
        for (String scanword: scanwords) {
            if (containValueInParentTreeItem(blockItem, scanword)) {
                continue;
            }
            blockItem.getChildren().add(new TreeItem<String>(scanword));
        }
        if (containValueInParentTreeItem(parentItem, block)) {
            return false;
        }
        else {
            parentItem.getChildren().add(blockItem);
            return true;
        }
    }

    public boolean remove (String ... name) {
        if (name.length == 1) {
            TreeItem<String> element = null;
            for (TreeItem<String> child: root.getChildren()) {
                if (child.getValue().equals(name)) {
                    element = child;
                }
            }
            root.getChildren().remove(element);
        }
        else if (name.length == 2) {
            TreeItem<String> element = null;
            for (TreeItem<String> child: root.getChildren()) {
                if (child.getValue().equals(name[0])) {
                    element = child;
                }
            }
            TreeItem<String> finalElement = null;
            for (TreeItem<String> child: element.getChildren()) {
                if (child.getValue().equals(name[1])) {
                    finalElement = child;
                }
            }
            element.getChildren().remove(finalElement);
        }
        else {
            return false;
        }
        return  true;
    }
}
