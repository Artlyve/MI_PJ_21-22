import java.util.ArrayList;

public class Sommet {

    /*** CONSTANTES ***/
    public enum color {Green, Orange, Red};
    public final static color DEFAULT_COLOR = color.Green;

    /*** ATTRIBUTS ***/
    private int value;
    private color c;
    private ArrayList<Sommet> elders = new ArrayList<>();

    /*** CONSTRUCTEUR ***/
    public Sommet(int value) {
        this.value = value;
        this.c = DEFAULT_COLOR;
    }

    /*** === METHODES === ***/
    public static ArrayList<Sommet> Initialize(int number) {
        ArrayList<Sommet> sommets = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Sommet s = new Sommet((i + 1));
            sommets.add(s);
        }
        return sommets;
    }

    public int getValue() {
        return this.value;
    }

    public color getColor() {
        return this.c;
    }

    public ArrayList<Sommet> getElders() {
        return this.elders;
    }

    public int getDistance() {
        return this.elders.size();
    }


    public void setGreen() {
        this.c = DEFAULT_COLOR;
        if (this.elders.size() > 0) {
            this.elders = new ArrayList<>();
        }
    }

    public void setOrange() {
        this.c = color.Orange;
    }

    public void setRed() {
        this.c = color.Red;
    }

    public void addElders(Sommet eld) {

        if (eld != null) {

            for (Sommet s : eld.getElders()) {
                this.elders.add(s);
            }
            this.elders.add(eld);
        }
    }

  /********* Methode d'affichage *********/
    private String eldToString() {
        String res = "[";
        for (Sommet s : this.elders) {
            res += " " + s.getValue();
        }
        res += " ]";
        return res;
    }


    private String printDistance() {
        String res = " | [ROOT]";
        if (this.getDistance() != 0) {
            res = " | Distance from [" + this.elders.get(0).getValue() + "] = " + this.getDistance();
        }
        return res;
    }

    @Override
    public String toString() {
        return "\n SOMMET [" + this.value + "] : Color = " + this.c +
                " | Elders = " + this.eldToString() +
                printDistance() + "\n";
    }
}
