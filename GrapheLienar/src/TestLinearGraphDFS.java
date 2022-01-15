import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class TestLinearGraphDFS {

    /*** === ATTRIBUTS === ***/
    private LinkedList<Sommet> queue;
    private ArrayList<Sommet> out;      //On y stockera les sommets rouges (sortis), plus pratique que la recherche
    private int[] cc;                   //C'est ici que l'on inscrit les composantes connexes

    /*** === CONSTRUCTEURS === ***/
    public TestLinearGraphDFS(int sommetNumber) {
        this.cc = new int[sommetNumber];
    }

    /*** === METHODES === ***/

    private boolean isRelatedComp() {
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

    private boolean isRelated() {

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

    private void addQueue(Sommet newSommet, Sommet elder) {

        if (newSommet.getColor() == Sommet.color.Green) {
            this.queue.addLast(newSommet);                  //On l'ajoute à la file
            newSommet.setOrange();                          //Il passe en Orange
            newSommet.addElders(elder);
        }
    }

    private void remQueue(Sommet newVertex) {

        if (newVertex.getColor() == Sommet.color.Orange) {
            this.queue.removeFirst();                       //On le retire de la file
            newVertex.setRed();                             //Il passe en Rouge
            this.out.add(newVertex);                        //On le stocke dans la file de sortie
        }
    }


    /*** === PARCOURS EN PROFONDEUR === ***/

    public void parcoursProfondeur(GraphLinearBis G) {

        Random rand = new Random();
        int sommet = rand.nextInt(G.order() - 1 + 1) + 1;       //Un random qui attribut le début du parcours en largeur à un sommet du graphe
        Sommet newS = G.getVertex(sommet);                          //Pointeur sur le sommet


        while (newS.getColor() != Sommet.color.Green) {         //Tant que l'on tombe sur un sommet qui a déjà été découvert
            sommet = rand.nextInt(G.order() - 1 + 1) + 1;       //On en prend un nouveau (pas optimisé, probabilité que cela cherche pendant longtemps)
            newS = G.getVertex(sommet);
        }

        int root = sommet;
        this.queue = new LinkedList<>();
        this.out = new ArrayList<>();


        System.out.print("\n\n   *****************\n   * => BEGIN : " + sommet + " *\n   *****************\n");


        this.addQueue(newS, null);

        // === PARCOURS
        while (this.queue.size() != 0) {

            Sommet[] adj = G.getAdjacencyList(sommet);


            for (Sommet v : adj) {
                this.addQueue(v, newS);                 //On ajoute le sommet à la liste et il passe en orange et on ajoute le parent (automatique)
                this.cc[v.getValue() - 1] = root;       //On rempli le tableau de composantes connexes
            }

            this.remQueue(newS);;                       //On a travaillé sur ce sommet, on le sort de la file et le passe en rouge (automatique)

            //NOUVEAU SOMMET
            if (this.queue.size() != 0) {
                newS = this.queue.getFirst();           //On prend le premier sommet de la file
                sommet = newS.getValue();               //Et on récupère sa valeur (utile à certaines fonctions)
            }
        }

/*        // === AFFICHAGE FINAL
        System.out.print("\n\t\tF = []\n");
        printDistances();                                               //On affiche les distances
        printRelatedComponent();                                        //On affiche la composane découverte à chaque fin de parcours

        if (!G.thereIsGreen()) {                                        //Une fois le graphe est complètement visité
            System.out.print("\n\n=== END OF THE WIDTH COURSE ===\n");
            printCC();                                                  //On affiche le tableau des composantes connexes
            G.setRelated(isRelated());                          //On indique àau graphe s'il est connexe ou non
        }*/
    }
}
