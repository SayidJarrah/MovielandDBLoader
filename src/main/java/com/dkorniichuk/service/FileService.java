package com.dkorniichuk.service;

import javafx.scene.control.TreeItem;

import java.io.File;
import java.util.List;

public interface FileService {

    TreeItem<File> createDirectoryTree();

    void processFiles(List<File> selectedFiles);
}
