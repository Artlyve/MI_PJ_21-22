public class GraphLinear {

    protected int[][] listeAdj;
    public int[] ensSommets;


    public GraphLinear(int n){
        listeAdj = new int[n][n];

        ensSommets = new int[n];
        for(int i = 1; i < this.ensSommets.length; i++){
            this.ensSommets[i-1] = i;
        }
        this.ensSommets[n-1] = 0;
    }

    public int order(){
        return this.ensSommets.length;
    }

    public int degree(int x){
        return this.listeAdj[x-1].length;
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
            this.listeAdj[x][i] = neighborhood[i];
        }
    }

    public int[] getAdjacencyList(int x){
        return this.listeAdj[x-1];
    }

    public int[] vertexSet(){
        return this.ensSommets;
    }

}
