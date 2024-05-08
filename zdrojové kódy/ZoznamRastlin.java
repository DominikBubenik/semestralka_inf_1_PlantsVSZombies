import java.util.ArrayList;
/**
 * v tejto triede vytvaram 5 arraylistov, do ktorych pridavam vsetky Rastliny v danej linii
 * 
 * @author Dominik Bubeník
 */
public class ZoznamRastlin {
    
    private ArrayList<Rastlina> prvaLinia;
    private ArrayList<Rastlina> druhaLinia;
    private ArrayList<Rastlina> tretiaLinia;
    private ArrayList<Rastlina> stvrtaLinia;
    private ArrayList<Rastlina> piataLinia;
    
    /*
     * konstruktor inicializuje atributy
     */
    public ZoznamRastlin() {
        this.prvaLinia = new ArrayList<>();
        this.druhaLinia = new ArrayList<>();
        this.tretiaLinia = new ArrayList<>();
        this.stvrtaLinia = new ArrayList<>();
        this.piataLinia = new ArrayList<>();
    }
    
    public ArrayList<Rastlina> getZotnamRastlin(int linia) {
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
    metoda prida Rastlinu do daneho arraylistu podla linie
     */
    public void pridajRastlinu(Rastlina rast, int linia) {
        if (linia == 0) {
            this.prvaLinia.add(rast);
        } else if (linia == 1) {
            this.druhaLinia.add(rast);
        } else if (linia == 2) {
            this.tretiaLinia.add(rast);
        } else if (linia == 3) {
            this.stvrtaLinia.add(rast);
        } else {
            this.piataLinia.add(rast);
        }
    }
}
