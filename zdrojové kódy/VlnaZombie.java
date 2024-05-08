import java.util.ArrayList;
import fri.shapesge.Manazer;
import javax.swing.JOptionPane;
/**
 * tato trieda je typu Singletone, vytvara Zombie, ktorych potom pridava do danych arraylistov podla linie, v ktorej su vygenerovani
 *
 * @author Dominik Bubeník
 */
public class VlnaZombie {
    private Manazer manazer;
    private static VlnaZombie instancia;
    private ArrayList<Zombie> prvaLinia;
    private ArrayList<Zombie> druhaLinia;
    private ArrayList<Zombie> tretiaLinia;
    private ArrayList<Zombie> stvrtaLinia;
    private ArrayList<Zombie> piataLinia;
    private int pocetVln;
    private int pocetZombie;
    private int pocetZivotov;
    
    /*
     * konstruktor inicializuje atributy 
     */
    private VlnaZombie() {
        this.prvaLinia = new ArrayList<Zombie>();
        this.druhaLinia = new ArrayList<Zombie>();
        this.tretiaLinia = new ArrayList<Zombie>();
        this.stvrtaLinia = new ArrayList<Zombie>();
        this.piataLinia = new ArrayList<Zombie>();
        this.manazer = new Manazer();
        this.pocetVln = 0;
        this.pocetZombie = 0;
        this.manazer.spravujObjekt(this);
    }

    public static VlnaZombie getVlnaZombie() {
        if (VlnaZombie.instancia == null) {
            VlnaZombie.instancia = new VlnaZombie();
        }
        return VlnaZombie.instancia;
    }

    public ArrayList<Zombie> getZoznamZombie(int linia) {
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

    public int getPocetZivichZombie() {
        return this.prvaLinia.size() + this.druhaLinia.size() + this.tretiaLinia.size() + this.stvrtaLinia.size() + this.piataLinia.size();
    }
    /*
    metoda odstrani konkretneho Zombie z arraylistu, ak uz nie su ziadny zivi Zombie tak hra sa skonci
     */
    public void odstranZombie(int linia) {
        switch (linia) {
            case 0:
                this.prvaLinia.get(0).znizZivoty();
                if (this.prvaLinia.get(0).getZivoty() == 0) {
                    this.prvaLinia.get(0).zmizni();
                    this.prvaLinia.remove(0);
                }
                break;
            case 1:
                this.druhaLinia.get(0).znizZivoty();
                if (this.druhaLinia.get(0).getZivoty() == 0) {
                    this.druhaLinia.get(0).zmizni();
                    this.druhaLinia.remove(0);
                }
                break;
            case 2:
                this.tretiaLinia.get(0).znizZivoty();
                if (this.tretiaLinia.get(0).getZivoty() == 0) {
                    this.tretiaLinia.get(0).zmizni();
                    this.tretiaLinia.remove(0);
                }
                break;
            case 3:
                this.stvrtaLinia.get(0).znizZivoty();
                if (this.stvrtaLinia.get(0).getZivoty() == 0) {
                    this.stvrtaLinia.get(0).zmizni();
                    this.stvrtaLinia.remove(0);
                }
                break;
            case 4:
                this.piataLinia.get(0).znizZivoty();
                if (this.piataLinia.get(0).getZivoty() == 0) {
                    this.piataLinia.get(0).zmizni();
                    this.piataLinia.remove(0);
                }
                break;

        }
        if (VlnaZombie.getVlnaZombie().getPocetZivichZombie() == 0) {
            JOptionPane.showMessageDialog(null, "Vyhral si! Zabil si vsetkych Zombies!");
            System.exit(0);
        }
    }
    /*
    metoda vracia X-ovu polohu prveho Zombie v liste v danej linii
     */
    public int getPolohaZombieX(int linia) {
        switch (linia) {
            case 0:
                if (this.prvaLinia.size() > 0) {
                    return this.prvaLinia.get(0).getPolohaX();
                } else {
                    return 800;
                }
            case 1:
                if (this.druhaLinia.size() > 0) {
                    return this.druhaLinia.get(0).getPolohaX();
                } else {
                    return 800;
                }
            case 2:
                if (this.tretiaLinia.size() > 0) {
                    return this.tretiaLinia.get(0).getPolohaX();
                } else {
                    return 800;
                }

            case 3:
                if (this.stvrtaLinia.size() > 0) {
                    return this.stvrtaLinia.get(0).getPolohaX();
                } else {
                    return 800;
                }
            
            case 4:
                if (this.piataLinia.size() > 0) {
                    return this.piataLinia.get(0).getPolohaX();
                } else {
                    return 800;
                }
            default: 
                return -1;
        }

    }
    /*
    vracia Y-ovu poloho prveho Zombie v liste danej linie
     */
    public int getPolohaZombieY(int linia) {
        switch (linia) {
            case 0:
                return this.prvaLinia.get(0).getPolohaY();
            case 1:
                return this.druhaLinia.get(0).getPolohaY();
            case 2:
                return this.tretiaLinia.get(0).getPolohaY();
            case 3:
                return this.stvrtaLinia.get(0).getPolohaY();
            case 4:
                return this.piataLinia.get(0).getPolohaY();
            default: 
                return -1;
        }
    }

    /*
    metoda nastavuje celkovy pocet vygenerovanych Zombie
     */
    public void setVlny(int pocetVlnZ, int pocetZivotovZ ) {
        this.pocetVln = pocetVlnZ;
        this.pocetZivotov = pocetZivotovZ;
    }

    /*
    metoda vytvara noveho Zombie v nahodnej linii v danom casovom intervale az pokym nie je vytvoreny stanoveny pocet
     */
    public void novyZombie() {
        if (this.pocetZombie < this.pocetVln) {
            int polohaY = (int)(Math.random() * (4 - 0 + 1) + 0); 
            Zombie zombie  = new Zombie(800, polohaY * 100, this.pocetZivotov);//
            switch (polohaY) {
                case 0:
                    this.prvaLinia.add(zombie);
                    break;
                case 1:
                    this.druhaLinia.add(zombie);
                    break;
                case 2:
                    this.tretiaLinia.add(zombie);
                    break;
                case 3:
                    this.stvrtaLinia.add(zombie);
                    break;
                case 4:
                    this.piataLinia.add(zombie);
                    break;
            }

            this.pocetZombie++;
        } else {
            this.manazer.prestanSpravovatObjekt(VlnaZombie.instancia);
        }

    }
}
