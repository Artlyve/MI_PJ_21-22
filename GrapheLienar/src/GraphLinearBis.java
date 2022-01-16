public class GraphLinearBis {

    protected Sommet[][] listeAdj;
    public Sommet[] ensSommets;


    public GraphLinearBis(int n){
        listeAdj = new Sommet[n][];

        ensSommets = new Sommet[n];

    }

    public int order(){
        return this.ensSommets.length;
    }

    public int degree(int x){
        return this.listeAdj[x-1].length;
    }


    public void setAdjacencyList(int x, Sommet[] neighborhood){
        for (int i = 0; i < neighborhood.length; i++)
        {
            this.listeAdj[x][i] = neighborhood[i];
        }
    }

    public Sommet[] getAdjacencyList(int x){
        return this.listeAdj[x-1];
    }

    public Sommet[] vertexSet(){
        return this.ensSommets;
    }


    public Sommet getVertex(int sommet) {
        Sommet res = null;

        if (isVertex(sommet)) {
            this.ensSommets[sommet-1] = res;
        }
        return res;
    }

    public boolean thereIsGreen() {
        boolean res = false;

        if (this.ensSommets.length > 0) {
            for (Sommet sommet : this.ensSommets) {
                if (sommet.getColor() == Sommet.color.Green) {
                    res = true;
                    break;
                }
            }
        }

        else {
            System.out.print("\n\t Graphe vide \n");
        }

        return res;
    }



    public boolean isVertex(int sommet) {
        boolean res = false;
        if (sommet <= this.listeAdj.length && sommet > 0) {
            res = true;
        }
        return res;
    }

    private boolean isLinked_aux(int v1, int v2) {

        boolean res = false;

        if (v1 == v2) {
            System.out.print("\n\t Les deux sommets sont égaux on ne peut effectuer la suite\n");
        }

        else if (this.isVertex(v1) && this.isVertex(v2) && this.degree(v1) != 0) {

            Sommet[] tmpS1 = this.getAdjacencyList(v1);

            //TEST POUR LE PREMIER SOMMET
            for (int i = 0; i < tmpS1.length; i++) {
                if (tmpS1[i].getValue() == v2) {
                    res = true;
                    break;
                }
            }
        }

        return res;
    }

    public boolean isLinked(int v1, int v2) {
        return (this.isLinked_aux(v1, v2) && this.isLinked_aux(v2, v1));
    }

}

