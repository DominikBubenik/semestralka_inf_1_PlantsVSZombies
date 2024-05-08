import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import java.util.ArrayList;

/**
 * trieda vytvara objekt Slnecnica ktorej priradi polohu X a Y a tiez pocet zivotov.
 * Slnecnica vytvara Slnka a moze byt odstranena objektom Zombie pri ich strete
 *
 * @author Dominik Bubeník
 *
 */
public class Slnecnica {
    private int poziciaX;
    private int poziciaY;
    private int zivoty;
    private int indexZombie;
    private ArrayList<Zombie> zoznamUtokZom;
    private Zombie utociaciZombie;
    private Slnko slnko;
    private Obrazok obrazok;
    private Manazer manazer;
    /*
     * konstruktor inicializuje atributy a vytvori obrazok, ktory reprezentuje Slnecnicu
     */
    public Slnecnica(int poziciaX, int poziciaY, int zivoty) {
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.zivoty = zivoty;

        this.zoznamUtokZom = new ArrayList<Zombie>();

        this.obrazok = new Obrazok("obrazky/slnecnica.png", this.poziciaX, this.poziciaY);
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

    public Slnko getSlnko() {
        return this.slnko;
    }

    /*
       pokial sa je nejaky Zombie v zozname utociacich Zombie, tak metoda porovnava kolko ma Slnecnica zivotov a kolko ma utociaci Zombie zivotov
       ak ma utociaci Zombie aspon jeden zivot, tak Slnecnici sa ubera zivot v casovom intervale
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
    metoda dostane pomocou parametra Zombie ktory utoci na Slnecnicu a jeho index v ArrayListe a priradi ho do zoznamu utociacich Zombie
     */
    public void utok(Zombie zombie, int indexZombie) {
        this.utociaciZombie = zombie;
        this.zoznamUtokZom.add(zombie);
        this.indexZombie = indexZombie;
    }

    /*
    metoda odstrani Slnecnicu
     */
    public void zmizni() {
        this.obrazok.skry();
        this.manazer.prestanSpravovatObjekt(this);
    }
    /*
    metoda odstrani Slnko
     */
    public void odstranSlnko() {
        this.slnko.zmizni();
        this.slnko = null;
    }

    /*
    metoda vytvory nove Slnko pri Slnecnici
     */
    public void noveSlnko() {
        if (this.slnko == null) {
            this.slnko = new Slnko(this.poziciaX, this.poziciaY, false);
        }
    }
}
