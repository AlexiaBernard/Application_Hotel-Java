package Vues;
//package fr.iutfbleau.projetIHM2021FI2.Vues;

//import fr.iutfbleau.projetIHM2021FI2.MNP.*;
//import fr.iutfbleau.projetIHM2021FI2.API.*;
import MNP.*;
import API.*;

public class VerificationReference {

    //public Prereservation getPrereservation(String r);
    public VerificationReference(String reference){
        PrereservationFactoryNP pre = new PrereservationFactoryNP();
        PrereservationNP prereservation = pre.getPrereservation(reference);
        System.out.println(reference);
    }  
}
