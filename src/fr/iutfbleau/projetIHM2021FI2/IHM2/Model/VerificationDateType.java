package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.API.TypeChambre;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.AfficherRatioType;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

/**
 * <code>VerificationDateType</code> est un modèle.
 * Il sert à calculer le ratio pour une date et un type de chambre donnés.
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class VerificationDateType {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private LocalDate date;
    private TypeChambre type;
    private JPanel centre;

    /**
     * Constructeur permettant d'accéder à un date et un type de chambre
     * 
     * @param fenetre                   la fenetre
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param date                      date donnée
     * @param type                      type de la chambre
     * @param centre                    centre de la fenetre
     */
    public VerificationDateType(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, LocalDate date,
            TypeChambre type, JPanel centre) {

        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.date = date;
        this.type = type;
        this.centre = centre;
    }

    /**
     * Vérification de l'état de la date et du type de chambre donnés et appel de la
     * fonction pour l'affichage du ratio
     */
    public void run(){
        try {
            int ratio = grandLivreDOrAPISeulement.getRatio(this.date, this.type);
            AfficherRatioType aff = new AfficherRatioType(this.fenetre, this.grandLivreDOrAPISeulement, 
                    this.date, ratio, this.type, this.centre);
            aff.run();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(fenetre,"Problème de calcul du ratio pour cette date et ce type de chambre.");
            Bureau bureau = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
            bureau.run();
        }
    }
    
}
