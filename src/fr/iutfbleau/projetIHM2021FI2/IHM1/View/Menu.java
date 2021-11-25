package fr.iutfbleau.projetIHM2021FI2.IHM1.View;

import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;

/**
 * <code>Menu</code> est une vue Elle affiche le menu de l'IHM
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class Menu {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private JPanel centre;

    /**
     * Récupération des informations pour la création du menu
     * 
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API
     *                                    correspondant à la préreservation
     * @param grandLivreDOrAPISeulement   modèle non persistant de Réservation
     * @param fenetre                     la fenetre
     * @param centre                      centre de la fenetre
     */
    public Menu(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement,
            JFrame fenetre, JPanel centre) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.centre = centre;
    }

    /**
     * Affichage du menu
     */
    public void run() {
        Color fond = new Color(222,212,249);
        
        /* Pour Référence */
        JLabel textRetrouver_ref = new JLabel("Retrouver réservation avec la référence.");
        textRetrouver_ref.setHorizontalAlignment(SwingConstants.CENTER);
        textRetrouver_ref.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        JPanel retrouver_ref = new JPanel();
        JTextField reference = new JTextField();
        //Panel du boutton pour pouvoir définir sa taille
        JPanel valider_ref_jp = new JPanel();
        JButton valider_ref = new JButton("Valider");
        valider_ref.setPreferredSize(new Dimension(100, 30));
        valider_ref_jp.setBackground(fond);
        valider_ref_jp.add(valider_ref);

        /* Pour Nom et Prénom */
        JLabel textRetrouver_np = new JLabel("Retrouver réservation avec le nom et le prénom du client.");
        textRetrouver_np.setHorizontalAlignment(SwingConstants.CENTER);
        textRetrouver_np.setFont(new Font("Serif", Font.HANGING_BASELINE, 20));
        JPanel retrouver_np = new JPanel();
        JPanel jp_nom_prenom = new JPanel();
        JLabel jl_nom = new JLabel("Nom : ");
        jl_nom.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel jl_prenom = new JLabel("Prénom : ");
        jl_prenom.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField nom = new JTextField();
        JTextField prenom = new JTextField();

        //Panel du boutton pour pouvoir définir sa taille
        JPanel valider_np_jp = new JPanel();
        JButton valider_np = new JButton("Valider");
        valider_np.setPreferredSize(new Dimension(100, 30));
        valider_np_jp.setBackground(fond);
        valider_np_jp.add(valider_np);

        JLabel blanc = new JLabel(" ");

        /* Pour les JTextField */
        nom.setFont(new Font("Serif", Font.BOLD, 20));
        nom.setPreferredSize(new Dimension(150, 30));
        prenom.setFont(new Font("Serif", Font.BOLD, 20));
        prenom.setPreferredSize(new Dimension(150, 30));
        reference.setFont(new Font("Serif", Font.BOLD, 20));
        reference.setPreferredSize(new Dimension(150, 30));

        /* Nom Prénom */
        jl_nom.setLabelFor(nom);
        jl_prenom.setLabelFor(prenom);
        jp_nom_prenom.setLayout(new GridLayout(2,3));
        jp_nom_prenom.setBackground(fond);
        jp_nom_prenom.add(jl_nom);
        jp_nom_prenom.add(nom);
        jp_nom_prenom.add(jl_prenom);
        jp_nom_prenom.add(prenom);

        /* Pour référence */
        retrouver_ref.setLayout(new GridLayout(4, 1));
        retrouver_ref.setBackground(fond);
        retrouver_ref.add(textRetrouver_ref);
        retrouver_ref.add(reference);
        retrouver_ref.add(valider_ref_jp);
        retrouver_ref.add(blanc);

        /* Pour Nom Prénom */
        jl_prenom.setLabelFor(prenom);
        retrouver_np.setLayout(new GridLayout(4, 1));
        retrouver_np.setBackground(fond);
        retrouver_np.add(textRetrouver_np);
        retrouver_np.add(jp_nom_prenom);
        retrouver_np.add(valider_np_jp);
        retrouver_np.add(blanc);

        /* Nettoie le centre */
        if (this.centre != null) {
            this.fenetre.remove(centre);
            this.fenetre.repaint();
            this.fenetre.revalidate();
        }

        this.fenetre.getContentPane().setBackground(fond);

        this.fenetre.add(retrouver_ref, BorderLayout.SOUTH);
        this.fenetre.add(retrouver_np, BorderLayout.NORTH);

        /* Les Listener */
        valider_ref.addActionListener(new TraitementReference(this.bookingPointComAPISeulement,
                this.grandLivreDOrAPISeulement, this.fenetre, this.centre, reference));
        valider_np.addActionListener(new TraitementNomPrenom(this.bookingPointComAPISeulement,
                this.grandLivreDOrAPISeulement, this.fenetre, this.centre, nom, prenom));

        this.fenetre.setVisible(true);
    }
}
