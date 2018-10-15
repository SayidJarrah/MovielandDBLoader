package com.dkorniichuk.service;

import javafx.scene.control.TreeItem;

public interface FileService {

    TreeItem<String> createDirectoryTree();
    String getFilesList();
}
