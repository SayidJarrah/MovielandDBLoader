package com.dkorniichuk.service.impl;

import com.dkorniichuk.dao.FileDao;
import com.dkorniichuk.dao.utils.Node;
import com.dkorniichuk.service.FileService;
import javafx.scene.control.TreeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TreeItem<File> createDirectoryTree() {
        Node<File> directoryTree = fileDao.load();
        TreeItem<File> tree = convertCustomTreeToTreeView(directoryTree, new TreeItem(directoryTree.getData()));
        return tree;
    }


    public TreeItem<File> convertCustomTreeToTreeView(Node<File> parentNode, TreeItem<File> root) {
        List<Node<File>> nodes = parentNode.getChildren();
        for (Node<File> node : nodes) {
            if (node.getData().isDirectory()) {
                TreeItem<File> treeItem = new TreeItem(node.getData());
                root.getChildren().add(treeItem);
                convertCustomTreeToTreeView(node, treeItem);
            }
        }
        return root;
    }

    @Override
    public void processFiles(List<File> selectedFiles) {
        System.out.println(selectedFiles);
        jdbcTemplate.execute("SELECT SYSDATE()  FROM DUAL");
        List list = jdbcTemplate.queryForList("select * from database_info");
        System.out.println(list);
    }


}
