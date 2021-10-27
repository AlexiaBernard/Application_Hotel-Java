package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Modele.VerificationReference;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;

public class TraitementListeValiderReference implements ActionListener {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private ButtonGroup group;
    private Set<Prereservation> prereservations;
    private JPanel centre;

        /**
         * 
         * @param bookingPointComAPISeulement
         * @param grandLivreDOrAPISeulement
         * @param fenetre
         * @param group
         * @param prereservations
         * @param centre
         */
    public TraitementListeValiderReference(PrereservationFactory bookingPointComAPISeulement,
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, ButtonGroup group,
            Set<Prereservation> prereservations, JPanel centre) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.group = group;
        this.prereservations = prereservations;
        this.centre = centre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String retour = group.getSelection().getActionCommand();
        String val = null;
        Prereservation choisi = null;

        for(Prereservation p : this.prereservations){
            val =""+p.getReference();
            if(val.equals(retour)){
                choisi = p;
                break;
            }
        }
        if(choisi!=null){
            AfficherReservations del = new AfficherReservations(bookingPointComAPISeulement, grandLivreDOrAPISeulement, fenetre, prereservations);
            del.deleteRef(this.centre);
            VerificationReference ref = new VerificationReference(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, choisi.getReference(), this.centre);
            ref.run();
        }else{
            System.err.println("Problème dans TraitementListeValiderReference : prereservation nulle !");
        }        
    }
}