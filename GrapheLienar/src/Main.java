public class Main {

    public static void main(){
        TestRandomLinearGraph graphe = new TestRandomLinearGraph();
        graphe.makeIt();

        TestLinearGraphDFS graphe1 = new TestLinearGraphDFS(10);
        graphe1.parcoursProfondeur(graphe.getMyGraph());

        TestLinearGraphDFSBis dfsBis = new TestLinearGraphDFSBis(10);
        dfsBis.parcoursProfondeur(graphe.getMyGraph());
    }
}
