import java.util.ArrayList;
import fri.shapesge.Manazer;

/**
 * trieda vytvara objekty Rastlina a Slnecnica, stara sa o stret objektov, zobrazovanie poctu Slnk a vytvaranie novych
 * 
 * @author Dominik Bubeník
 */
public class ElementyHry {
    private ZoznamRastlin zoznamRastlin;
    private ZoznamSlnecnic zoznamSlnecnic;
    private boolean[][] obsadenostPola;
    private Manazer manazer;
    private boolean zobrazenieSlnka;
    private Slnko slnko;
    private double pocetSlnk;
    private ArrayList<Zombie> spustacZombie;
    private Displej displejA;
    private Displej displejB;
    private Displej displejC;

    /*
    v konstruktore inicializujem atributy a nastavujem disiplej pre pocet Slnk
     */
    public ElementyHry() {
        this.zoznamRastlin = new ZoznamRastlin();
        this.zoznamSlnecnic = new ZoznamSlnecnic();
        this.spustacZombie = new ArrayList<Zombie>();

        this.zobrazenieSlnka = true;
        this.obsadenostPola = new boolean[9][5];
        this.pocetSlnk = 0;

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

        this.displejA = new Displej(20, 10, 250, 530);
        this.displejA.zobraz(0);
        this.displejB = new Displej(20, 10, 295, 530);
        this.displejB.zobraz(0);
        this.displejC = new Displej(20, 10, 340, 530);
        this.displejC.zobraz(0);
    }

    public ZoznamSlnecnic getZoznamSlnecnic() {
        return this.zoznamSlnecnic;
    }
    
    public boolean getObsadenostPola(int x, int y) {
        return this.obsadenostPola[x][y];
    }
    public Slnko getSlnko() {
        return this.slnko;
    }
    public void setZobrazenieSlnka(boolean zobraz) {
        this.zobrazenieSlnka = zobraz;
    }

    /*
    metoda vytvara novu Rastlinu (ak ma hrac dostatocny pocet Slnk), ktoru prida do zoznamuRastlin, nastavi obsadenie pola a znizi pocet Slnk
     */
    public void novaRastlina (int x, int y, int pocetZivotov) {
        if (this.pocetSlnk >= 100) {
            Rastlina rastlina = new Rastlina(x, y, pocetZivotov);

            this.zoznamRastlin.pridajRastlinu(rastlina, y / 100);
            this.obsadenostPola[x / 100][y / 100] = true;
            this.pridajPocetSlnk(-100);
        
        }
    }

    /*
    metoda vytvara novu Slnecnicu (ak ma hrac dostatocny pocet Slnk), ktoru prida do zoznamuSlnecnic, nastavi obsadenie pola a znizi pocet Slnk
     */
    public void novaSlnecnica (int x, int y, int pocetZivotov) {
        if (this.pocetSlnk >= 50) {
            Slnecnica slnecnica = new Slnecnica(x, y, pocetZivotov);

            this.obsadenostPola[x / 100][y / 100] = true;
            this.zoznamSlnecnic.pridajRastlinu(slnecnica, y / 100);
            this.pridajPocetSlnk(-50);

        }
    }

    /*
    metoda vytvori nove Slnko ak nie je ine mimo pola
     */
    public void noveSlnko() {
        if (this.zobrazenieSlnka) {
            this.slnko = new Slnko(1, 700, true);
            this.zobrazenieSlnka = false;
        }
    }

    /*
    metoda prida pocet Slnk podla zadanej hodnoty v parametri a zobrazi cislo na displeji
     */
    public void pridajPocetSlnk(double pocet) {
        if (this.pocetSlnk <= 900 || pocet < 0) {
            this.pocetSlnk += pocet;
        }

        this.displejA.vynuluj();
        this.displejA = null;
        this.displejA = new Displej(20, 10, 250, 530);

        this.displejB.vynuluj();
        this.displejB = null;
        this.displejB = new Displej(20, 10, 295, 530);
        this.displejA.zobraz((int)Math.floor(this.pocetSlnk / 100));
        this.displejB.zobraz((int)Math.floor((this.pocetSlnk % 100) / 10));

    }
    /*
    metoda skryje slnko a posle spravu pridajPocetSlnk s hodnotou parametra 50
     */
    public void odstranSlnko() {
        this.pridajPocetSlnk(50);
        this.slnko.zmizni();
        this.slnko = null;
    }

