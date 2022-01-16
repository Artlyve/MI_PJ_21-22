import java.util.ArrayList;
import java.util.LinkedList;

public class TestLinearGraphDFSBis extends TestLinearGraphDFS {



    /*** === CONSTRUCTEURS ===
     * @param sommetNumber***/
    public TestLinearGraphDFSBis(int sommetNumber) {
        super(sommetNumber);
    }



    public void isOrange(Sommet[] g){
        boolean b = false;
        for(int i = 0; i < g.length; i++){
            if(g[i].getColor() == Sommet.color.Orange){
                b = true;
                break;
            }
        }
        if(b){
            System.out.println("Circuit dans le graphe !!");
        }
    }

    @Override
    public void parcoursProfondeur (GraphLinearBis G) {

        int sommet = 0;
        Sommet newS = G.getVertex(sommet);


        while (newS.getColor() != Sommet.color.Green) {
            newS = G.getVertex(sommet);
            newS.setGreen();
            sommet++;
        }

        int root = sommet;
        this.queue = new LinkedList<>();
        this.out = new ArrayList<>();


        System.out.print("\n\n   --------\n   Début : " + sommet + " *\n   --------\n");


        this.addQueue(newS, null);
        newS.setOrange();

        // === PARCOURS
        int count = 0;
        while (this.queue.size() != 0) {

            Sommet[] adj = G.getAdjacencyList(sommet);

            this.remQueue(newS);
            newS.setRed();

            isOrange(adj); ///Recherche si notre graphe comporte un circuit


            for (Sommet v : adj) {
                if (v.getColor() == Sommet.color.Green) {
                    this.addQueue(v, newS);
                    v.setOrange();
                    this.cc[v.getValue() - 1] = root;
                }
            }
        }
        System.out.print("\n\t\tF = []\n");
        printDistances();                                               //On affiche les distances

        if (!G.thereIsGreen()) {                                        //Une fois le graphe est complètement visité
            System.out.print("\n\n=== END OF THE DEPTH COURSE ===\n");
            printCC();                                                  //On affiche le tableau des composantes connexes
        }
    }
}
