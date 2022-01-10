public class Arc {



    enum sens{
        XtoY,
        YtoX
    }

    private int x;
    private int y;
    private sens mySens;


    public Arc(int x, int y, sens mySens) {
        this.x = x;
        this.y = y;
        this.mySens = mySens;
    }
}
