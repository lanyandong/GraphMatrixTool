package cn.ydlan.graphmatrix;

public class Main {

    public static void main(String[] args) {

        String[] nodes= {"A","B","C","D","E"}; // 节点

        int[][] m = new int[nodes.length][nodes.length]; //根据节点数目生成邻接矩阵

        // 邻接矩阵初始化
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j <m[i].length; j++) {
                if(j == i) {
                    m[i][j] = 0;
                }
                else {
                    m[i][j]=Integer.MAX_VALUE;
                }
            }
        }

        // 定义边的关系
        m[0][1] = 5;
        m[1][4] = 3;
        m[1][2] = 2;
        m[4][2] = 4;
        m[0][3] = 6;
        m[3][4] = 8;

        //
        GraphMatrix mg = new GraphMatrix(m,nodes);   // 生成邻接矩阵
        String graph = mg.generateDG();              // 生成有向图

        String html = WriteHtml.generateHTML(graph); // 生成html字符串
        String file = "D:/有向图.html";
        WriteHtml.writeHTML(file, html);      // 输出为html文件


        graph = mg.generateUG();              // 生成无向图
        html = WriteHtml.generateHTML(graph); // 生成html字符串
        file = "D:/无向图.html";
        WriteHtml.writeHTML(file, html);      // 输出为html文件
    }
}
