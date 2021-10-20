package fr.iutfbleau.projetIHM2021FI2.Controller;

import fr.iutfbleau.projetIHM2021FI2.API.Chambre;
import fr.iutfbleau.projetIHM2021FI2.API.Client;
import fr.iutfbleau.projetIHM2021FI2.API.Prereservation;
import fr.iutfbleau.projetIHM2021FI2.API.Reservation;
import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.MNP.ChambreNP;
import fr.iutfbleau.projetIHM2021FI2.MNP.ReservationNP;
import fr.iutfbleau.projetIHM2021FI2.Modele.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.Date;

/**
 * <code>TraitementNomPrenom</code> est un contrôleur.
 * Il gère le bouton lancer du menu
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */

public class TraitementValider implements ActionListener{
    private JFrame fenetre;
    private Chambre chambre;
    private Prereservation prereservation;

    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param fenetre the linked window
     * @param prereservation
     * @param chambre
     */
    public TraitementValider(JFrame fenetre, Chambre chambre, Prereservation prereservation) {
        this.fenetre = fenetre;
        this.chambre = chambre;
        this.prereservation = prereservation;
    }

    /**
     * Lors du clic sur le bouton
     * @param e the action data
     */
    @Override
    public void actionPerformed(ActionEvent e){
        //ReservationFactoryNP chambresReservees = new ReservationFactoryNP();
        //chambresReservees.createReservation(prereservation, chambre);
        System.out.println("Réservation validée");
    }
    
}

/* Utilisation de 
public Reservation createReservation(Prereservation p, Chambre c);
*/