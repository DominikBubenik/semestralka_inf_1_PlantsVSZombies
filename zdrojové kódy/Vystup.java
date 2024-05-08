import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * trieda obsahuje GUI pre pouzivatela, moze si vybrat zo 3 moznosti hry alebo si moze zadat vlastne parametre hry
 *
 * @author Dominik Bubeník
 */
public class Vystup implements ActionListener {
    private JFrame okno;
    private JPanel lista;
    private JButton lvl1;
    private JButton lvl2;
    private JButton lvl3;
    private JButton vlastnyLvl;
    private HraciaPlocha hp;

    /*
    konstruktor vytvory uvitacie okno a okno pre zadanie levelu
     */
    public  Vystup() {
        JOptionPane.showMessageDialog(null, "Vitaj v hre Plants vs Zombies!");

        this.okno = new JFrame();
        this.lista = new JPanel();

        this.lista.setBounds(500, 500, 100, 100);
        this.lista.setLayout(new GridLayout(0 , 1));
        this.lvl1 = new JButton("Level 1");
        this.lvl2 = new JButton("Level 2");
        this.lvl3 = new JButton("Level 3");
        this.vlastnyLvl = new JButton("Vlastne");

        this.lvl1.addActionListener(this);
        this.lvl2.addActionListener(this);
        this.lvl3.addActionListener(this);
        this.vlastnyLvl.addActionListener(this);


        this.lista.add(this.lvl1);
        this.lista.add(this.lvl2);
        this.lista.add(this.lvl3);
        this.lista.add(this.vlastnyLvl);

        this.okno.setSize(800, 800);
        this.okno.setLocationRelativeTo(null);
        this.okno.add(this.lista, BorderLayout.CENTER);
        this.okno.setVisible(true);
    }

    /*
    po kliknuti na danu moznost sa vyvori hra
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.lvl1) {
            System.out.println("Level 1");
            this.hp = new HraciaPlocha(10, 10, 3);
            this.okno.dispose();
        } else if (e.getSource() == this.lvl2) {
            System.out.println("Level 2");
            this.hp = new HraciaPlocha(10, 20, 5);
            this.okno.dispose();
        } else if (e.getSource() == this.lvl3) {
            System.out.println("Level 3");
            this.hp = new HraciaPlocha(8, 25, 7);
            this.okno.dispose();
        } else if (e.getSource() == this.vlastnyLvl) {
            System.out.println("Level vlastne");
            int pocetZivotovRastliny = 0;
            int pocetZombie = 0;
            int pocetZivotovZombie = 0;
            try {
                pocetZivotovRastliny = Integer.parseInt(JOptionPane.showInputDialog("Zadaj pocet zivotov rastlin"));
                pocetZombie = Integer.parseInt(JOptionPane.showInputDialog("Zadaj pocet zombie"));
                pocetZivotovZombie = Integer.parseInt(JOptionPane.showInputDialog("Zadaj pocet zivotov zombie"));

            } catch (Exception exep) {
                System.out.println("Zly vstup");
                JOptionPane.showMessageDialog(null, "Zly vstup");
                exep.printStackTrace();
            }
            if (pocetZivotovZombie > 0 && pocetZombie > 0 && pocetZivotovRastliny > 0) {
                this.hp = new HraciaPlocha(pocetZivotovRastliny, pocetZombie, pocetZivotovZombie);
                this.okno.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Zly vstup");
            }

        }
    }
}
