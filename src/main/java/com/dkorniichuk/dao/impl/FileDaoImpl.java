package com.dkorniichuk.dao.impl;

import com.dkorniichuk.dao.FileDao;
import com.dkorniichuk.dao.utils.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class FileDaoImpl implements FileDao {
    Logger logger = LoggerFactory.getLogger(FileDaoImpl.class);
    private static final String ROOT_PATH = "D:\\logs";

    @Override
    public Node<File> load() {
        Node<File> directoryTree = createTree(new Node<>(new File(ROOT_PATH)));
        System.out.println(directoryTree);
        return directoryTree;
    }

    private Node<File> createTree(Node<File> node) {
        File[] files = node.getData().listFiles();
        for (File file : files) {
            Node newNode = node.addChild(new Node(file));
            if (file.isDirectory()) {
                createTree(newNode);
            }
        }
        return node;
    }
}

