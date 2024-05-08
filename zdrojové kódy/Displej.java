import fri.shapesge.Obdlznik;

/**
 * trieda vytvara sedem segmentovy displej pre zobrazenie digitalnych cisiel v urcitej velkosti a umiestneni na hracej ploche
 *
 * @author Dominik Bubeník
 */
public class Displej {
    private Obdlznik ob1;
    private Obdlznik ob2;
    private Obdlznik ob3;
    private Obdlznik ob4;
    private Obdlznik ob5;
    private Obdlznik ob6;
    private Obdlznik ob7;
    /*
     * konstruktor vytvori segmenty displeja obdlznikoveho tvaru
     */
    public Displej(int stranaA, int stranaB, int polohaX, int polohaY) {
        this.ob1 = new Obdlznik(polohaX, polohaY);
        this.ob1.zmenStrany(stranaA, stranaB);
        this.ob1.zmenFarbu("yellow");
        this.ob2 = new Obdlznik(polohaX + stranaA, polohaY + stranaB);
        this.ob2.zmenStrany(stranaB, stranaA);
        this.ob2.zmenFarbu("yellow");
        this.ob3 = new Obdlznik(polohaX + stranaA, polohaY + 2 * stranaB + stranaA);
        this.ob3.zmenStrany(stranaB, stranaA);
        this.ob3.zmenFarbu("yellow");
        this.ob4 = new Obdlznik(polohaX, polohaY + 2 * stranaA + 2 * stranaB);
        this.ob4.zmenStrany(stranaA, stranaB);
        this.ob4.zmenFarbu("yellow");
        this.ob5 = new Obdlznik(polohaX - stranaB, polohaY + stranaA + 2 * stranaB);
        this.ob5.zmenStrany(stranaB, stranaA);
        this.ob5.zmenFarbu("yellow");
        this.ob6 = new Obdlznik(polohaX - stranaB, polohaY + stranaB);
        this.ob6.zmenStrany(stranaB, stranaA);
        this.ob6.zmenFarbu("yellow");
        this.ob7 = new Obdlznik(polohaX, polohaY + stranaA + stranaB);
        this.ob7.zmenStrany(stranaA, stranaB);
        this.ob7.zmenFarbu("yellow");

    }

    /*
    metoda zobrazi dane cislo v parametri
     */
    public void zobraz(int cislo) {

        switch ( cislo) {
            case 0:
                this.ob1.zobraz();
                this.ob2.zobraz();
                this.ob3.zobraz();
                this.ob4.zobraz();
                this.ob5.zobraz();
                this.ob6.zobraz();
                this.ob7.skry();
                break;
            case 1:
                this.ob1.skry();
                this.ob2.zobraz();
                this.ob3.zobraz();
                this.ob4.skry();
                this.ob5.skry();
                this.ob6.skry();
                this.ob7.skry();
                break;
            case 2:
                this.ob1.zobraz();
                this.ob2.zobraz();
                this.ob3.skry();
                this.ob4.zobraz();
                this.ob5.zobraz();
                this.ob6.skry();
                this.ob7.zobraz();
                break;
            case 3:
                this.ob1.zobraz();
                this.ob2.zobraz();
                this.ob3.zobraz();
                this.ob4.zobraz();
                this.ob5.skry();
                this.ob6.skry();
                this.ob7.zobraz();
                break;
            case 4:
                this.ob1.skry();
                this.ob2.zobraz();
                this.ob3.zobraz();
                this.ob4.skry();
                this.ob5.skry();
                this.ob6.zobraz();
                this.ob7.zobraz();
                break;
            case 5:
                this.ob1.zobraz();
                this.ob2.skry();
                this.ob3.zobraz();
                this.ob4.zobraz();
                this.ob5.skry();
                this.ob6.zobraz();
                this.ob7.zobraz();
                break;
            case 6:
                this.ob1.zobraz();
                this.ob2.skry();
                this.ob3.zobraz();
                this.ob4.zobraz();
                this.ob5.zobraz();
                this.ob6.zobraz();
                this.ob7.zobraz();
                break;
            case 7:
                this.ob1.zobraz();
                this.ob2.zobraz();
                this.ob3.zobraz();
                this.ob4.skry();
                this.ob5.skry();
                this.ob6.skry();
                this.ob7.skry();
                break;
            case 8:
                this.ob1.zobraz();
                this.ob2.zobraz();
                this.ob3.zobraz();
                this.ob4.zobraz();
                this.ob5.zobraz();
                this.ob6.zobraz();
                this.ob7.zobraz();
                break;
            case 9:
                this.ob1.zobraz();
                this.ob2.zobraz();
                this.ob3.zobraz();
                this.ob4.zobraz();
                this.ob5.skry();
                this.ob6.zobraz();
                this.ob7.zobraz();
                break;

        }

    }
    /*
    metoda zobrazi cislo 0
     */
    public void vynuluj() {
        this.ob1.skry();
        this.ob2.skry();
        this.ob3.skry();
        this.ob4.skry();
        this.ob5.skry();
        this.ob6.skry();
        this.ob7.skry();
    }
}
