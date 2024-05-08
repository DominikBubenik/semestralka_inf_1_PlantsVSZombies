import fri.shapesge.Stvorec;

/**
 *trieda vytvara stvorce s danou X a Y suradnicou a danou sirkov v dvoch farbach
 *
 * @author Dominik Bubeník

 */
public class PolickoDispleja {
    
    private Stvorec stvorec;
    /*
     * konstruktor vytvori stvorec so suradnicami X a Y a danou sirkou
     */
    public PolickoDispleja(int x, int y, int sirka) {
        this.stvorec = new Stvorec();
        this.stvorec.zmenStranu(sirka - 1);
        this.stvorec.posunVodorovne(-60 + x);
        this.stvorec.posunZvisle(-50 + y);
        this.stvorec.zmenFarbu("lawnGreenA");
        this.stvorec.zobraz();
    }
    
    public void setFarbaSvetla() {
        this.stvorec.zmenFarbu("lawnGreenA");
    }
    
    public void setFarbaTmava() {
        this.stvorec.zmenFarbu("lawnGreenB");
    }
    

}
