import java.util.ArrayList;
/**
 * v tejto triede vytvaram 5 arraylistov, do ktorych pridavam vsetky Slnecnice v danej linii
 *
 * @author Dominik Bubeník
 */
public class ZoznamSlnecnic {
    private ArrayList<Slnecnica> prvaLinia;
    private ArrayList<Slnecnica> druhaLinia;
    private ArrayList<Slnecnica> tretiaLinia;
    private ArrayList<Slnecnica> stvrtaLinia;
    private ArrayList<Slnecnica> piataLinia;
    /*
     * konstruktor inicializuje atributy
     */
    public ZoznamSlnecnic() {
        this.prvaLinia = new ArrayList<>();
        this.druhaLinia = new ArrayList<>();
        this.tretiaLinia = new ArrayList<>();
        this.stvrtaLinia = new ArrayList<>();
        this.piataLinia = new ArrayList<>();
    }

    public ArrayList<Slnecnica> getZotnamRastlin(int linia) {
        if (linia == 0) {
            return this.prvaLinia;
        } else if (linia == 1) {
            return this.druhaLinia;
        } else if (linia == 2) {
            return this.tretiaLinia;
        } else if (linia == 3) {
            return this.stvrtaLinia;
        } else {
            return this.piataLinia;
        }
    }
    /*
    metoda prida Slnecnicu do daneho arraylistu podla linie
     */
    public void pridajRastlinu(Slnecnica sln, int linia) {
        if (linia == 0) {
            this.prvaLinia.add(sln);
        } else if (linia == 1) {
            this.druhaLinia.add(sln);
        } else if (linia == 2) {
            this.tretiaLinia.add(sln);
        } else if (linia == 3) {
            this.stvrtaLinia.add(sln);
        } else {
            this.piataLinia.add(sln);
        }
    }
}
