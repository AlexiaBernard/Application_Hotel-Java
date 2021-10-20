package fr.iutfbleau.projetIHM2021FI2.Vues;

import fr.iutfbleau.projetIHM2021FI2.MNP.*;
import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.Modele.*;
import fr.iutfbleau.projetIHM2021FI2.Constructeur.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.Constructeur.TraitementNomPrenom;
import fr.iutfbleau.projetIHM2021FI2.Constructeur.TraitementReference;

import java.awt.*;

public class Menu {
	private JFrame fenetre = new JFrame("Menu");

    private JLabel textRetrouver_ref = new JLabel("Retrouver réservation avec les références.");
    private JPanel retrouver_ref = new JPanel();
    private JTextField reference = new JTextField();
    private JButton valider_ref = new JButton("Valider");

    private JLabel textRetrouver_np = new JLabel("Retrouver réservation avec le nom et le prénom du client.");
    private JPanel retrouver_np = new JPanel();
    private JPanel jp_nom = new JPanel();
    private JPanel jp_prenom = new JPanel();
    private JLabel jl_nom = new JLabel("Nom : ");
    private JLabel jl_prenom = new JLabel("Prénom : ");
    private JTextField nom = new JTextField();
    private JTextField prenom = new JTextField();
    private JButton valider_np = new JButton("Valider");


    /* Message d'erreur*/
    private int erreur;
    private JLabel erreur_ref = new JLabel("Je n'ai pas trouvé de préreservation avec cette référence.");
    private JLabel erreur_np = new JLabel("Je n'ai pas trouvé de préreservation avec ces nom et prénom.");

	private JLabel test = new JLabel(" ");


    /**
     * Constructeur qui crée et affiche le Menu
     */
    public Menu(JFrame fenetre, int erreur){
		this.fenetre = fenetre;

        /* Pour les JTextField */
        this.nom.setFont(new Font("Serif", Font.BOLD, 20));
        this.nom.setPreferredSize(new Dimension(150, 30));
        this.prenom.setFont(new Font("Serif", Font.BOLD, 20));
        this.prenom.setPreferredSize(new Dimension(150, 30));
        this.reference.setFont(new Font("Serif", Font.BOLD, 20));
        this.reference.setPreferredSize(new Dimension(150, 30));

        /* Nom */
        this.jl_nom.setLabelFor(this.nom);
        this.jp_nom.add(this.jl_nom);
        this.jp_nom.add(this.nom);

        /* Prénom */
        this.jl_prenom.setLabelFor(this.prenom);
        this.jp_prenom.add(this.jl_prenom);
        this.jp_prenom.add(this.prenom);

		/* Erreur */
        this.erreur = erreur;

		/* Pour référence */
		this.retrouver_ref.setLayout(new GridLayout(4,1));
		this.retrouver_ref.add(this.textRetrouver_ref);
		this.retrouver_ref.add(this.reference);
		this.retrouver_ref.add(this.valider_ref);
		if(this.erreur == 1){
			this.erreur_ref.setForeground(new Color(231,54,54));
			this.retrouver_ref.add(this.erreur_ref);
		} else if (this.erreur==0){
			this.retrouver_ref.add(this.test);
		}

		/* Pour Nom Prénom */
		this.jl_prenom.setLabelFor(this.prenom);
		this.retrouver_np.setLayout(new GridLayout(5,1));
		this.retrouver_np.add(this.textRetrouver_np);
		this.retrouver_np.add(this.jp_nom);
		this.retrouver_np.add(this.jp_prenom);
		this.retrouver_np.add(this.valider_np);
		if(this.erreur == 2){
			this.erreur_np.setForeground(new Color(231,54,54));
			this.retrouver_np.add(this.erreur_np);
		} else if (this.erreur==0){
			this.retrouver_np.add(this.test);
		}
        

        this.fenetre.add(this.retrouver_ref,BorderLayout.SOUTH);
        this.fenetre.add(this.retrouver_np,BorderLayout.NORTH);

        /* Les Listener */
        this.valider_ref.addActionListener(new TraitementReference(this.fenetre, this.reference));
        this.valider_np.addActionListener(new TraitementNomPrenom(this.fenetre, this.nom, this.prenom));

		this.fenetre.setVisible(true);
    }
}
