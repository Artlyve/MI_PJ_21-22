import java.util.Random;
import java.util.Scanner;

public class GraphLinearEx1 extends GraphLienar{


    enum coin{
        PILE,
        Face
    }

    public GraphLinearEx1(int n) {
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


    public void makeIt(){
        Scanner sc = new Scanner( System.in );

    }

}
