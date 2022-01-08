public class GraphLienar {

    protected int[][] listeAdj;
    protected byte[][] matriceAdj;
    public int[] ensSommets;


    public GraphLienar(int n){
        listeAdj = new int[n][n];

        matriceAdj = new byte[n][n];

        ensSommets = new int[n];
        for(int i = 1; i < this.ensSommets.length; i++){
            this.ensSommets[i] = i;
        }

    }

    public int order(){
        return this.ensSommets.length;
    }
/*
    public int outDegree(int x){

    }

    public int inDegree(int x){

    }*/

    public int Degree(int x){
        return this.listeAdj[x-1].length;
    }

    public boolean isVertex(int x){
        return x >= 1 || x <= order();
    }

    public boolean isEdge(int x, int y){
        boolean res = false;
        for(int i = 0; i < this.listeAdj[x].length; i++)
        {
            if(this.listeAdj[x-1][i] == y)
            {
                res = true;
            }
        }
        return res;
    }

    public void setAdjacencyList(int x, int[] neighborhood){
        for (int i = 0; i < neighborhood.length; i++)
        {
            this.listeAdj[x-1][i] = neighborhood[i];
        }
    }

    public int[] getAdjacencyList(int x){
        return this.listeAdj[x-1];
    }

    public int[] vertexSet(){

        return this.ensSommets;
    }

    public void setAdjacencyMatrix(byte[][] matrix){

        int len = matrix.length;
        int last = 0;
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if (matrix[i-1][j] == 1) {
                    ensSommets[last] = j+1;
                    last++;
                }
            }
        }

    }
    public byte[][] getAdjacencyMatrix(){
        return matriceAdj;
    }

}
