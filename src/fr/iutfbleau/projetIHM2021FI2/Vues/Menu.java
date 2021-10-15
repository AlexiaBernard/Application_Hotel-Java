package Vues;
import fr.iutfbleau.projetIHM2021FI2.Vues.*;
import javax.swing.*;
import java.awt.*;

public class Menu {
    private JFrame fenetre = new JFrame("Menu");

    private JLabel textRetrouver = new JLabel("Retrouver réservation avec le nom et le prénom du client.");
    private JPanel retrouver_np = new JPanel();
    private JTextField nom = new JTextField();
    private JTextField prenom = new JTextField();
    private JButton valider_np = new JButton("Valider");

    private JLabel textRetrouver_ref = new JLabel("Retrouver réservation avec les références.");
    private JPanel retrouver_ref = new JPanel();
    private JTextField reference = new JTextField();
    private JButton valider_ref = new JButton("Valider");

    /**
     * Constructeur qui crée et affiche le Menu
     */
    public Menu(){
        this.fenetre.setSize(1000, 800);
        this.fenetre.setLocation(0, 0);
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.retrouver_np.add(this.textRetrouver_np);
        this.retrouver_np.add(this.nom);
        this.retrouver_np.add(this.prenom);
        this.retrouver_np.add(this.valider_np);
        this.fenetre.add(this.retrouver_np);

        this.retrouver_ref.add(this.textRetrouver_ref);
        this.retrouver_ref.add(this.reference);
        this.retrouver_ref.add(this.valider_ref);
        this.fenetre.add(this.retrouver_ref);

        this.valider_ref.addActionListener(new TraitementReference(this.fenetre));
        this.valider_np.addActionListener(new TraitementNomPrenom(this.fenetre));

		this.fenetre.setVisible(true);
    }
}
