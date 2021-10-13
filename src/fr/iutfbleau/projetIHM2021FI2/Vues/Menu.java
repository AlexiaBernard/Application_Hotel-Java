package Vues;
import fr.iutfbleau.projetIHM2021FI2.Vues.*;
import javax.swing.*;
import java.awt.*;

public class Menu {
    private JFrame fenetre = new JFrame("Menu");
    private JPanel retrouver = new JPanel();
    private JLabel textRetrouver = new JLabel("Retrouver réservation");
    private JButton ref = new JButton("référence");
    private JButton np = new JButton("Nom Prénom");

    /**
     * Constructeur qui crée et affiche le Menu
     */
    public Menu(){
        this.fenetre.setSize(1000, 800);
        this.fenetre.setLocation(0, 0);
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.retrouver.add(this.textRetrouver);
        this.retrouver.add(this.ref);
        this.retrouver.add(this.np);
        this.fenetre.add(this.retrouver);

        this.ref.addActionListener(new TraitementReference(this.fenetre));
        this.np.addActionListener(new TraitementNomPrenom(this.fenetre));

		this.fenetre.setVisible(true);
    }
}
