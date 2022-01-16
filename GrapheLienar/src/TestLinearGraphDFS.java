import java.util.ArrayList;
import java.util.LinkedList;

public class TestLinearGraphDFS {

    /*** === ATTRIBUTS === ***/
    protected LinkedList<Sommet> queue;
    protected ArrayList<Sommet> out;      //On y stockera les sommets rouges (sortis), plus pratique que la recherche
    protected int[] cc;                   //C'est ici que l'on inscrit les composantes connexes

    /*** === CONSTRUCTEURS === ***/
    public TestLinearGraphDFS(int sommetNumber) {
        this.cc = new int[sommetNumber];
    }

    /*** === METHODES === ***/

    public boolean isRelatedComp() {
        boolean res = false;                //false : aucune composante connexe, tous les sommets sont isolés
        for (int i : this.cc) {
            if (i != 0) {
                res = true;                 //true : il existe des composantes connexes
                break;
            }
        }
        return res;
    }

    public LinkedList<Sommet> getQueue() {
        return this.queue;
    }

    public boolean isRelated() {

        boolean res = isRelatedComp();     //Si tous les sommets sont d'emblée tous isolés, cela ne sert à rien de chercher (règle au préalable le pb du 0 cf. ci-dessous)

        if (res) {
            int fstRoot = this.cc[0];           //On prend la première racine mais on aurait pu prendre une racine au hasard

            for (int d : this.cc) {             //Pour l'ensemble des sommets, on teste le point de départ (pb du 0 : si tous les sommets étaient à 0, le graphe aurait été connexe)
                if (d != fstRoot) {
                    res = false;                //Si le point de départ de deux sommets est différent, alors le graphe n'est pas connexe
                    break;                      //Et alors on peut sortir de la boucle
                }
            }
        }
        return res;
    }

    public void addQueue(Sommet newSommet, Sommet elder) {

        if (newSommet.getColor() == Sommet.color.Orange) {
            this.queue.addLast(newSommet);                  //On l'ajoute à la file
            newSommet.setRed();                          //Il passe en Orange
            newSommet.addElders(elder);
        }
    }

    public void remQueue(Sommet newVertex) {

        if (newVertex.getColor() == Sommet.color.Orange) {
            this.queue.removeFirst();                       //On le retire de la file
            newVertex.setRed();                             //Il passe en Rouge
            this.out.add(newVertex);                        //On le stocke dans la file de sortie
        }
    }

    //UN JOLI AFFICHAGE D'UN TABLEAU ASSOCIANT CHAQUE SOMMET A LA RACINE DE SA COMPOSANTE CONNEXE
    public void printCC() {
        System.out.print("\n\tRELATED COMPONENTS BOARD :\n\n\t  Vertexes :\t");
        //LES SOMMETS
        for (int i = 0; i < this.cc.length; i++) {
            //UNIQUEMENT POUR ALIGNER LES CHIFFRES AVEC LES NOMBRES
            if (i < 9) {
                System.out.print("| 0" + (i + 1) + " |");               //On ajoute un '0' devant un chiffre pour éviter un décalage
            }
            else {
                System.out.print("| " + (i + 1) + " |");
            }
        }
        System.out.print("\n\t  Roots :\t");
        //LES RACINES
        for (int i = 0; i < this.cc.length; i++) {
            //UNIQUEMENT POUR ALIGNER LES CHIFFRES AVEC LES NOMBRES
            if (this.cc[i] < 10) {
                System.out.print("| 0" + this.cc[i] + " |");            //On ajoute un '0' devant un chiffre pour éviter un décalage
            }
            else {
                System.out.print("| " + this.cc[i] + " |");
            }
        }
        System.out.print("\n\n");
    }

    public void printDistances() {
        //SI LA LISTE NE POSSEDE PAS QU'UN UNIQUE ELEMENT
        if (this.out.size() > 1) {
            System.out.print("\n\t=== DISTANCES FROM THE BEGIN (" + this.out.get(0).getValue() + ") ===\n\t==\n");
            for (int i = 1; i < this.out.size(); i++) {
                Sommet v = this.out.get(i);
                System.out.print("\t==\t" + v.getValue() + "\t: " + v.getDistance() + "\n");
            }
            System.out.print("\t==\n\t=====================================\n");
        }
    }

    /*** === PARCOURS EN PROFONDEUR === ***/

    public void parcoursProfondeur(GraphLinearBis G) {

        int sommet = 0;                                              //Un random qui attribut le début du parcours en largeur à un sommet du graphe
        Sommet newS = G.getVertex(sommet);                          //Pointeur sur le sommet


        while (newS.getColor() != Sommet.color.Green) {
            newS = G.getVertex(sommet);
            newS.setGreen();
            sommet++;
        }

        int root = sommet;
        this.queue = new LinkedList<>();
        this.out = new ArrayList<>();


        System.out.print("\n\n   *****************\n   * => BEGIN : " + sommet + " *\n   *****************\n");


        this.addQueue(newS, null);
        newS.setOrange();

        // === PARCOURS
        int count =0;
        while (this.queue.size() != 0) {

            Sommet[] adj = G.getAdjacencyList(sommet);

            this.remQueue(newS);
            newS.setRed();

            for (Sommet v : adj) {
                if(v.getColor() == Sommet.color.Green){
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
