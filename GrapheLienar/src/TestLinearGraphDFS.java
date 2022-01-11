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
    //VERIFIE SI TOUT LES SOMMETS NE SONT PAS ISOLES
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

    // === ACCESSEURS ===
    public LinkedList<Sommet> getQueue() {
        return this.queue;
    }

    //RENVOIE SI LE GRAPHE EST CONNEXE
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

    // === MUTATEURS ===

    //AJOUTE UN SOMMET A LA FILE, LE PASSE EN ORANGE ET LUI AJOUTE UN PARENT
    private void addQueue(Sommet newSommet, Sommet elder) {

        //SSI LE SOMMET N'A PAS DEJA ETE DECOUVERT
        if (newSommet.getColor() == Sommet.color.Green) {
            this.queue.addLast(newSommet);                  //On l'ajoute à la file
            newSommet.setOrange();                          //Il passe en Orange
            newSommet.addElders(elder);
        }
    }

    //POUR RETIRER UN SOMET DE LA FILE ET LE PASSER EN ROUGE
    private void remQueue(Sommet newVertex) {

        //SSI LE SOMMET A DEJA ETE DECOUVERT
        if (newVertex.getColor() == Sommet.color.Orange) {
            this.queue.removeFirst();                       //On le retire de la file
            newVertex.setRed();                             //Il passe en Rouge
            this.out.add(newVertex);                        //On le stocke dans la file de sortie
        }
    }

    // === AFFICHAGE ===

    /*private void printQueue() {
        if (this.queue.size() != 0) {
            System.out.print("\n\t\tF = [");
            for (Sommet s : this.queue) {
                System.out.print(" " + s.getValue());
            }
            System.out.print(" ]\n");
        }
    }

    private void printRelatedComponent() {
        if (this.out.size() > 1) {
            System.out.print("\n\tRelated Component = [");
            for (Sommet s : this.out) {
                System.out.print(" " + s.getValue());
            }
            System.out.print(" ]\n");
        }
    }

    //UN JOLI AFFICHAGE D'UN TABLEAU ASSOCIANT CHAQUE SOMMET A LA RACINE DE SA COMPOSANTE CONNEXE
    private void printCC() {
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

    private void printDistances() {
        //SI LA LISTE NE POSSEDE PAS QU'UN UNIQUE ELEMENT
        if (this.out.size() > 1) {
            System.out.print("\n\t=== DISTANCES FROM THE BEGIN (" + this.out.get(0).getValue() + ") ===\n\t==\n");
            for (int i = 1; i < this.out.size(); i++) {
                Sommet s = this.out.get(i);
                System.out.print("\t==\t" + s.getValue() + "\t: " + s.getDistance() + "\n");
            }
            System.out.print("\t==\n\t=====================================\n");
        }
    }*/

    /*** === PARCOURS EN PROFONDEUR === ***/

    public void parcoursProfondeur(GraphLinear G) {

        // === VARIABLES
        Random rand = new Random();
        int sommet = rand.nextInt(G.order() - 1 + 1) + 1;       //Un random qui attribut le début du parcours en largeur à un sommet du graphe
        Sommet newS = G.getVertex(vertex);                          //Pointeur sur le sommet

        //ON NE VA PAS BÊTEMENT REFAIRE UN PARCOURS DEJA REALISE
        while (newV.getColor() != Vertex.color.Green) {         //Tant que l'on tombe sur un sommet qui a déjà été découvert
            vertex = rand.nextInt(G.order() - 1 + 1) + 1;       //On en prend un nouveau (pas optimisé, probabilité que cela cherche pendant longtemps)
            newV = G.getVertex(vertex);
        }

        int root = vertex;
        this.queue = new LinkedList<>();
        this.out = new ArrayList<>();

        //AFFICHAGE
        System.out.print("\n\n   *****************\n   * => BEGIN : " + vertex + " *\n   *****************\n");

        //AJOUT DU PREMIER SOMMET A LA FILE
        this.addQueue(newV, null);                      //Le sommet est ajouté à la file, il passe en orange et il n'a pas de parents (null)

        // === PARCOURS
        while (this.queue.size() != 0) {

            Vertex[] adj = G.getAdjencyList(vertex);    //La suite d'adjacence du sommet en question

            //AFFICHAGE
            printQueue();                               //On affiche l'état de la file

            //POUR CHAQUE SOMMET DE LA SUITE D'ADJACENCE
            for (Vertex v : adj) {
                this.addQueue(v, newV);                 //On ajoute le sommet à la liste et il passe en orange et on ajoute le parent (automatique)
                this.cc[v.getValue() - 1] = root;       //On rempli le tableau de composantes connexes
            }

            this.remQueue(newV);;                       //On a travaillé sur ce sommet, on le sort de la file et le passe en rouge (automatique)

            //NOUVEAU SOMMET
            if (this.queue.size() != 0) {
                newV = this.queue.getFirst();           //On prend le premier sommet de la file
                vertex = newV.getValue();               //Et on récupère sa valeur (utile à certaines fonctions)
            }
        }

        // === AFFICHAGE FINAL
        System.out.print("\n\t\tF = []\n");
        printDistances();                                               //On affiche les distances
        printRelatedComponent();                                        //On affiche la composane découverte à chaque fin de parcours

        if (!G.thereIsGreen()) {                                        //Une fois le graphe est complètement visité
            System.out.print("\n\n=== END OF THE WIDTH COURSE ===\n");
            printCC();                                                  //On affiche le tableau des composantes connexes
            G.setRelated(isRelated());                          //On indique àau graphe s'il est connexe ou non
        }
    }
}
