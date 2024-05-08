import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import java.util.ArrayList;
/**
 * trieda vytvara objekt Rastlina ktorej priradi polohu X a Y a tiez pocet zivotov.
 * Rastlina vytvara strely a moze byt odstranena objektom Zombie pri ich strete
 * 
 * @author Dominik Bubeník
 *
 */
public class Rastlina {
    private Obrazok obrazok;
    private int poziciaX;
    private int poziciaY;
    private int zivoty;
    private int indexZombie;
    private Zombie utociaciZombie;
    private Manazer manazer;
    private ArrayList<Zombie> zoznamUtokZom;
    /*
     * konstruktor inicializuje atributy a vytvori obrazok, ktory reprezentuje Rastlinu
     */
    public Rastlina(int poziciaX, int poziciaY, int pocetZivotov) {
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.zivoty = pocetZivotov;

        this.zoznamUtokZom = new ArrayList<Zombie>();

        this.obrazok = new Obrazok("obrazky/rastlina.png", poziciaX, poziciaY);
        this.obrazok.zobraz();

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }

    public int getPoziciaX() {
        return this.poziciaX;
    }

    public int getPoziciaY() {
        return this.poziciaY;
    }

    public int getZivoty() {
        return this.zivoty;
    }

    /*
    pokial sa je nejaky Zombie v zozname utociacich Zombie, tak metoda porovnava kolko ma Rastlina zivotov a kolko ma utociaci Zombie zivotov
    ak ma utociaci Zombie aspon jeden zivot Rastline sa ubera zivot v casovom intervale
     */
    public void znizZivotyRastliny() {
        try {
            if (this.zoznamUtokZom.size() > 0) {
                if (this.zivoty == 0) {
                    this.zoznamUtokZom.clear();
                } else if (VlnaZombie.getVlnaZombie().getZoznamZombie(this.getPoziciaY() / 100).size() == 0) {
                    System.out.println();
                } else if (VlnaZombie.getVlnaZombie().getZoznamZombie(this.getPoziciaY() / 100).get(this.indexZombie).getZivoty() > 1) {
                    this.zivoty--;
                }
            }
        } catch (Exception e) {
            System.out.println("Znova chby pri znizZivotyRatliny");
            e.printStackTrace();
        }
    }
    /*
    metoda dostane pomocou parametra Zombie ktory utoci na Rastlinu a jeho index v ArrayListe a priradi ho do zoznamu utociacich Zombie
     */
    public void utok(Zombie zombie, int indexZombie) {
        this.utociaciZombie = zombie;
        this.zoznamUtokZom.add(zombie);
        this.indexZombie = indexZombie;
    }

    /*
    metoda skryje Rastlinu a prestane fungovat
     */
    public void zmizni() {
        this.obrazok.skry();
        this.manazer.prestanSpravovatObjekt(this);
    }

    /*
    metoda vytvara novu strelu v casovom intervale pomocou manazera
     */
    public void vystrel() {
        Strela str = new Strela(this.poziciaX, this.poziciaY);
    }

}
