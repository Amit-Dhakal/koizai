package com.koizai.commonservice.sharepoint.util;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderList {

    public static Node getNode(File node) {
        if (node.isDirectory()) {
            return new Node(node.getName(), "directory", null, getDirList(node));
        } else {
            return new Node(node.getName(), "file", null, null);
        }
    }

    public static List<Node> getDirList(File node) {
        List<Node> nodeList = new ArrayList<>();
        for (File n : node.listFiles()) {
            nodeList.add(getNode(n));
        }
        return nodeList;
    }

    @Data
    public static class Node {
        private String name;
        private String type;
        private String relativeUri;
        private List<Node> nodeList;

        public Node() {
        }

        public Node(String name, String type, String relativeUri, List<Node> nodeList) {
            this.name = name;
            this.type = type;
            this.relativeUri = relativeUri;
            this.nodeList = nodeList;
        }

    }

}