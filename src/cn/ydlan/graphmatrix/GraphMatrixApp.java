package cn.ydlan.graphmatrix;


/**
 * 将图保存为邻接矩阵，在根据图的结构性质，装换为对应的DOT脚本
 * 将DOT脚本输出为HTML文件（SVG）
 */

public class GraphMatrixApp{

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
        String file = "D:/graph/有向图.html";
        WriteHtml.writeHTML(file, html);      // 输出为html文件


        graph = mg.generateUG();              // 生成无向图
        html = WriteHtml.generateHTML(graph); // 生成html字符串
        file = "D:/graph/无向图.html";
        WriteHtml.writeHTML(file, html);      // 输出为html文件
    }
}




// 创建图的邻接矩阵
class GraphMatrix {
    private int[][] edges;	    //存放边的信息
    private String[] vertexes;	//存放结点的信息

    public GraphMatrix(int[][] edges, String[] vertexes) {
        super();
        this.edges = edges;
        this.vertexes = vertexes;
    }

    public int[][] getEdges() {
        return edges;
    }

    public String[] getVertexes() {
        return vertexes;
    }


    // 其实就是将图的信息封装成dot语言格式
    public String generateUG() {	//产生无向图dot语言
        int size=edges.length;
        StringBuilder graph=new StringBuilder("graph g {");
        for(int i=0;i<size;i++) {	//读取顶点的信息
            graph.append(i+"[label=\""+vertexes[i]+"\"];");
        }

        for(int i=0;i<size;i++) {	//读取边的信息
            for(int j=i;j<size;j++) {
                if(edges[i][j]!=Integer.MAX_VALUE&&edges[i][j]!=0) {	//两者相邻
                    graph.append(i+"--"+j+"[label=\""+edges[i][j]+"\"];");
                }
            }
        }
        graph.append("}");
        return graph.toString();
    }


    public String generateDG() {	//产生有向图dot语言
        int size=edges.length;
        StringBuilder graph=new StringBuilder("digraph g {");

        for(int i=0;i<size;i++) {	//读取顶点的信息
            graph.append(i+"[label=\""+vertexes[i]+"\"];");
        }

        for(int i=0;i<size;i++) {	//读取边的信息
            for(int j=0;j<size;j++) {
                if(edges[i][j]!=Integer.MAX_VALUE&&edges[i][j]!=0) {	//两者相邻
                    graph.append(i+"->"+j+"[label=\""+edges[i][j]+"\"];");
                }
            }
        }
        graph.append("}");

        System.out.println(graph.toString());
        return graph.toString();
    }
}
