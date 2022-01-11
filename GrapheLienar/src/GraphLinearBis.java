public class GraphLinearBis {

    protected Sommet[][] listeAdj;
    public Sommet[] ensSommets;


    public GraphLinearBis(int n){
        listeAdj = new Sommet[n][n];

        ensSommets = new Sommet[n];
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

    /*** === METHODES === ***/

    // === VERTEX ===

    //RETOURNE LE SOMMET A PARTIR D'UNE VALEUR
    public Sommet getVertex(int sommet) {
        Sommet res = null;

        //ON CHERCHE SSI LE SOMMET EXISTE DANS LE GRAPHE
        if (isVertex(sommet)) {
            this.ensSommets[sommet-1] = res;
        }
        return res;
    }

    //RENVOIE UN BOOLEEN S'IL RESTE UN OU PLUSIEURS SOMMETS A DECOUVRIR
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
            System.out.print("\n\t [!] Your Graph is empty. [!]\n");
        }

        return res;
    }

    //AFFICHAGE DE TOUS LES SOMMETS DU GRAPHE
    public void printVertexes() {
        System.out.println("\n ====== VERTEXES LIST ======");
        for (Sommet sommet : this.ensSommets) {
            System.out.println(sommet);
        }
        System.out.print(" =========================\n");
    }


    // === WIDTH COURSE ===

    //SI ON A DEJA EFFECTUER UN PARCOURS EN LARGEUR SUR LE GRAPHE ET
    //QUE LE GRAPHE EST MODIFIE OU QUE L'ON REFAIT UN PARCOURS EN LARGEUR
    public void reInitVertexes() {
        for (Sommet sommet : this.ensSommets) {
            sommet.setGreen();
        }
    }

    // === GRAPH ===

    //VERIFIE SI LE SOMMET EXISTE BEL ET BIEN DANS LE GRAPHE
    public boolean isVertex(int sommet) {
        boolean res = false;
        if (sommet <= this.listeAdj.length && sommet > 0) {
            res = true;
        }
        return res;
    }

    //VERIFIE SI DEUX SOMMETS SONT LIES PAR UNE ARÊTE (EN SOMME S'ILS SONT ADJACENTS)

    // [AUXILIAIRE]
    private boolean isLinked_aux(int v1, int v2) {

        boolean res = false;

        //SI LES DEUX SOMMETS SONT EN FAIT UN SEUL ET UNIQUE SOMMET (BOUCLE)
        if (v1 == v2) {
            System.out.print("\n\t === Requested vertexes are equal, loops aren't accepted.\n");
        }

        //SINON SI LES DEUX SOMMETS DEMANDES FONT PARTIE DU GRAPHE ET S'ILS ONT CHACUN AU MOINS UN SOMMET ADJACENT
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

    // [PRINCIPALE]
    public boolean isLinked(int v1, int v2) {
        return (this.isLinked_aux(v1, v2) && this.isLinked_aux(v2, v1));
    }

}

