package cn.ydlan.graphmatrix;

import java.util.LinkedList;

/**
 *  将一个数组的值存入二叉树中,并进行可视化输出
 */

public class BinTreeApp{

    public static void main(String[] args) {
        BinTree binTree = new BinTree();

        int[] array = {11, 12, 13, 4, 5, 6, 7};

        // 功能：把一个数组的值存入二叉树中,并进行可视化输出
        String treeStr = binTree.createBinTree(array);

        String html = WriteHtml.generateHTML(treeStr);
        String file = "D:/graph/二叉树.html";  // 自定义输出路径
        WriteHtml.writeHTML(file, html);

    }
}


class BinTree {

    // 内部类（节点）
    private static class Node {
        Node leftChild;
        Node rightChild;
        int data;

        Node(int newData) {
            leftChild = null;
            rightChild = null;
            data = newData;
        }
    }

    // 生成二叉树DOT脚本
    public String createBinTree(int array[]) {

        LinkedList<Node> nodeList = new LinkedList<Node>();

        StringBuilder treeStr = new StringBuilder("graph g {");
        for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {

            nodeList.add(new Node(array[nodeIndex]));
            treeStr.append(nodeIndex + "[label=\"" + nodeList.get(nodeIndex).data + "\"];");
        }

        // 按照二叉树子节点与父节点的关系进行存取
        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {

            // 左孩子
            nodeList.get(parentIndex).leftChild = nodeList
                    .get(parentIndex * 2 + 1);
            // 右孩子
            nodeList.get(parentIndex).rightChild = nodeList
                    .get(parentIndex * 2 + 2);


            treeStr.append(parentIndex + "--" + (parentIndex * 2 + 1) + ";");

            treeStr.append(parentIndex + "--" + (parentIndex * 2 + 2) + ";");
        }


        // 最后一个父节点(可能没有右孩子)
        int lastParentIndex = array.length / 2 - 1;

        nodeList.get(lastParentIndex).leftChild = nodeList
                .get(lastParentIndex * 2 + 1);

        treeStr.append(lastParentIndex + "--" + (lastParentIndex * 2 + 1) + ";");

        // 如果数组的长度为奇数才建立右孩子
        if (array.length % 2 == 1) {
            nodeList.get(lastParentIndex).rightChild = nodeList
                    .get(lastParentIndex * 2 + 2);

            treeStr.append(lastParentIndex + "--" + (lastParentIndex * 2 + 2) + ";");
        }

        treeStr.append("}");
        //System.out.println("treeStr:" + treeStr);
        return treeStr.toString();
    }
}