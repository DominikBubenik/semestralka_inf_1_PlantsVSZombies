import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
/**
 *trieda vytvara objekt Slnko, ktoremu priradi polohu X a Y a hodnotu typu boolean ci sa ma hybat alebo nie
 *
 * @author Dominik Bubeník

 */
public class Slnko {
    
    private int polohaX;
    private int polohaY;
    private int smer;
    private boolean pohyb;
    private Manazer manazer;
    private Obrazok obrazok;
    /*
     * konstruktor inicializuje atributy a vytvori obrazok, ktory reprezentuje Slnko
     */
    public Slnko(int polohaX, int polohaY, boolean pohyb) {
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        this.smer = 1;
        this.pohyb = pohyb;

        this.obrazok = new Obrazok("obrazky/slnko.png", this.polohaX, this.polohaY);
        this.obrazok.zobraz();
        
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }
    
    public int getPolohaX() {
        return this.polohaX;
    }
    
    public int getPolohaY() {
        return this.polohaY;
    }

    /*
    metoda zabezpecuje pohyb slnka z jednej strany hracej plochy na druhu
     */
    public void posunSlnka() {
        if (this.pohyb) {
            if (this.polohaX >= 800) {
                this.smer *= -1;
            } else if (this.polohaX <= 0) {
                this.smer *= -1;
            }

            if (this.obrazok != null) {
                this.obrazok.posunVodorovne(5 * this.smer);
                this.polohaX += 5 * this.smer;
            }
        }

    }
    /*
    metoda skryje Slnko
     */
    public void zmizni() {
        this.obrazok.skry();
        this.obrazok = null;
     
    }
}
