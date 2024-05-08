import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import javax.swing.JOptionPane;
/**
 * trieda vytvara objekt Zombie ktoremu priradi polohu X a Y a tiez pocet zivotov.
 * Zombie sa pohybuje z pravej strany hracej plochy. Pokial sa dostane az na lavu stranu hra sa konci.
 *
 * @author Dominik Bubeník
 *
 */
public class Zombie {
    
    private int posun;
    private int zivoty;
    private int polohaX;
    private int polohaY;
    private boolean zastav;
    private Manazer manazer;
    private Obrazok obrazok;
    
    /*
     * konstruktor inicializuje atributy a vytvori obrazok, ktory reprezentuje Zombie
     */
    public Zombie(int polohaX, int polohaY, int pocetZivotov) {
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        this.zivoty = pocetZivotov;

        this.posun = -2;
        this.zastav = false;

        this.obrazok = new Obrazok("obrazky/zombie.png", polohaX, polohaY);
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

    public int getZivoty() {
        return this.zivoty;
    }

    public void setPohyb(boolean stoj) {
        this.zastav = stoj;
    }

    /*
    metoda znizi pocet zivotov Zombie
     */
    public void znizZivoty() {
        this.zivoty--;
    }

    /*
    metoda odstrani Zombie
     */
    public void zmizni() {
        this.obrazok.skry();
        this.manazer.prestanSpravovatObjekt(this);

    }
    /*metoda posunie Zombie ak sa atribut stoj rovna false
    *ak je poloha mensia ako 0 skonci sa hra
     */
    public void posunZombie() {

        if (!this.zastav) {
            if (this.obrazok != null) {
                this.obrazok.posunVodorovne(this.posun);
                this.polohaX += this.posun;
            }

            if (this.polohaX <= 0) {
                JOptionPane.showMessageDialog(null, "Prehral si! Zombie sa dostal k domu!");
                System.exit(0);
            }
        }

    }
}
