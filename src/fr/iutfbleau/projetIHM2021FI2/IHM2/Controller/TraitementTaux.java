package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.*;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

/**
 * <code>TraitementGraphique</code> est un contrôleur. Il gère le bouton
 * <b>Demander</b> le taux du Bureau
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class TraitementTaux implements ActionListener {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JPanel centre;

    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * 
     * @param fenetre                   la fenetre
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param centre                    le centre de la fenetre
     */
    public TraitementTaux(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
    }
    
    /**
     * Récupère des informations supplémentaires pour ensuite créer le graphique
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = JOptionPane.showInputDialog(this.fenetre,
                    "Veuillez entrer la date du jour ou vous souhaitez avoir ce taux. (JJ/MM/AAAA)");
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                LocalDate date = LocalDate.parse(input, inputFormat);
                VerificationDate verif = new VerificationDate(this.fenetre, this.grandLivreDOrAPISeulement, date,
                        this.centre);
                verif.run();
            } catch (NullPointerException g) {
                Bureau bur = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
                bur.run();
            }
        } catch (DateTimeParseException f) {
            JOptionPane.showMessageDialog(this.fenetre, "Le format de la date n'est pas correct.");
            Bureau bur = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
            bur.run();
        }
    }
}