    /*
    metoda porovnava stret Rastliny a Zombie ak je Zombie vzdialeny z pravej strany o menej ako 50 jednotiek tak sa zastavi a zautoci na Rastlinu pomocou spravy Rastline utok(Zombie zombie, int index )
    ak Zombie znici Rastlinu, tak pokracuje dalej v pohybe inak zmizne Zombie
     */
    public void stretObjektov() {
        int poradieRastliny = -1;
        int poradieZombie = -1;
        int linia = -1;
        for (int i = 0; i < 5; i++) {
            for (Rastlina rast : this.zoznamRastlin.getZotnamRastlin(i)) {
                for (Zombie zombie : VlnaZombie.getVlnaZombie().getZoznamZombie(i)) {
                    if (rast.getPoziciaX() <= zombie.getPolohaX() && rast.getPoziciaX() + 50 >= zombie.getPolohaX()) {
                        zombie.setPohyb(true);
                        linia = i;
                        poradieRastliny = this.zoznamRastlin.getZotnamRastlin(i).indexOf(rast);
                        poradieZombie = VlnaZombie.getVlnaZombie().getZoznamZombie(i).indexOf(zombie);
                        rast.utok(zombie, poradieZombie);
                        this.spustacZombie.add(zombie);
                    }
                }
            }

            try {

                if (this.zoznamRastlin.getZotnamRastlin(linia).get(poradieRastliny).getZivoty() <= 0) {
                    int x = this.zoznamRastlin.getZotnamRastlin(linia).get(poradieRastliny).getPoziciaX();
                    int y = this.zoznamRastlin.getZotnamRastlin(linia).get(poradieRastliny).getPoziciaY();  
                    this.obsadenostPola[x / 100][y / 100] = false;
                    this.zoznamRastlin.getZotnamRastlin(linia).get(poradieRastliny).zmizni();
                    this.zoznamRastlin.getZotnamRastlin(linia).remove(poradieRastliny);
                    for (Zombie z : this.spustacZombie) {
                        z.setPohyb(false);
                    }

                }
            } catch (Exception e) {
                //System.out.println("Something went wrong.");
            }
        }
    }

    /*
    metoda porovnava stret Slnecnice a Zombie ak je Zombie vzdialeny z pravej strany o menej ako 50 jednotiek tak sa zastavi a zautoci na Slnecnicu pomocou spravy Slnecnici utok(Zombie zombie, int index )
    ak Zombie znici Slnecnicu, tak pokracuje dalej v pohybe inak zmizne Zombie
     */
    public void stretSlnecnice() {
        int poradieSlnecnice = -1;
        int poradieZombie = -1;
        int linia = -1;
        for (int i = 0; i < 5; i++) {
            for (Slnecnica sln : this.zoznamSlnecnic.getZotnamRastlin(i)) {
                for (Zombie zombie : VlnaZombie.getVlnaZombie().getZoznamZombie(i)) {
                    if (sln.getPoziciaX() <= zombie.getPolohaX() && sln.getPoziciaX() + 50 >= zombie.getPolohaX()) {
                        zombie.setPohyb(true);
                        linia = i;
                        poradieSlnecnice = this.zoznamSlnecnic.getZotnamRastlin(i).indexOf(sln);
                        poradieZombie = VlnaZombie.getVlnaZombie().getZoznamZombie(i).indexOf(zombie);
                        sln.utok(zombie, poradieZombie);
                        this.spustacZombie.add(zombie);
                    }
                }
            }

            try {

                if (this.zoznamSlnecnic.getZotnamRastlin(linia).get(poradieSlnecnice).getZivoty() <= 0) {
                    int x = this.zoznamSlnecnic.getZotnamRastlin(linia).get(poradieSlnecnice).getPoziciaX();
                    int y = this.zoznamSlnecnic.getZotnamRastlin(linia).get(poradieSlnecnice).getPoziciaY();
                    this.obsadenostPola[x / 100][y / 100] = false;
                    this.zoznamSlnecnic.getZotnamRastlin(linia).get(poradieSlnecnice).odstranSlnko();
                    this.zoznamSlnecnic.getZotnamRastlin(linia).get(poradieSlnecnice).zmizni();
                    this.zoznamSlnecnic.getZotnamRastlin(linia).remove(poradieSlnecnice);
                    for (Zombie z : this.spustacZombie) {
                        z.setPohyb(false);
                    }
                }
            } catch (Exception e) {
                //System.out.println("Something went wrong.");
            }

        }
    }
}
