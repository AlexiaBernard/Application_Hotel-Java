package fr.iutfbleau.projetIHM2021FI2.IHM1.Modele;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;
import fr.iutfbleau.projetIHM2021FI2.MNP.*;

public class VerificationNomPrenom {
    public VerificationNomPrenom(PrereservationFactory bookingPointComAPISeulement, JFrame fenetre, String nom, String prenom){
     
        try{
            Set<Prereservation> prereservations = bookingPointComAPISeulement.getPrereservations(nom, prenom);

            System.out.println(nom + " " + prenom);
            //...
        }catch(IllegalStateException e){
            System.out.print("Je n'ai pas trouvé de préreservation avec ces nom et prénom.\n");
            new Menu(bookingPointComAPISeulement, fenetre, 2);
        }        
    }
}
