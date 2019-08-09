package cn.ydlan.graphmatrix;


// 创建图的邻接矩阵
public class GraphMatrix {
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
        return graph.toString();
    }
}
