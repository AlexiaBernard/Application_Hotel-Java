package fr.iutfbleau.projetIHM2021FI2.Vues;

import fr.iutfbleau.projetIHM2021FI2.MNP.*;
import fr.iutfbleau.projetIHM2021FI2.API.*;
import java.time.LocalDate;
import javax.swing.*;;

public class Afficher {

    public Afficher(Prereservation prereservation){
        TypeChambre type = prereservation.getTypeChambre();
        LocalDate dateDebut = prereservation.getDateDebut();
        int jours = prereservation.getJours();
        System.out.println("Prereservation = "+ prereservation.monPrint());
        System.out.println("Type de Chambre = "+type+ "Date de début = "+dateDebut+"Nombre de jour = "+jours);
    }
    
}
