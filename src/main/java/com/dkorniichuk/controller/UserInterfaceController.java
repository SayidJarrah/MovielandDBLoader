package com.dkorniichuk.controller;

import com.dkorniichuk.service.FileService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class UserInterfaceController implements Initializable {
    Logger logger = LoggerFactory.getLogger(UserInterfaceController.class);

    @FXML
    TreeView<String> directoriesTree;
    @FXML
    ListView<String> filesList;
    @FXML
    TextField inputHost;
    @FXML
    TextField inputPort;
    @FXML
    TextField inputSchema;
    @FXML
    TextField inputUser;
    @FXML
    TextField inputPassword;
    @FXML
    Button buttonTestConnection;
    @FXML
    Button buttonImportData;
    @Autowired
    private FileService fileService;


    public void initialize(URL location, ResourceBundle resources) {
        logger.info("start initializing...");
       // setTreeMockUpData();
        directoriesTree.setRoot(fileService.createDirectoryTree());
        setListMockUpData();

    }

    private void setTreeMockUpData() {
        TreeItem<String> root = new TreeItem<>("Root");
        root.setExpanded(true);

        TreeItem<String> nodeA = new TreeItem<>("nodeA");
        TreeItem<String> nodeB = new TreeItem<>("nodeB");
        root.getChildren().addAll(nodeA, nodeB);
        nodeA.setExpanded(true);

        TreeItem<String> nodeA1 = new TreeItem<>("nodeA1");
        TreeItem<String> nodeA2 = new TreeItem<>("nodeA2");
        nodeA.getChildren().addAll(nodeA1, nodeA2);

        directoriesTree.setRoot(root);
    }

    private void setListMockUpData() {
        List<String> items = new ArrayList();
        items.add("item1");
        items.add("item2");
        items.add("item3");

        ObservableList oItems = FXCollections.observableList(items);
        filesList.setItems(oItems);


        filesList.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
            public ObservableValue<Boolean> call(String item) {
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) ->
                        System.out.println("Check box for "+item+" changed from "+wasSelected+" to "+isNowSelected)//TODO: rewrite with lambda
                );
                return observable ;
            }
        }));
    }
}
