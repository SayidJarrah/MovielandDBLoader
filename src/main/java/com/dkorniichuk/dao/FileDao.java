package com.dkorniichuk.dao;

import com.dkorniichuk.dao.utils.Node;

import java.io.File;

public interface FileDao {

    Node<File> load();
}
