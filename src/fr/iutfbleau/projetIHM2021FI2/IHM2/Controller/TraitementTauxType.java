package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.API.TypeChambre;
import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.VerificationDateType;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

/**
 * <code>TraitementGraphique</code> est un contrôleur. Il gère le bouton
 * <b>Demander</b> le taux du Bureau
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class TraitementTauxType implements ActionListener {

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
    public TraitementTauxType(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, JPanel centre) {
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
            String input = JOptionPane.showInputDialog(this.fenetre,"Veuillez entrer la date du jour ou vous souhaitez avoir ce taux. (JJ/MM/AAAA)");
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                LocalDate date = LocalDate.parse(input, inputFormat);
                Object[] options = {"1LS", "2LS", "1LD", "Annuler" };
                int retour = JOptionPane.showOptionDialog(this.fenetre,
                                            "De quel type voulez vous faire le ratio ?(L = lit, S = simple, D = double)",
                                            "Type de chambre",
                                            JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            options,
                                            options[0] );
    
                TypeChambre type = null;
                if (retour == 0 || retour == 1 || retour ==2){
                    if (retour == 0){
                        type = TypeChambre.UNLS;
                    } else if (retour == 1) {
                        type = TypeChambre.DEUXLS;
                    } else if (retour == 2) {
                        type = TypeChambre.UNLD;
                    }
                    VerificationDateType verif = new VerificationDateType(this.fenetre, this.grandLivreDOrAPISeulement, date, type, this.centre);
                    verif.run();
                } else {
                    Bureau bureau = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
                    bureau.run();
                }
            } catch (NullPointerException f){
                Bureau bur = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
                bur.run();
            }
        }catch (DateTimeParseException f){
            JOptionPane.showMessageDialog(this.fenetre,"Le format de la date n'est pas correct.");
            Bureau bur = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
            bur.run();
        }
    }
}
