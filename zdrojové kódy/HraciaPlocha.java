import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import javax.swing.JOptionPane;
/**
 * trieda vytvara hraciu plochu pre hru, vytvara ikony a stvorcovu siet pre hru, reaguje na kliknutia mysou
 *
 * @author Dominik Bubeník
 */
public class HraciaPlocha {

    private PolickoDispleja[][] displej;
    private Obrazok koniecIkona;
    private Obrazok ponukaRastliny;
    private Obrazok ponukaSlnecnice;
    private boolean[][] obsadenost;
    private boolean aktivovanaRastlina;
    private boolean aktivovanaSlnecnica;
    private Manazer manazer;
    private final int velkostPolicka = 100;
    private final int dlzka = 5;
    private final int sirka = 9;
    private int pocetZivotovRastliny;
    private ElementyHry eHra;

    /*
    konstruktor inicializuje atributy, vytvara stvorcovu hraciu plochu a ikony pre interakciu hraca
     */
    public HraciaPlocha(int pocetZivotovRastliny, int pocetVln, int pocetZivotovZombie) {

        this.displej = new PolickoDispleja[this.dlzka][this.sirka];
        this.obsadenost = new boolean[this.sirka][this.dlzka];
        this.pocetZivotovRastliny = pocetZivotovRastliny;

        this.koniecIkona = new Obrazok("obrazky/exit.png", 670, 500);
        this.koniecIkona.zobraz();
        this.ponukaRastliny = new Obrazok("obrazky/neaktivnaRast.png", 0, 500);
        this.ponukaRastliny.zobraz();
        this.ponukaSlnecnice = new Obrazok("obrazky/neaktivnaSlnecnica.png", 105, 500);
        this.ponukaSlnecnice.zobraz();

        Obrazok pozadieDispleja = new Obrazok("obrazky/pozadieDispleja.png", 205, 500);
        pozadieDispleja.zobraz();

        this.aktivovanaRastlina = false;
        this.aktivovanaSlnecnica = false;
        VlnaZombie.getVlnaZombie();
        VlnaZombie.getVlnaZombie().setVlny(pocetVln, pocetZivotovZombie);

        for (int i = 0; i < this.dlzka; i++) {
            for (int j = 0; j < this.sirka; j++) { 
                PolickoDispleja p = new PolickoDispleja(j * this.velkostPolicka, i * this.velkostPolicka, this.velkostPolicka);
                if ((i + j) % 2 == 0) {
                    p.setFarbaSvetla();
                } else {
                    p.setFarbaTmava();
                }
                this.displej [i][j] = p;
            }
        }

        this.eHra = new ElementyHry();
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this.eHra);
        this.manazer.spravujObjekt(this);
    }

    /*
    metoda zaznamenava kliknutie mysou na hracej ploche, podla polohy kliknutia vyhodnocuje co sa ma stat
     */
    public void vyberSuradnice (int x, int y) {
        //kliknutie na slnko
        if (this.eHra.getSlnko() != null) {
            if (x >= this.eHra.getSlnko().getPolohaX() && x <= this.eHra.getSlnko().getPolohaX() + 100 && y >= this.eHra.getSlnko().getPolohaY() && y <= this.eHra.getSlnko().getPolohaY() + 100) {
                this.eHra.odstranSlnko();
                this.eHra.setZobrazenieSlnka(true);
            }
        }
        //kliknutie na ikonu "exit", skonci sa program
        if (x >= 700 && y >= 500 && y <= 600) {
            System.exit(0);
            int odpoved = JOptionPane.showConfirmDialog(null, "Chces skoncit program?", "Koniec", JOptionPane.YES_NO_OPTION);

            if (odpoved == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        //kliknutie na ponuku Rastliny alebo Slnecnice, tym ju hrac aktivuje aby ich mohol vytvorit
        if (x <= 100 && y >= 500 & y <= 600) {
            this.aktivovanaRastlina = true;
            this.ponukaRastliny.zmenObrazok("obrazky/aktivnaRast.png");
            this.aktivovanaSlnecnica = false;
            this.ponukaSlnecnice.zmenObrazok("obrazky/neaktivnaSlnecnica.png");
        } else if (x >= 105 && x <= 205 && y >= 500 & y <= 600) {
            this.aktivovanaSlnecnica = true;
            this.ponukaSlnecnice.zmenObrazok("obrazky/aktivnaSlnecnica.png");
            this.aktivovanaRastlina = false;
            this.ponukaRastliny.zmenObrazok("obrazky/neaktivnaRast.png");
        }
        x = x / this.velkostPolicka;
        y = y / this.velkostPolicka;

        if (x > this.displej[0].length - 1 || y > this.displej.length - 1) {
            System.out.println("Klikol si vedla");
            return;
        }

        // kliknutie na Slnko, ktore vytvorila Slnecnica
        try {
            for (Slnecnica sln : this.eHra.getZoznamSlnecnic().getZotnamRastlin(y)) {
                if (sln.getSlnko().getPolohaX() == (x * 100)) {
                    sln.odstranSlnko();
                    this.eHra.pridajPocetSlnk(50);
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        //kliknutie pre vytvorenie Rastliny alebo Slnecnice na hracej ploche
        if (!this.eHra.getObsadenostPola(x, y)) {
            System.out.println("ciste pole");
            if (this.aktivovanaRastlina) {
                this.eHra.novaRastlina(x * 100, y * 100, this.pocetZivotovRastliny);
                this.aktivovanaRastlina = false;
                this.ponukaRastliny.zmenObrazok("obrazky/neaktivnaRast.png");
            } else if (this.aktivovanaSlnecnica) {
                this.eHra.novaSlnecnica(x * 100, y * 100, this.pocetZivotovRastliny);
                this.aktivovanaSlnecnica = false;
                this.ponukaSlnecnice.zmenObrazok("obrazky/neaktivnaSlnecnica.png");
            } else {
                System.out.println("Nemas aktivovanu rast");
            }

        } else {
            System.out.println("obsadene pole");
        }

    }

    public void koniecHry() {
        this.manazer.prestanSpravovatObjekt(this);
        this.manazer.prestanSpravovatObjekt(this.eHra);

    }

}
