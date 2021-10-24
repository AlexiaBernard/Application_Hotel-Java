package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Modele.AjoutReservation;

public class TraitementListeValider implements ActionListener {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Prereservation prereservation;
    private ButtonGroup group;
    private Set<Chambre> disponibles;

    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param prereservation
     * @param group
     * @param disponibles
     */
    public TraitementListeValider(PrereservationFactory bookingPointComAPISeulement,
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, Prereservation prereservation,
            ButtonGroup group, Set<Chambre> disponibles) {
                this.bookingPointComAPISeulement = bookingPointComAPISeulement;
                this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
                this.fenetre = fenetre;
                this.prereservation = prereservation;
                this.group = group;
                this.disponibles = disponibles;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String retour = group.getSelection().getActionCommand();
        String val = null;
        Chambre choisi = null;

        for(Chambre c : this.disponibles){
            val =""+c.getNumero();
            if(val.equals(retour)){
                choisi = c;
                break;
            }
        }
        if(choisi!=null){
            AjoutReservation reser = new AjoutReservation(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, choisi, prereservation);  
            reser.run();
        }else{
            System.err.println("Problème dans TraitementListeValider : chambre nulle !");
        }        
    }
}
