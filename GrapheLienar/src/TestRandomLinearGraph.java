import java.util.Random;
import java.util.Scanner;

public class TestRandomLinearGraph extends GraphLinear {


    enum coin{
        PILE,
        Face
    }

    private GraphLinear myGraph;

    public TestRandomLinearGraph(int n) {
        super( n );
    }

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

    public int rand(int n){

        Random rd = new Random();
        return rd.nextInt(n + 1);
    }


    public void makeIt(){
        Scanner sc = new Scanner( System.in );
        int n = sc.nextInt();

        myGraph = new GraphLinear(n);
        for(int i = 0; i < n-1; i++){
            coin c = randomCoin();

            if(myGraph.ensSommets[i+1] != 0){

                if(c == coin.PILE){
                    c = randomCoin();
                    if(c == coin.PILE){
                        int x = myGraph.ensSommets[i];
                        int[] y = {myGraph.ensSommets[i+1]};
                        System.out.println("x : " + x + " " + y[0] );
                        myGraph.setAdjacencyList(x, y);
                    }else{
                        int y = myGraph.ensSommets[i+1];
                        int[] x = {myGraph.ensSommets[i]};
                        System.out.println("tab x : " + x[0] + " " + y );
                        myGraph.setAdjacencyList(y, x);
                    }
                }
            }else {break;}
        }
    }

    public static void main(String...args){
        TestRandomLinearGraph g = new TestRandomLinearGraph( 10);
        g.makeIt();/*
        for(int i = 0; i < g.myGraph.listeAdj.length; i++){
           for(int j = 0; j < g.myGraph.listeAdj[i].length; j++){
               System.out.print(g.myGraph.listeAdj[i][j] + " ");
           }
            System.out.println();
        }*/

        System.out.println();

        for(int k = 0; k < g.myGraph.ensSommets.length; k++){
            System.out.print(g.myGraph.ensSommets[k] + " ");
        }
        System.out.println();
        for(int k = 0; k < g.myGraph.getAdjacencyList(1).length; k++){
            System.out.print(g.myGraph.getAdjacencyList(1)[k] + " ");
        }

    }

}
