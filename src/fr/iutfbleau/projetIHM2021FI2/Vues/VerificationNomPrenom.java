package Vues;
//package fr.iutfbleau.projetIHM2021FI2.Vues;

//import fr.iutfbleau.projetIHM2021FI2.MNP.*;
//import fr.iutfbleau.projetIHM2021FI2.API.*;
import MNP.*;
import API.*;

public class VerificationNomPrenom {
    public VerificationNomPrenom(String nom, String prenom){
        PrereservationFactoryNP pre = new PrereservationFactoryNP();
        PrereservationNP prereservation = pre.getPrereservation(nom, prenom);

        System.out.println(nom + " " + prenom);
    }
}
