import fri.shapesge.Kruh;
import fri.shapesge.Manazer;

/**
 *trieda vytvara objekt Strela, ktoremu priradi polohu X a Y
 * objekt po strete so Zombie alebo po prekroceni hracej plochy zmizne
 * 
 * @author Dominik Bubeník

 */
public class Strela {

    private int posun;
    private int poziciaX;
    private int poziciaY;
    private Manazer manazer;
    private Kruh kruh;

    /*
     * konstruktor inicializuje atributy a vytvori kruh so suradnicami, ktory reprezentuje Strelu
     */
    public Strela(int poziciaX, int poziciaY) {
        this.posun = 5;
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;

        this.kruh = new Kruh(poziciaX , poziciaY);
        this.kruh.zmenFarbu("black");
        this.kruh.zobraz();

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

    }

    public int getPoziciaX() {
        return this.poziciaX;
    }

    public int getPoziciaY() {
        return this.poziciaY;
    }

    public void koniecStrely() {
        this.kruh.skry();
        this.kruh = null;
    }

    /*
    metoda pomocou manazere posuva strelu smerom vpravo, zaroven porovnava stret so Zombie
    po strete alebo prekroceni dlzky hracej plochy zmizne
     */
    public void posunStrely() {
        if (this.kruh != null ) {

            if (VlnaZombie.getVlnaZombie().getPolohaZombieX(this.poziciaY / 100) < this.getPoziciaX() && VlnaZombie.getVlnaZombie().getPolohaZombieY(this.poziciaY / 100) == this.getPoziciaY()) {
                VlnaZombie.getVlnaZombie().odstranZombie(this.poziciaY / 100);
                this.koniecStrely();
            } else if (this.getPoziciaX() < 800) {
                this.poziciaX += this.posun;
                this.kruh.posunVodorovne(this.posun);

            } else {
                this.koniecStrely();
            }
        } 
    }

}
