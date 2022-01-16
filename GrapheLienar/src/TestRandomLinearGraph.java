import java.util.Random;
import java.util.Scanner;

public class TestRandomLinearGraph {


    enum coin{
        PILE,
        Face
    }

    private GraphLinearBis myGraph;

    public GraphLinearBis getMyGraph() {
        return myGraph;
    }

    public void setMyGraph(GraphLinearBis myGraph) {
        this.myGraph = myGraph;
    }


    /**********   Notre pile/Face  ********/
    public coin randomCoin(){
        Random r = new Random();
        int n = r.nextInt(2);
        switch (n){
            case 0:
                return coin.PILE;
            case 1:
                return coin.Face;
            default:
                System.out.println("Coin default");
        }
        return null;
    }


    /*********   Génération du graphe   ***********/
    public void makeIt(){
        Scanner sc = new Scanner( System.in );
        int n = sc.nextInt();

        myGraph = new GraphLinearBis(n);
        for(int i = 0; i < n-1; i++){
            coin c = randomCoin();

            if(myGraph.ensSommets[i+1].getValue() != 0){

                if(c == coin.PILE){
                    c = randomCoin();
                    if(c == coin.PILE){
                        int x = myGraph.ensSommets[i].getValue();
                        Sommet[] y = {myGraph.ensSommets[i+1]};
                        System.out.println("x : " + x + " " + y[0] );
                        myGraph.setAdjacencyList(x, y);
                    }else{
                        int y = myGraph.ensSommets[i+1].getValue();
                        Sommet[] x = {myGraph.ensSommets[i]};
                        System.out.println("tab x : " + x[0] + " " + y );
                        myGraph.setAdjacencyList(y, x);
                    }
                }
            }else {break;}
        }
    }


}
