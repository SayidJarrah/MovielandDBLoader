package com.dkorniichuk.controller;

import com.dkorniichuk.entity.DataSourceProperty;
import com.dkorniichuk.service.FileService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class UserInterfaceController implements Initializable {
    Logger logger = LoggerFactory.getLogger(UserInterfaceController.class);

    @FXML
    TreeView<File> directoriesTree;
    @FXML
    ListView<File> filesList;
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

    private List<File> selectedItems = new ArrayList<>();


    public void initialize(URL location, ResourceBundle resources) {
        logger.info("start initializing...");
        initDirectoryTree(directoriesTree);
        initFileList(filesList);
        initImportButton(buttonImportData);
        initTestConnectionButton(buttonTestConnection);

    }

    private void initDirectoryTree(TreeView treeView) {
        treeView.setRoot(fileService.createDirectoryTree());

        treeView.setCellFactory(new Callback<TreeView<File>, TreeCell<File>>() {
            @Override
            public TreeCell<File> call(TreeView<File> param) {
                return new TextFieldTreeCell<>(new StringConverter<File>() {
                    @Override
                    public String toString(File object) {
                        return object.getName();
                    }

                    @Override
                    public File fromString(String string) {
                        return null;
                    }
                });
            }
        });

        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<File>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<File>> observable, TreeItem<File> oldValue, TreeItem<File> newValue) {

                List<File> files = new ArrayList<>();
                for (File file : newValue.getValue().listFiles()) {
                    if (file.isFile()) {
                        files.add(file);
                    }
                }
                ObservableList oItems = FXCollections.observableList(files);
                filesList.setItems(oItems);
                selectedItems.clear();

            }
        });
    }

    private void initFileList(ListView listView) {
        listView.setCellFactory(CheckBoxListCell.forListView(new Callback<File, ObservableValue<Boolean>>() {

            public ObservableValue<Boolean> call(File item) {
                BooleanProperty observable = new SimpleBooleanProperty();

                observable.addListener((obs, wasSelected, isNowSelected) ->
                {
                    if (isNowSelected) {
                        selectedItems.add(item);
                    } else {
                        selectedItems.remove(item);
                    }

                });
                return observable;
            }
        }, new StringConverter<File>() {
            @Override
            public String toString(File object) {
                return object.getName();
            }

            @Override
            public File fromString(String string) {
                return null;
            }
        }));
    }

    private void initImportButton(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validateInputParameters(); //TODO
                instantiateDataSource();
                fileService.processFiles(selectedItems);
            }
        });
    }

    private void initTestConnectionButton(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validateInputParameters();
               // databaseService.testConnection();
            }
        });
    }

    private void validateInputParameters(){
       if (inputHost.getText().isEmpty()){
           System.out.println("empty");
       }
    }

    private void instantiateDataSource(){
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
       /* dataSourceProperty.setHost(inputHost.getText());
        dataSourceProperty.setPort(inputPort.getText());
        dataSourceProperty.setUser(inputUser.getText());
        dataSourceProperty.setPassword(inputPassword.getText());*/


        dataSourceProperty.setHost("jdbc:mysql://localhost/whd2");
        dataSourceProperty.setPort(inputPort.getText());
        dataSourceProperty.setUser("whd2");
        dataSourceProperty.setPassword("whd2");

        System.out.println(dataSourceProperty.toString());
    }


}
