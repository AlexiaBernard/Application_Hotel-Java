package fr.iutfbleau.projetIHM2021FI2.IHM1.Vues;
import javax.swing.JFrame;

public class Main {
 public static void main(String[] args) {

	JFrame fenetre = new JFrame("Menu");

	fenetre.setSize(800, 600);
	fenetre.setLocation(0, 0);
	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	new Menu(fenetre, 0);
 }    
}

/*
A demander : 
_Pas de Table Prereservation ??
_On peut faire notre propre base de données ? : pour pouvoir insérer des reservations
_Pas de Table Chambre ?? 
*/
