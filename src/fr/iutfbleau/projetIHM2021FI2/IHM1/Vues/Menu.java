package fr.iutfbleau.projetIHM2021FI2.IHM1.Vues;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.PrereservationFactory;
import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;

import java.awt.*;

public class Menu {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
	private JFrame fenetre = new JFrame("Menu");
    private int erreur;


    /**
     * Constructeur qui crée et affiche le Menu
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param erreur
     */
    public Menu(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, int erreur){
		this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.erreur = erreur;

        
    }

    public void run(){

    /*Pour Référence*/
    JLabel textRetrouver_ref = new JLabel("Retrouver réservation avec les références.");
    JPanel retrouver_ref = new JPanel();
    JTextField reference = new JTextField();
    JButton valider_ref = new JButton("Valider");

    /*Pour Nom et Prénom*/
    JLabel textRetrouver_np = new JLabel("Retrouver réservation avec le nom et le prénom du client.");
    JPanel retrouver_np = new JPanel();
    JPanel jp_nom = new JPanel();
    JPanel jp_prenom = new JPanel();
    JLabel jl_nom = new JLabel("Nom : ");
    JLabel jl_prenom = new JLabel("Prénom : ");
    JTextField nom = new JTextField();
    JTextField prenom = new JTextField();
    JButton valider_np = new JButton("Valider");


    /* Message d'erreur*/
    JLabel erreur_ref = new JLabel("Je n'ai pas trouvé de préreservation avec cette référence.");
    JLabel erreur_np = new JLabel("Je n'ai pas trouvé de préreservation avec ces nom et prénom.");

	JLabel test = new JLabel(" ");


        /* Pour les JTextField */
        nom.setFont(new Font("Serif", Font.BOLD, 20));
        nom.setPreferredSize(new Dimension(150, 30));
        prenom.setFont(new Font("Serif", Font.BOLD, 20));
        prenom.setPreferredSize(new Dimension(150, 30));
        reference.setFont(new Font("Serif", Font.BOLD, 20));
        reference.setPreferredSize(new Dimension(150, 30));

        /* Nom */
        jl_nom.setLabelFor(nom);
        jp_nom.add(jl_nom);
        jp_nom.add(nom);

        /* Prénom */
        jl_prenom.setLabelFor(prenom);
        jp_prenom.add(jl_prenom);
        jp_prenom.add(prenom);

		/* Pour référence */
		retrouver_ref.setLayout(new GridLayout(4,1));
		retrouver_ref.add(textRetrouver_ref);
		retrouver_ref.add(reference);
		retrouver_ref.add(valider_ref);
		if(this.erreur == 1){
			erreur_ref.setForeground(new Color(231,54,54));
			retrouver_ref.add(erreur_ref);
		} else if (this.erreur==0){
			retrouver_ref.add(test);
		}

		/* Pour Nom Prénom */
		jl_prenom.setLabelFor(prenom);
		retrouver_np.setLayout(new GridLayout(5,1));
		retrouver_np.add(textRetrouver_np);
		retrouver_np.add(jp_nom);
		retrouver_np.add(jp_prenom);
		retrouver_np.add(valider_np);
		if(this.erreur == 2){
			erreur_np.setForeground(new Color(231,54,54));
			retrouver_np.add(erreur_np);
		} else if (this.erreur==0){
			retrouver_np.add(test);
		}
        

        this.fenetre.add(retrouver_ref,BorderLayout.SOUTH);
        this.fenetre.add(retrouver_np,BorderLayout.NORTH);

        /* Les Listener */
        valider_ref.addActionListener(new TraitementReference(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement ,this.fenetre, reference));
        valider_np.addActionListener(new TraitementNomPrenom(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, nom, prenom));

		this.fenetre.setVisible(true);
    }
}
