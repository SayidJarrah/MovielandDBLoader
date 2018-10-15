package com.dkorniichuk.service.impl;

import com.dkorniichuk.dao.FileDao;
import com.dkorniichuk.dao.utils.Node;
import com.dkorniichuk.service.FileService;
import javafx.scene.control.TreeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Override
    public TreeItem<String> createDirectoryTree() {
        Node<File> directoryTree = fileDao.load();

        TreeItem<String> root = new TreeItem<>(directoryTree.getData().getName());
        TreeItem<String> tree = traverse(directoryTree, root);
        return tree;
    }

    public TreeItem<String> traverse(Node<File> parentNode, TreeItem<String> root) {
        List<Node<File>> nodes = parentNode.getChildren();
        for (Node<File> node : nodes) {
            System.out.println(node.getData().getName());
            if(node.getData().isDirectory()) {
                TreeItem<String> treeItem = new TreeItem<>(node.getData().getName());
                root.getChildren().add(treeItem);
                traverse(node, treeItem);
            }
        }
        return root;
    }

    @Override
    public String getFilesList() {
        return null;
    }
}
